#include "stdafx.h"

#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>
#pragma comment(lib, "Ws2_32.lib")

#include <queue>

#include "CommunicationController.h"
#include "CriticalSection.h"
#include "Thread.h"

// =============================================================================

static const int DEFAULT_BUFLEN = 512;

// =============================================================================

namespace desm {

	struct CommunicationController::Impl {

		typedef Thread<typename CommunicationController::Impl>   tThread;
		typedef CommunicationController::eMode                   eMode;
		typedef std::queue<std::string>                          tQueue;

		eMode                  m_mode;
		std::string            m_host;
		unsigned short         m_port;

		// IMPORTANT: use wrapper functions below to ensure thread-safety
		tQueue                 m_sendQueue;
		CriticalSectionHandle  m_sendCSH;
		tQueue                 m_recvQueue;
		CriticalSectionHandle  m_recvCSH;

		tThread*               m_mainThread;
		bool                   m_mainThreadStop;
		SOCKET                 m_mainSocket;
		tThread*               m_recvThread; // not ideal but best to keep code similar and simple for client and server
		bool                   m_recvThreadStop;
		SOCKET                 m_recvSocket;

		Impl(eMode mode, const std::string& host, unsigned short port)
			: m_mode(mode)
			, m_host(host)
			, m_port(port)
			, m_sendQueue()
			, m_sendCSH()
			, m_recvQueue()
			, m_recvCSH()
			, m_mainThread(NULL)
			, m_mainThreadStop(false)
			, m_mainSocket(INVALID_SOCKET)
			, m_recvThread(NULL)
			, m_recvThreadStop(false)
			, m_recvSocket(INVALID_SOCKET)
		{
			WSADATA wsa;
			if(WSAStartup(MAKEWORD(2,0), &wsa) != 0) {
				throw std::bad_alloc("unable to start windows sockets");
			}
			// setup main threads
			DWORD (Impl::*fct)(void) = NULL;
			switch(mode) {
			case MODE_SERVER:
				fct = &Impl::startServer;
				break;
			case MODE_CLIENT:
				fct = &Impl::startClient;
				break;
			default:
				throw std::bad_alloc("invalid mode");
			};
			m_mainThread = new tThread(this, fct);
			if(!m_mainThread) {
				throw std::bad_alloc("unable to allocate main communication thread");
			}
			// start main thread
			if(!m_mainThread->start()) {
				throw std::bad_alloc("unable to start main communication thread");
			}
		}

		~Impl() {
			m_mainThreadStop = true;
			m_recvThreadStop = true;
			if(m_recvSocket != INVALID_SOCKET && m_recvSocket != m_mainSocket) {
				closesocket(m_recvSocket);
			}
			if(m_mainSocket != INVALID_SOCKET) {
				closesocket(m_mainSocket);
			}
			clearQueue(m_recvQueue, m_recvCSH);
			clearQueue(m_sendQueue, m_sendCSH);
			WSACleanup();
			m_mainThread->join();
			if(m_recvThread) {
				m_recvThread->join();
			}
			delete m_recvThread;
			delete m_mainThread;
		}

		void clearQueue(tQueue& q, CriticalSectionHandle& csh) {
			CriticalSection cs(csh);
			while(!q.empty()) {
				q.pop();
			}
		}

		void pushQueue(tQueue& q, const std::string& data, CriticalSectionHandle& csh) {
			CriticalSection cs(csh);
			q.push(data);
		}

		bool popQueue(tQueue& q, std::string& data, CriticalSectionHandle& csh) {
			CriticalSection cs(csh);
			if(q.empty()) {
				return false;
			}
			data = q.front();
			q.pop();
			return true;
		}

		bool startReceiveThread() {
			m_recvThread = new tThread(this, &Impl::receiveData);
			return m_recvThread && m_recvThread->start();
		}

		DWORD receiveData() {
			long rc = 0;
			char buf[DEFAULT_BUFLEN];
			while(rc != SOCKET_ERROR && !m_recvThreadStop) {
				rc = ::recv(m_recvSocket, buf, DEFAULT_BUFLEN, 0);
				if(rc == 0) {
					printf("Client hat die Verbindung getrennt..\n");
					break;
				}
				if(rc == SOCKET_ERROR) {
					printf("Fehler: recv, fehler code: %d\n", WSAGetLastError());
					break;
				}
				// TODO: stitch buffer together until NUL byte received?
				buf[rc]='\0';
				printf("[Receive] %s\n", buf);
				pushQueue(m_recvQueue, std::string(buf), m_recvCSH);
			}
			return 0;
		}

		DWORD sendData() {
			long rc = 0;
			// Daten austauschen
			std::string data;
			while(rc != SOCKET_ERROR && !m_mainThreadStop) {
				if(popQueue(m_sendQueue, data, m_sendCSH)) {
					printf("[Sending] %s\n", data.c_str());
					rc = ::send(m_recvSocket, data.c_str(), data.length(), 0);
					if(rc == SOCKET_ERROR) {
						break;
					}
				} else {
					// some polling going on...
					Sleep(50);
				}
			}
			return (rc == SOCKET_ERROR) ? 1 : 0;
		}

		DWORD startServer(){
			long rc;
			SOCKADDR_IN addr;

			// Socket erstellen
			m_mainSocket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(m_mainSocket == INVALID_SOCKET) {
				printf("Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n",WSAGetLastError());
				return 1;
			} else {
				printf("Socket erstellt!\n");
			}

			// Socket binden
			memset(&addr, 0, sizeof(SOCKADDR_IN));
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = ADDR_ANY;
			rc = ::bind(m_mainSocket, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
			if(rc == SOCKET_ERROR) {
				printf("Fehler: bind, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Socket an port gebunden\n");
			}

			// In den listen Modus
			rc = ::listen(m_mainSocket, 10);
			if(rc == SOCKET_ERROR) {
				printf("Fehler: listen, fehler code: %d\n",WSAGetLastError());
				return 1;
			} else {
				printf("m_socket ist im listen Modus....\n");
			}

			// Max: now it may get a bit confusing with the socket names...

			// Verbindung annehmen
			m_recvSocket = ::accept(m_mainSocket, NULL, NULL);
			if(m_recvSocket == INVALID_SOCKET) {
				fprintf(stderr, "Fehler: accept, fehler code: %d\n",WSAGetLastError());
				return 1;
			} else {
				fprintf(stderr, "Neue Verbindung wurde akzeptiert!\n");
			}

			if(!startReceiveThread()) {
				fprintf(stderr, "unable to start receiving thread");
				return 1;
			}

			return sendData();
		}

		DWORD startClient(){
			long rc;
			SOCKADDR_IN addr;

			// Socket erstellen
			m_mainSocket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(m_mainSocket == INVALID_SOCKET) {
				fprintf(stderr, "Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Socket erstellt!\n");
			}

			// Verbinden
			memset(&addr, 0, sizeof(SOCKADDR_IN)); // zuerst alles auf 0 setzten
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = inet_addr(m_host.c_str()); // zielrechner ist unser eigener

			rc = ::connect(m_mainSocket, (SOCKADDR*)&addr, sizeof(SOCKADDR));
			if(rc == SOCKET_ERROR) {
				m_mainSocket = INVALID_SOCKET;
				printf("Fehler: connect gescheitert, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Client ist verbunden mit Server..\n");
			}

			// again that confusin socket stuff -> needs to be improved
			m_recvSocket = m_mainSocket;
			if(!startReceiveThread()) {
				fprintf(stderr, "unable to start receiving thread");
				return 1;
			}

			return sendData();
		}

	};

	// =============================================================================

	CommunicationController::CommunicationController(CommunicationController::eMode mode, const std::string& host, unsigned short port)
		: pimpl(new Impl(mode, host, port))
	{
	}

	CommunicationController::~CommunicationController()
	{
		delete pimpl;
	}

	bool CommunicationController::receive(std::string& data)
	{
		return pimpl->popQueue(pimpl->m_recvQueue, data, pimpl->m_recvCSH);
	}

	bool CommunicationController::send(const std::string& data)
	{
		pimpl->pushQueue(pimpl->m_sendQueue, data, pimpl->m_sendCSH);
		return true;
	}

};
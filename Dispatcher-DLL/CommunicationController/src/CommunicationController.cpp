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
#include "Thread.h"

// =============================================================================

static const int DEFAULT_BUFLEN = 512;

// =============================================================================

struct CommunicationController::Impl {

	typedef Thread<typename CommunicationController::Impl>   tThread;
	typedef CommunicationController::eMode                   eMode;
	typedef std::queue<std::string>                          tQueue;

	eMode           m_mode;
	std::string     m_host;
	unsigned short  m_port;

	// IMPORTANT: use wrapper functions below to ensure thread-safety
	tQueue          m_receiveQueue;
	tQueue          m_sendingQueue;

	tThread*        m_mainThread;
	bool            m_mainThreadStop;

	SOCKET          m_socket;

	Impl(eMode mode, const std::string& host, unsigned short port)
		: m_mode(mode)
		, m_host(host)
		, m_port(port)
		, m_receiveQueue()
		, m_sendingQueue()
		, m_mainThread(NULL)
		, m_mainThreadStop(false)
		, m_socket(INVALID_SOCKET)
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
		if(m_socket != INVALID_SOCKET) {
			closesocket(m_socket);
		}
		WSACleanup();
		m_mainThreadStop = true;
		clearQueue(m_receiveQueue);
		clearQueue(m_sendingQueue);
		m_mainThread->join();
		delete m_mainThread;
	}

	void clearQueue(tQueue& q/* TODO hand in according mutex */) {
		// TODO: add critical section!
		while(!q.empty()) {
			q.pop();
		}
	}

	void pushQueue(tQueue& q, const std::string& data/* TODO hand in according mutex */) {
		// TODO: add critical section!
		q.push(data);
	}

	bool popQueue(tQueue& q, std::string& data/* TODO hand in according mutex */) {
		// TODO: add critical section!
		if(q.empty()) {
			return false;
		}
		data = q.front();
		q.pop();
		return true;
	}

	DWORD startServer(){
		long rc;
		SOCKADDR_IN addr;
		char buf[DEFAULT_BUFLEN];

		// Socket erstellen
		m_socket = socket(AF_INET, SOCK_STREAM, 0);
		if(m_socket == INVALID_SOCKET) {
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
		rc = ::bind(m_socket, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
		if(rc == SOCKET_ERROR) {
			printf("Fehler: bind, fehler code: %d\n", WSAGetLastError());
			return 1;
		} else {
			printf("Socket an port gebunden\n");
		}

		// In den listen Modus
		rc = ::listen(m_socket, 10);
		if(rc == SOCKET_ERROR) {
			printf("Fehler: listen, fehler code: %d\n",WSAGetLastError());
			return 1;
		} else {
			printf("m_socket ist im listen Modus....\n");
		}

		// Verbindung annehmen
		SOCKET connectedSocket = ::accept(m_socket, NULL, NULL);
		if(connectedSocket == INVALID_SOCKET) {
			printf("Fehler: accept, fehler code: %d\n",WSAGetLastError());
			system("pause");
			return 1;
		}
		else {
			printf("Neue Verbindung wurde akzeptiert!\n");
		}

		// TODO: move to thread that handles each thread conncetion sepearately
		{
			// Daten austauschen
			while(rc != SOCKET_ERROR && !m_mainThreadStop) {
				rc = ::recv(connectedSocket, buf, DEFAULT_BUFLEN, 0);
				if(rc == 0) {
					printf("Client hat die Verbindung getrennt..\n");
					break;
				}
				if(rc == SOCKET_ERROR) {
					printf("Fehler: recv, fehler code: %d\n", WSAGetLastError());
					break;
				}
				buf[rc]='\0';
				printf("[Server] received: %s\n", buf);
				pushQueue(m_receiveQueue, std::string(buf));
				std::string data;
				while(popQueue(m_sendingQueue, data)) {
					rc = ::send(connectedSocket, data.c_str(), data.length(), 0);
					if(rc == SOCKET_ERROR) {
						break;
					}
				}
			}

			closesocket(connectedSocket);
		}

		return 0;
	}

	DWORD startClient(){
		long rc;
		SOCKADDR_IN addr;
		char buf[DEFAULT_BUFLEN];

		// Socket erstellen
		m_socket = ::socket(AF_INET, SOCK_STREAM, 0);
		if(m_socket == INVALID_SOCKET) {
			printf("Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n", WSAGetLastError());
			return 1;
		} else {
			printf("Socket erstellt!\n");
		}

		// Verbinden
		memset(&addr, 0, sizeof(SOCKADDR_IN)); // zuerst alles auf 0 setzten
		addr.sin_family = AF_INET;
		addr.sin_port = m_port;
		addr.sin_addr.s_addr = inet_addr(m_host.c_str()); // zielrechner ist unser eigener

		rc = ::connect(m_socket, (SOCKADDR*)&addr, sizeof(SOCKADDR));
		if(rc == SOCKET_ERROR) {
			m_socket = INVALID_SOCKET;
			printf("Fehler: connect gescheitert, fehler code: %d\n", WSAGetLastError());
			return 1;
		} else {
			printf("Client ist verbunden mit Server..\n");
		}

		// Daten austauschen
		while(rc != SOCKET_ERROR && !m_mainThreadStop) {
			std::string data;
			while(popQueue(m_sendingQueue, data)) {
				printf("[Client] sending: %s\n", data.c_str());
				rc = ::send(m_socket, data.c_str(), data.length(), 0);
				if(rc == SOCKET_ERROR) {
					break;
				}
			}
			if(rc == SOCKET_ERROR) {
				break;
			}
			// TODO: put receiving in separate thread to avoid blocking of sending!
			rc = ::recv(m_socket, buf, DEFAULT_BUFLEN, 0);
			if(rc == 0) {
				printf("Server hat die Verbindung getrennt..\n");
				break;
			}
			if(rc == SOCKET_ERROR) {
				printf("Fehler: recv, fehler code: %d\n", WSAGetLastError());
				break;
			}
			buf[rc] = '\0';
			printf("[Client] received: %s\n", buf);
			pushQueue(m_receiveQueue, std::string(buf));
		}

		return 0;
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
	return pimpl->popQueue(pimpl->m_receiveQueue, data);
}

bool CommunicationController::send(const std::string& data)
{
	pimpl->pushQueue(pimpl->m_sendingQueue, data);
	return true;
}

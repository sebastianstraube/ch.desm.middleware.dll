#include "stdafx.h"

#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>
#pragma comment(lib, "Ws2_32.lib")

#include <list>
#include <queue>

#include "CommunicationController.h"
#include "CriticalSection.h"
#include "SecureQueue.h"
#include "Thread.h"

////////////////////////////////////////////////////////////////////////////////

static const int DEFAULT_BUFLEN = 512;
static const DWORD INTERRUPT_TIMEOUT = 20;


////////////////////////////////////////////////////////////////////////////////

namespace desm {

	struct CommunicationController::Impl {
		
		////////////////////////////////////////////////////////////////////////
		// member

		typedef Thread<typename CommunicationController::Impl, void*>    tMainThread;
		typedef Thread<typename CommunicationController::Impl, SOCKET*>  tSocketThread;
		typedef CommunicationController::eMode                           eMode;
		typedef SecureQueue<std::string>                                 tQueue;

		eMode              m_mode;
		std::string        m_host;
		unsigned short     m_port;

		tQueue             m_sendQueue;
		tQueue             m_recvQueue;

		tMainThread*       m_mainThread;
		bool               m_mainThreadStop;
		
		struct Connection {
			Connection() : recvThread(NULL), sendThread(NULL), socket(INVALID_SOCKET) {}
			~Connection() { delete recvThread; delete sendThread; }
			tSocketThread* recvThread;
			tSocketThread* sendThread;
			SOCKET socket;
		};
		
		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(eMode mode, const std::string& host, unsigned short port)
			: m_mode(mode)
			, m_host(host)
			, m_port(port)
			, m_sendQueue()
			, m_recvQueue()
			, m_mainThread(NULL)
			, m_mainThreadStop(false)
		{
			// TODO: really start wsa stuff on every impl call?
			WSADATA wsa;
			if(WSAStartup(MAKEWORD(2,0), &wsa) != 0) {
				throw std::bad_alloc("unable to start windows sockets");
			}
			// setup main threads
			DWORD (Impl::*fct)(void*) = NULL;
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
			m_mainThread = new tMainThread(this, fct);
			if(!m_mainThread || !m_mainThread->start()) {
				throw std::bad_alloc("unable to start main thread");
			}
		}

		~Impl() {
			m_recvQueue.clear();
			m_sendQueue.clear();
			m_mainThreadStop = true;
			m_mainThread->interrupt();
			m_mainThread->join();
			delete m_mainThread;
			// TODO: same here, really cleanup on every impl close?
			WSACleanup();
		}
		
		////////////////////////////////////////////////////////////////////////
		// thread helper

		bool startReceiveThread(Connection& c) {
			c.recvThread = new tSocketThread(this, &Impl::receiveData, &c.socket);
			return c.recvThread && c.recvThread->start();
		}

		bool startSendThread(Connection& c) {
			c.sendThread = new tSocketThread(this, &Impl::sendData, &c.socket);
			return c.sendThread && c.sendThread->start();
		}
		
		bool startAcceptThread(tSocketThread** thread, SOCKET* s) {
			*thread = new tSocketThread(this, &Impl::acceptConnections, s);
			return *thread && (*thread)->start();
		}

		////////////////////////////////////////////////////////////////////////
		// socket thread helper

		DWORD receiveData(SOCKET* s) {
			long rc = 0;
			char buf[DEFAULT_BUFLEN];
			while(rc != SOCKET_ERROR && *s && *s != INVALID_SOCKET) {
				rc = ::recv(*s, buf, DEFAULT_BUFLEN-1, 0);
				if(rc == 0) {
					printf("[%d] Connection closed\n", m_mode);
					break;
				}
				if(rc == SOCKET_ERROR) {
					int wsaErr = WSAGetLastError();
					if(wsaErr == WSAECONNABORTED || wsaErr == WSAECONNRESET) {
						printf("[%d] Connection closed\n", m_mode);
					} else {
						printf("[%d] Unknown WSA Error: %d\n", m_mode, WSAGetLastError());
					}
					break;
				}
				if(rc < 0) {
					printf("[%d] Unknown Socket Error: %d\n", m_mode, rc);
					break;
				}
				// TODO: stitch buffer together until NUL byte received?
				buf[rc]='\0';
				printf("[Receive] %s\n", buf);
				m_recvQueue.push(std::string(buf));
			}
			printf("[%d] leaving receive thread!\n", m_mode);
			return (rc < 0) ? 1 : 0;
		}

		DWORD sendData(SOCKET* s) {
			long rc = 0;
			// Daten austauschen
			std::string data;
			while(rc != SOCKET_ERROR && *s && *s != INVALID_SOCKET) {
				if(m_sendQueue.pop(data)) {
					printf("[Sending] %s\n", data.c_str());
					rc = ::send(*s, data.c_str(), data.length(), 0);
					if(rc == SOCKET_ERROR || rc < 0) {
						break;
					}
				} else {
					// some polling going on...
					Sleep(10);
				}
			}
			printf("[%d] leaving send thread!\n", m_mode);
			return (rc < 0) ? 1 : 0;
		}

		DWORD acceptConnections(SOCKET* s) {
			std::list<Connection*> connections;
			while(!m_mainThreadStop && *s && *s != INVALID_SOCKET) {
				Connection* c = new Connection();
				c->socket = ::accept(*s, NULL, NULL);
				if(c->socket == INVALID_SOCKET) {
					int wsaErr = WSAGetLastError();
					if(wsaErr == WSAECONNABORTED || wsaErr == WSAECONNRESET || wsaErr == WSAEINTR) {
						printf("[%d] Connection closed\n", m_mode);
					} else {
						printf("[%d] Unknown WSA Error: %d\n", m_mode, WSAGetLastError());
					}
					// TODO memory leak of c
					break;
				} else {
					fprintf(stderr, "Neue Verbindung wurde akzeptiert!\n");
				}
				
				if(!startReceiveThread(*c)) {
					fprintf(stderr, "unable to start receiving thread");
					// TODO memory leak of c
					break;
				}

				if(!startSendThread(*c)) {
					fprintf(stderr, "unable to start receiving thread");
					// TODO memory leak of c
					break;
				}

				connections.push_back(c);
			}
			
			std::list<Connection*>::iterator it = connections.begin();
			for(; it != connections.end(); ++it) {
				Connection* c = *it;
				if(!c) { continue; }
				// kill connection's socket and wait for him to close on its own
				if(c->socket != INVALID_SOCKET) {
					::closesocket(c->socket);
					c->socket = INVALID_SOCKET;
				}
				c->recvThread->join();
				c->sendThread->join();
				delete c;
			}

			return 0;
		}
		
		////////////////////////////////////////////////////////////////////////
		// server impl

		DWORD startServer(void*){
			long rc;
			SOCKADDR_IN addr;
			SOCKET mainSocket = INVALID_SOCKET;

			// Socket erstellen
			mainSocket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(mainSocket == INVALID_SOCKET) {
				fprintf(stderr, "Unable to start server: %d\n",WSAGetLastError());
				return 1;
			}

			// Socket binden
			memset(&addr, 0, sizeof(SOCKADDR_IN));
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = ADDR_ANY;
			rc = ::bind(mainSocket, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
			if(rc == SOCKET_ERROR) {
				fprintf(stderr, "Unable to start server: %d\n", WSAGetLastError());
				return 1;
			}

			// In den listen Modus
			rc = ::listen(mainSocket, 10);
			if(rc == SOCKET_ERROR) {
				fprintf(stderr, "Unable to start server: %d\n", WSAGetLastError());
				return 1;
			}
			if(rc < 0) {
				fprintf(stderr, "Unable to start server: %d\n", rc);
				return 1;
			}

			tSocketThread* acceptThread;
			if(!startAcceptThread(&acceptThread, &mainSocket)) {
				fprintf(stderr, "Unable to start accept thread\n");
				return 1;
			}
			
			while(acceptThread && acceptThread->isRunning()) {
				bool interrupted = false;
				interrupted = interrupted || acceptThread->isInterrupted(INTERRUPT_TIMEOUT);
				interrupted = interrupted || m_mainThread->isInterrupted(INTERRUPT_TIMEOUT);
				if(interrupted) {
					printf("Server shutdown requested!\n");
					if(mainSocket != INVALID_SOCKET) {
						::closesocket(mainSocket);
						// TODO handle closesocket's return code
						mainSocket = INVALID_SOCKET;
					}
					acceptThread->join();
					break;
				}
			}
			
			return 0;
		}
		
		////////////////////////////////////////////////////////////////////////
		// client impl

		DWORD startClient(void*){
			int maxRetryCount = 10;
			int currRetryCount = 0;
			long rc;
			SOCKADDR_IN addr;

			do {		
				Connection c;

				currRetryCount++;
				if(currRetryCount > 1) {
					printf("Client retry: %d\n", currRetryCount);
				}

				c.socket = ::socket(AF_INET, SOCK_STREAM, 0);
				if(c.socket == INVALID_SOCKET) {
					fprintf(stderr, "Unable to create Client socket: %d\n", WSAGetLastError());
					continue;
				}
				
				// Verbinden
				memset(&addr, 0, sizeof(SOCKADDR_IN));
				addr.sin_family = AF_INET;
				addr.sin_port = m_port;
				addr.sin_addr.s_addr = inet_addr(m_host.c_str());
				
				rc = ::connect(c.socket, (SOCKADDR*)&addr, sizeof(SOCKADDR));
				if(rc == SOCKET_ERROR) {
					c.socket = INVALID_SOCKET;
					fprintf(stderr, "Unable to connect to server: %d\n", WSAGetLastError());
					continue;
				}
				
				if(!startReceiveThread(c)) {
					fprintf(stderr, "Unable to start receiving thread\n");
					continue;
				}
				
				if(!startSendThread(c)) {
					fprintf(stderr, "Unable to start sending thread\n");
					continue;
				}
				
				printf("client connected! :) sending/receiving data\n");

				while(c.recvThread->isRunning() || c.sendThread->isRunning()) {
					bool interrupted = false;
					interrupted = interrupted || c.recvThread->isInterrupted(INTERRUPT_TIMEOUT);
					interrupted = interrupted || c.sendThread->isInterrupted(INTERRUPT_TIMEOUT);
					interrupted = interrupted || m_mainThread->isInterrupted(INTERRUPT_TIMEOUT);
					if(interrupted) {
						printf("Client shutdown requested!\n");
						::closesocket(c.socket);
						c.socket = INVALID_SOCKET;
						c.recvThread->join();
						c.sendThread->join();
						break;
					}
				}

			} while(!m_mainThreadStop && currRetryCount < maxRetryCount);

			return 0;
		}

	};

	////////////////////////////////////////////////////////////////////////////

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
		return pimpl->m_recvQueue.pop(data);
	}

	bool CommunicationController::send(const std::string& data)
	{
		pimpl->m_sendQueue.push(data);
		return true;
	}

};
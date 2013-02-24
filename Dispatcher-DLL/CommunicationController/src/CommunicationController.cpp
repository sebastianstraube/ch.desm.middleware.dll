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

static const unsigned DEFAULT_BUFLEN = 10;

static const DWORD INTERRUPT_TIMEOUT = 10;
static const unsigned CLIENT_MAX_RETRY = 10;

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	struct CommunicationController::Impl {
		
		////////////////////////////////////////////////////////////////////////
		// types
		
		typedef struct Connection;

		typedef Thread<typename CommunicationController::Impl, void*>        tMainThread;
		typedef Thread<typename CommunicationController::Impl, Connection*>  tDataThread;
		typedef Thread<typename CommunicationController::Impl, SOCKET*>      tSocketThread;
		typedef CommunicationController::eMode                               eMode;
		typedef SecureQueue<std::string>                                     tQueue;
		
		struct Connection {
			Connection() : recvThread(NULL), sendThread(NULL), socket(INVALID_SOCKET) {}
			~Connection() { delete recvThread; delete sendThread; }
			tDataThread* recvThread;
			tDataThread* sendThread;
			tQueue recvQueue;
			tQueue sendQueue;
			SOCKET socket;
		};
		
		////////////////////////////////////////////////////////////////////////
		// member

		eMode                   m_mode;
		std::string             m_host;
		unsigned short          m_port;

		tQueue                  m_sendQueue;
		tQueue                  m_recvQueue;

		tMainThread*            m_mainThread;
		bool                    m_mainThreadStop;
		std::list<Connection*>  m_clientConnections;
		
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
			, m_clientConnections()
		{
			//! @see http://msdn.microsoft.com/en-us/library/windows/desktop/ms742213(v=vs.85).aspx
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
			//! @see http://msdn.microsoft.com/en-us/library/windows/desktop/ms741549(v=vs.85).aspx
			WSACleanup();
		}

		////////////////////////////////////////////////////////////////////////
		// thread helper

		bool startReceiveThread(Connection* c) {
			c->recvThread = new tDataThread(this, &Impl::receiveData, c);
			return c->recvThread && c->recvThread->start();
		}

		bool startSendThread(Connection* c) {
			c->sendThread = new tDataThread(this, &Impl::sendData, c);
			return c->sendThread && c->sendThread->start();
		}
		
		bool startAcceptThread(tSocketThread** thread, SOCKET* s) {
			*thread = new tSocketThread(this, &Impl::acceptConnections, s);
			return *thread && (*thread)->start();
		}

		////////////////////////////////////////////////////////////////////////
		// socket thread helper

		static const unsigned PAYLOAD_OFFSET = sizeof(unsigned);
		static const unsigned PAYLOAD_MAX_LEN = DEFAULT_BUFLEN - PAYLOAD_OFFSET;
		
		DWORD receiveData(Connection* c) {
			long rc = 0;
			char buf[DEFAULT_BUFLEN];
			std::string partialData;
			while(rc != SOCKET_ERROR && c && c->socket != INVALID_SOCKET) {
				rc = ::recv(c->socket, buf, DEFAULT_BUFLEN, 0);
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
				unsigned remainingLength = *((unsigned*)&buf[0]);
				unsigned payloadLength = rc - PAYLOAD_OFFSET;
				partialData += std::string(&buf[PAYLOAD_OFFSET], payloadLength);
				if(remainingLength == payloadLength) {
					c->recvQueue.push(partialData);
					printf("[Received] %s\n", partialData.c_str());
					partialData.clear();
				}
			}
			printf("[%d] leaving receive thread!\n", m_mode);
			return (rc < 0) ? 1 : 0;
		}

		DWORD sendData(Connection* c) {
			long rc = 0;
			std::string data;
			char buf[DEFAULT_BUFLEN];
			
			while(rc != SOCKET_ERROR && c && c->socket != INVALID_SOCKET) {
				if(data.empty()) {
					if(c->sendQueue.pop(data)) {
						printf("[Sending] %s\n", data.c_str());
					}
				}
				if(!data.empty()) {
					*((unsigned*)&buf[0]) = static_cast<unsigned>(data.size());
					unsigned payloadLen = (PAYLOAD_MAX_LEN < data.size()) ? PAYLOAD_MAX_LEN : data.size();
					memcpy(&buf[PAYLOAD_OFFSET], data.c_str(), payloadLen);
					rc = ::send(c->socket, buf, PAYLOAD_OFFSET + payloadLen, 0);
					if(rc == SOCKET_ERROR || rc < 0) {
						fprintf(stderr, "Error: sending failed %d\n", WSAGetLastError());
						break;
					}
					unsigned sentPayload = static_cast<unsigned>(rc) - PAYLOAD_OFFSET;
					if(sentPayload < data.size()) {
						data = data.substr(sentPayload, std::string::npos);
					} else {
						data.clear();
					}
				} else {
					Sleep(10); // some polling going on...
				}
			}
			printf("[%d] leaving send thread!\n", m_mode);
			return (rc < 0) ? 1 : 0;
		}

		DWORD acceptConnections(SOCKET* s) {
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
				
				if(!startReceiveThread(c)) {
					fprintf(stderr, "unable to start receiving thread");
					// TODO memory leak of c
					break;
				}

				if(!startSendThread(c)) {
					fprintf(stderr, "unable to start receiving thread");
					// TODO memory leak of c
					break;
				}

				m_clientConnections.push_back(c);
			}
			
			std::list<Connection*>::iterator it = m_clientConnections.begin();
			for(; it != m_clientConnections.end(); ++it) {
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
				// forward sending queues
				syncServerQueues(m_clientConnections);
				// check for interruption request
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

		void syncServerQueues(std::list<Connection*>& connections) {
			tQueue tmp;
			m_sendQueue.moveTo(tmp);
			std::list<Connection*>::iterator it = connections.begin();
			// TODO CriticalSection needed here! edited in acceptThread at the same time!
			for(; it != connections.end(); ++it) {
				Connection* connection = *it;
				tmp.copyTo(connection->sendQueue);
				connection->recvQueue.moveTo(m_recvQueue);
			}
		}
		
		////////////////////////////////////////////////////////////////////////
		// client impl

		DWORD startClient(void*){
			unsigned retryCount = 0;
			long rc;
			SOCKADDR_IN addr;

			do {		
				Connection c;

				retryCount++;
				if(retryCount > 1) {
					printf("Client retry: %d/%d\n", retryCount, CLIENT_MAX_RETRY);
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
				
				if(!startReceiveThread(&c)) {
					fprintf(stderr, "Unable to start receiving thread\n");
					continue;
				}
				
				if(!startSendThread(&c)) {
					fprintf(stderr, "Unable to start sending thread\n");
					continue;
				}
				
				printf("client connected! :) sending/receiving data\n");

				while(c.recvThread->isRunning() || c.sendThread->isRunning()) {
					// forward content of according queue
					syncClientQueues(c);
					// check for interruption request
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

			} while(!m_mainThreadStop && retryCount < CLIENT_MAX_RETRY);

			return 0;
		}

		void syncClientQueues(Connection& c) {
			c.recvQueue.moveTo(m_recvQueue);
			m_sendQueue.moveTo(c.sendQueue);
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
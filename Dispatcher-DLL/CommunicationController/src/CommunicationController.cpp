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

	/*
	* CommunicationController Implementation
	**/
	struct CommunicationController::Impl {

		typedef Thread<typename CommunicationController::Impl>   tThread;
		typedef CommunicationController::eMode                   eMode;
		typedef std::queue<std::string>                          tQueue;
		char*				callFrom;
		eMode				   m_mode;
		std::string			   m_ip_server;
		std::string			   m_ip_client;
		unsigned short		   m_port;

		// IMPORTANT: use wrapper functions below to ensure thread-safety
		tQueue                 m_queue;
		CriticalSectionHandle  m_CSH;

		tThread*               m_thread;
		bool                   m_threadStop;
		SOCKET                 m_commonSocket;
		SOCKET                 m_serverDataSocket;
		SOCKET                 m_listenSocket;

		Impl(eMode mode, const std::string& host,  const std::string& client, unsigned short port)
			: m_mode(mode)
			, m_ip_server(host)
			, m_ip_client(client)
			, m_port(port)
			, m_queue()
			, m_CSH()
			, m_thread(NULL)
			, m_threadStop(false)
			, m_commonSocket(INVALID_SOCKET)
		{
			WSADATA wsa;
			if(WSAStartup(MAKEWORD(2,0), &wsa) != 0) {
				throw std::bad_alloc("unable to start windows sockets");
			}
			// setup main threads
			DWORD (Impl::*fct)(void) = NULL;
			switch(mode) {
				case MODE_SERVER:
					callFrom = "Server";
					fct = &Impl::startServer;
					break;
				case MODE_CLIENT:
					callFrom = "Client";
					fct = &Impl::startClient;
					break;
				default:
					throw std::bad_alloc("invalid mode");
			};
			m_thread = new tThread(this, fct);
			if(!m_thread) {
				throw std::bad_alloc("unable to allocate main communication thread");
			}
			// start main thread
			if(!m_thread->start()) {
				throw std::bad_alloc("unable to start main communication thread");
			}
		}

		/*stop server and client
		* problem closing connection to "hard" - keyheading {GRACEFUL, HARD, FORCE}
		* Basti: its possible that the "delete cc_client;" and "delete cc_server;" calls
		* aren't going thru the destructor in this specific sequence or rather the constructor
		* call is in the different threads not synced - that's maybe a probem?
		* 
		* source:
		* interesting stuff - http://beej.us/guide/bgnet/output/print/bgnet_A4.pdf
		* interesting examples - http://tangentsoft.net/wskfaq/
		* check disconnect - http://stackoverflow.com/questions/685951/how-can-i-check-if-a-client-disconnected-through-winsock-in-c
		* Error 10054 - http://support.ipswitch.com/kb/WSK-19980714-EM01.htm
		* http://stackoverflow.com/questions/3444403/winsock-stop-accepting-new-connections-yet-keep-comm-with-existing-connections
		* http://msdn.microsoft.com/en-us/library/windows/desktop/bb530743(v=vs.85).aspx
		* http://msdn.microsoft.com/en-us/library/ms737593%28v=VS.85%29.aspx
		* http://msdn.microsoft.com/en-us/library/windows/desktop/ms740476(v=vs.85).aspx
		* http://stackoverflow.com/questions/11532311/winsock-send-always-returns-error-10057-in-server
		* 
		*/
		~Impl() {
			m_threadStop = true;
			if(m_commonSocket != INVALID_SOCKET) {
				int m_commonSocketResult = shutdown(m_commonSocket, SD_BOTH);
				if (m_commonSocketResult == SOCKET_ERROR) {
					printf("[%s] m_commonSocket shutdown failed with error: %d\n", callFrom, WSAGetLastError());
					closesocket(m_commonSocket);
				}

				int m_serverDataSocketResult = shutdown(m_serverDataSocket, SD_BOTH);
				if (m_serverDataSocketResult == SOCKET_ERROR) {
					printf("[%s] m_serverDataSocketResult shutdown failed with error: %d\n", callFrom, WSAGetLastError());
					closesocket(m_serverDataSocketResult);
				}
			}
			clearQueue(m_queue, m_CSH);
			WSACleanup();

			delete m_thread;
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
			m_thread = new tThread(this, &Impl::receiveData);
			return m_thread && m_thread->start();
		}

		DWORD receiveData() {
			long rc = 0;
			char buf[DEFAULT_BUFLEN];
			SOCKET scopeSocket;
			// Set the socket I/O mode: In this case FIONBIO
			// enables or disables the blocking mode for the 
			// socket based on the numerical value of iMode.
			// If iMode = 0, blocking is enabled; 
			// If iMode != 0, non-blocking mode is enabled.
			u_long iMode = 1;

			printf("[%s] - [receiveDataTHREAD] starting receive data\n", callFrom);
			while(rc != SOCKET_ERROR && !m_threadStop) {
				if(!m_threadStop){

					switch(m_mode) {
						case MODE_SERVER:
							printf("[%s] receiveData scopeSocket = m_serverDataSocket\n", callFrom);
							scopeSocket = m_serverDataSocket;
							break;
						case MODE_CLIENT:
							printf("[%s] receiveData scopeSocket = m_commonSocket\n", callFrom);
							scopeSocket = m_commonSocket;
							break;
						default:
							throw std::bad_alloc("invalid mode");
					};

					rc = ioctlsocket(scopeSocket, FIONBIO, &iMode);
					if (rc != NO_ERROR){
						printf("[%s] failed set iMode on ioctlsocket with error: %ld\n", callFrom, WSAGetLastError());
						break;
					}
					rc = ::recv(scopeSocket, buf, DEFAULT_BUFLEN, 0);
					if (rc != NO_ERROR){
						printf("[%s] failed recv with error: %ld\n", callFrom, WSAGetLastError());
						break;
					}
				}

				if(rc == 0) {
					printf("[%s] Verbindung getrennt ...\n", callFrom);
					break;
				}

				// Shutdown our socket
				printf("[%s] shutdown scopeSocket!\r\n", callFrom);
				rc = shutdown(scopeSocket,SD_RECEIVE);

				// Close our socket entirely
				printf("[%s] close scopeSocket!\r\n", callFrom);
				rc = closesocket(scopeSocket);

				// TODO: stitch buffer together until NULL byte received?
				buf[rc]='\0';
				printf("[%s] [Receive] %d\n", callFrom, buf);
				pushQueue(m_queue, std::string(buf), m_CSH);
			}
			// All data left us, so need to shutdown the connection - till sending next time data.
			//printf("[%s] shutdown scopeSocket ...\n", callFrom);
			//int m_commonSocketResult = shutdown(scopeSocket, SD_SEND);

			return rc == NO_ERROR? sendData() : 0;
		}

		DWORD sendData() {
			long rc = 0;
			SOCKET scopeSocket;
			// Daten austauschen
			std::string data;
			while(rc != SOCKET_ERROR && !m_threadStop && (WSAGetLastError() != WSAEWOULDBLOCK)) {
				if(popQueue(m_queue, data, m_CSH)) {
					
					switch(m_mode) {
						case MODE_SERVER:
							printf("[%s] sendData scopeSocket = m_serverDataSocket\n", callFrom);
							scopeSocket = m_serverDataSocket;
							break;
						case MODE_CLIENT:
							printf("[%s] sendData scopeSocket = m_commonSocket\n", callFrom);
							scopeSocket = m_commonSocket;
							break;
						default:
							throw std::bad_alloc("invalid mode");
					};

					/*Another thread can be stopped and this trys to send data
					* to a dead connection.
					*/
					if(!m_threadStop){
						printf("[%s] Sending Data: %s\n", callFrom, data.c_str());
						rc = ::send(scopeSocket, data.c_str(), data.length(), 0);
					}

					if(WSAGetLastError() == WSAEWOULDBLOCK){
						printf("[%s] !!!!WSAEWOULDBLOCK!!!! \n", callFrom);
					}

					if(rc == SOCKET_ERROR) {
						break;
					}
				} else {
					// can be improved - u know: source http://msdn.microsoft.com/en-us/library/windows/desktop/ms686689(v=vs.85).aspx
					// some polling going on...
					Sleep(50);
				}
			}
			// All data left us, so need to shutdown the connection - till sending next time data.
			printf("[%s] shutdown m_commonSocket!\r\n", callFrom);
			

			switch(m_mode) {
				case MODE_SERVER:
					printf("[%s] shutdown scopeSocket = m_serverDataSocket\n", callFrom);
					rc = shutdown(m_serverDataSocket, SD_SEND);
					break;
				case MODE_CLIENT:
					printf("[%s] shutdown scopeSocket = m_commonSocket\n", callFrom);
					rc = shutdown(m_commonSocket, SD_SEND);
					break;
				default:
					throw std::bad_alloc("invalid mode");
			};

			rc = shutdown(scopeSocket, SD_SEND);

			return (rc == NO_ERROR) ? 0 : 1;
		}

		DWORD startServer(){
			long rc;
			SOCKADDR_IN addr;

			// Socket erstellen
			m_commonSocket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(m_commonSocket == INVALID_SOCKET) {
				printf("[%s] Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n", callFrom, WSAGetLastError());
				return 1;
			} else {
				printf("[%s] Socket erstellt!\n", callFrom);
			}

			// Socket binden
			memset(&addr, 0, sizeof(SOCKADDR_IN));
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = ADDR_ANY;
			rc = ::bind(m_commonSocket, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
			if(rc == SOCKET_ERROR) {
				printf("[%s] Fehler: bind, fehler code: %d\n", callFrom, WSAGetLastError());
				return 1;
			} else {
				printf("[%s] Socket an port gebunden ...\n", callFrom);
			}

			// In den listen Modus
			rc = ::listen(m_commonSocket, 10);
			if(rc == SOCKET_ERROR) {
				printf("[%s] Fehler: listen, fehler code: %d\n", callFrom, WSAGetLastError());
				return 1;
			} else {
				printf("[%s] listen Modus ...\n", callFrom);
			}

			// Max: now it may get a bit confusing with the socket names...
			// Basti: yeahh got this ;-)  - we shall use only one socket in this class ...

			/*
			* need improvement
			*
			/* Verbindung annehmen, wenn gefordert*/
			//m_serverDataSocket = ::accept(m_commonSocket, NULL, NULL);
			printf("[%s] Waiting for accepting connections ...\n", callFrom);

			// the implementation should handle more than one connect,
			// cause of network error or disconnected client
			// when not, u have to restart the whole simulation to start the server again
			m_serverDataSocket = accept(m_commonSocket, NULL, NULL);

			//// AcceptConnections /////////////////////////////////////////////////
			// Spins forever waiting for connections.  For each one that comes in, 
			// we create a thread to handle it and go back to waiting for
			// connections.  If an error occurs, we return.
			/*
			 while (1) {
				 sockaddr_in sinRemote;
					int nAddrSize = sizeof(sinRemote);

					while (1) {
						SOCKET sd = accept(ListeningSocket, (sockaddr*)&sinRemote,
								&nAddrSize);
						if (sd != INVALID_SOCKET) {
							cout << "Accepted connection from " <<
									inet_ntoa(sinRemote.sin_addr) << ":" <<
									ntohs(sinRemote.sin_port) << "." <<
									endl;

							DWORD nThreadID;
							CreateThread(0, 0, EchoHandler, (void*)sd, 0, &nThreadID);
						}
						else {
							cerr << WSAGetLastErrorMessage("accept() failed") << 
									endl;
							return;
						}
					}
				}
					
			*/

			if(m_serverDataSocket == INVALID_SOCKET) {
				printf("[%s] Fehler: accept, fehler code: %d\n", callFrom,WSAGetLastError());
				return 1;
			} else {
				printf("[%s] Neue Verbindung wurde akzeptiert ...\n", callFrom);
			}

			if(!startReceiveThread()) {
				printf("[%s] unable to start receiving thread", callFrom);
				return 1;
			}

			//if u want only a one shot connection, the socket can be shutdown here ...
			//shutdown(m_serverDataSocket, SD_BOTH);

			return sendData();
		}

		/*
		* note that the socket name is dependent on the view, if u start server or client
		*/
		DWORD startClient(){
			long rc;
			SOCKADDR_IN addr;

			// Socket erstellen
			m_commonSocket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(m_commonSocket == INVALID_SOCKET) {
				printf("[%s] Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n", callFrom, WSAGetLastError());
				return 1;
			} else {
				printf("[%s] Socket erstellt!\n", callFrom);
			}

			int iOptVal = 0;
			int iOptLen = sizeof (int);
			
			BOOL bOptVal = TRUE;
			int bOptLen = sizeof (BOOL);

			// source: http://msdn.microsoft.com/en-us/library/windows/desktop/ee470551(v=vs.85).aspx
			int iResult = setsockopt(m_commonSocket, SOL_SOCKET, SO_KEEPALIVE, (char *) &bOptVal, bOptLen);

			if (iResult == SOCKET_ERROR) {
				printf("[%s] setsockopt for SO_KEEPALIVE failed with error: %u\n", callFrom, WSAGetLastError());
			} else
				printf("[%s] Set SO_KEEPALIVE: ON\n", callFrom);

			iResult = getsockopt(m_commonSocket, SOL_SOCKET, SO_KEEPALIVE, (char *) &iOptVal, &iOptLen);
			if (iResult == SOCKET_ERROR) {
				printf("[%s] getsockopt for SO_KEEPALIVE failed with error: %u\n", callFrom, WSAGetLastError());
			} else
				printf("[%s] SO_KEEPALIVE Value: %ld\n", callFrom, iOptVal);

			// Verbinden
			memset(&addr, 0, sizeof(SOCKADDR_IN)); // zuerst alles auf 0 setzten
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = inet_addr(m_ip_client.c_str()); // zielrechner ist unser eigener

			do{
				rc = ::connect(m_commonSocket, (SOCKADDR*)&addr, sizeof(SOCKADDR));
				
				if(rc == SOCKET_ERROR) {
					printf("[%s] Warning: connect gescheitert, fehler code: %d, trying again ...\n", callFrom, WSAGetLastError());
					Sleep(100);

				} else {
					printf("[%s] verbunden mit Server ...\n", callFrom);
				}

			}while(rc == SOCKET_ERROR);
			

			// again that confusin socket stuff -> needs to be improved
			if(!startReceiveThread()) {
				printf("[%s] unable to start receiving thread", callFrom);
				return 1;
			}

			return sendData();
		}

	};

	CommunicationController::CommunicationController(CommunicationController::eMode mode, const std::string& ip_server, const std::string& ip_client, unsigned short port) : pimpl(new Impl(mode, ip_server, ip_client, port))
	{}

	CommunicationController::~CommunicationController(){
		delete pimpl;
	}

	/*
	* need improvement
	* http://tangentsoft.net/wskfaq/glossary.html#non-blocking
	*/
	bool CommunicationController::receive(std::string& data){
		return pimpl->popQueue(pimpl->m_queue, data, pimpl->m_CSH);
	}

	bool CommunicationController::send(const std::string& data){
		pimpl->pushQueue(pimpl->m_queue, data, pimpl->m_CSH);
		return true;
	}
};
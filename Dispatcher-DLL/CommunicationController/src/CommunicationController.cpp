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

			//need improvement, start thread only when data waiting in queue
			return m_thread && m_thread->start();
		}

		DWORD receiveData() {
			printf("[%s] starting receiveDataThread\n", callFrom);

			// Set the socket I/O mode: In this case FIONBIO
			// enables or disables the blocking mode for the 
			// socket based on the numerical value of iMode.
			// If iMode = 0, blocking is enabled; 
			// If iMode != 0, non-blocking mode is enabled.
			u_long iMode = 0;
			long rc = -1;
			char buf[DEFAULT_BUFLEN];

			
			while(rc != SOCKET_ERROR && rc != NO_ERROR) {
				if(!m_threadStop){

					switch(m_mode) {
						case MODE_SERVER:
							printf("[%s] receiveData m_serverDataSocket\n", callFrom);
							rc = ioctlsocket(m_serverDataSocket, FIONBIO, &iMode);
							if (rc != NO_ERROR){
								printf("[%s] failed set iMode on ioctlsocket with error: %ld\n", callFrom, WSAGetLastError());
								break;
							}
							rc = ::recv(m_serverDataSocket, buf, DEFAULT_BUFLEN, 0);
							if (WSAGetLastError() != NO_ERROR){
									printf("[%s] failed recv with error: %ld\n", callFrom, WSAGetLastError());
									break;
							}
							break;
						case MODE_CLIENT:
							printf("[%s] receiveData m_commonSocket\n", callFrom);
							rc = ioctlsocket(m_commonSocket, FIONBIO, &iMode);
							if (rc != NO_ERROR){
								printf("[%s] failed set iMode on ioctlsocket with error: %ld\n", callFrom, WSAGetLastError());
								break;
							}
							rc = ::recv(m_commonSocket, buf, DEFAULT_BUFLEN, 0);
								if (WSAGetLastError() != NO_ERROR){
									printf("[%s] failed recv with error: %ld\n", callFrom, WSAGetLastError());
									break;
							}
							break;
						default:
							throw std::bad_alloc("invalid mode");
					};
				}

				if(rc == NO_ERROR) {
					printf("[%s] Verbindung getrennt ...\n", callFrom);
					break;
				}

				// TODO: stitch buffer together until NULL byte received?
				//buf[rc]='\0';
				printf("[%s] buffer: %d\n", callFrom, buf);
				pushQueue(m_queue, std::string(buf), m_CSH);
			}

			return (rc != NO_ERROR? WSAGetLastError() : sendData());
		}

		DWORD sendData() {

			// Set the socket I/O mode: In this case FIONBIO
			// enables or disables the blocking mode for the 
			// socket based on the numerical value of iMode.
			// If iMode = 0, blocking is enabled; 
			// If iMode != 0, non-blocking mode is enabled.
			u_long iMode = 0;
			long rc = 0;

			// Daten austauschen
			std::string data;
			while(rc != SOCKET_ERROR && !m_threadStop && (WSAGetLastError() != WSAEWOULDBLOCK)) {
				if(popQueue(m_queue, data, m_CSH) ) {
					if(!m_threadStop){						
						switch(m_mode) {
							case MODE_SERVER:
								printf("[%s] Sending Data with m_serverDataSocket: %s\n", callFrom, data.c_str());
								rc = ::send(m_serverDataSocket, data.c_str(), data.length(), 0);

								break;
							case MODE_CLIENT:
								printf("[%s] Sending Data with m_commonSocket: %s\n", callFrom, data.c_str());
								rc = ::send(m_commonSocket, data.c_str(), data.length(), 0);
								break;
							default:
								throw std::bad_alloc("invalid mode");
						};

						if(WSAGetLastError() == WSAEWOULDBLOCK){
							printf("[%s] !!!!WSAEWOULDBLOCK!!!! \n", callFrom);
						}

						if(rc == SOCKET_ERROR) {
							break;
						}
					}
				} else {
					// can be improved - u know: source http://msdn.microsoft.com/en-us/library/windows/desktop/ms686689(v=vs.85).aspx
					// some polling going on...
					Sleep(50);
				}
			}

			switch(m_mode) {
				case MODE_SERVER:
					// shutdown our socket entirely
					printf("[%s] shutdown m_serverDataSocket!\r\n", callFrom);
					rc = shutdown(m_serverDataSocket, SD_SEND);

					// Close our socket entirely
					printf("[%s] close m_serverDataSocket!\r\n", callFrom);
					rc = closesocket(m_serverDataSocket);
					break;
				case MODE_CLIENT:
					// shutdown our socket entirely
					printf("[%s] shutdown m_commonSocket!\r\n", callFrom);
					rc = shutdown(m_commonSocket, SD_SEND);

					// Close our socket entirely
					printf("[%s] close m_commonSocket!\r\n", callFrom);
					rc = closesocket(m_commonSocket);
					break;
				default:
					throw std::bad_alloc("invalid mode");
			};

			return (rc != NO_ERROR? WSAGetLastError() : 0);
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
			if(rc != NO_ERROR) {
				printf("[%s] Fehler: bind, fehler code: %d\n", callFrom, WSAGetLastError());
				return 1;
			} else {
				printf("[%s] Socket an port gebunden ...\n", callFrom);
			}

			// In den listen Modus
			rc = ::listen(m_commonSocket, 10);
			if(rc != NO_ERROR) {
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

			return rc;
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

			return rc;
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
		bool iReturn = pimpl->popQueue(pimpl->m_queue, data, pimpl->m_CSH);
		printf("[%s] popQueue: %s\n", this->pimpl->callFrom, iReturn);
		return true;
	}

	bool CommunicationController::send(const std::string& data){
		pimpl->pushQueue(pimpl->m_queue, data, pimpl->m_CSH);
		printf("[%s] pushQueue: %s\n", this->pimpl->callFrom, data.c_str());
		return true;
	}
};
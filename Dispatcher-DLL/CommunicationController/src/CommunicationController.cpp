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
		SOCKET                 m_socket;
		SOCKET                 m_clientSocket;

		Impl(eMode mode, const std::string& host,  const std::string& client, unsigned short port)
			: m_mode(mode)
			, m_ip_server(host)
			, m_ip_client(client)
			, m_port(port)
			, m_queue()
			, m_CSH()
			, m_thread(NULL)
			, m_threadStop(false)
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
			if(m_socket != INVALID_SOCKET) {
				int m_socketResult = shutdown(m_socket, SD_BOTH);
				if (m_socketResult == SOCKET_ERROR) {
					printf("m_socket shutdown failed with error: %d\n", WSAGetLastError());
					closesocket(m_socket);
				}

				int m_clientSocketResult = shutdown(m_clientSocket, SD_BOTH);
				if (m_clientSocketResult == SOCKET_ERROR) {
					printf("m_clientSocketResult shutdown failed with error: %d\n", WSAGetLastError());
					closesocket(m_clientSocketResult);
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


			/*Another thread can be stopped and this trys to receive data from
			* a dead connection.
			*/
			printf("\n%s -> receive data:", callFrom);
			while(rc != SOCKET_ERROR && !m_threadStop) {
				printf("%s -> .\n", callFrom);
				if(!m_threadStop){
					// when call as Server then need to receive from m_clientSocket
					// when call as Client then need to receive from m_socket
					switch(m_mode) {
						case MODE_SERVER:
							rc = ::recv(m_clientSocket, buf, DEFAULT_BUFLEN, 0);
							break;
						case MODE_CLIENT:
							rc = ::recv(m_socket, buf, DEFAULT_BUFLEN, 0);
							break;
					};
				}

				if(rc == 0) {
					printf("%s -> Verbindung getrennt ...\n", callFrom);
					break;
				}

				if(rc == SOCKET_ERROR) {
					printf(" -> Fehler: recv, fehler code: %d\n", callFrom, WSAGetLastError());
					break;
				}

				// TODO: stitch buffer together until NULL byte received?
				buf[rc]='\0';
				printf("%s -> [Receive] %s\n", callFrom, buf);
				pushQueue(m_queue, std::string(buf), m_CSH);
			}
			return 0;
		}

		DWORD sendData() {
			long rc = 0;

			// Daten austauschen
			std::string data;
			while(rc != SOCKET_ERROR && !m_threadStop) {
				if(popQueue(m_queue, data, m_CSH)) {
					printf("[Sending] %s\n", data.c_str());

					/*Another thread can be stopped and this trys to send data
					* to a dead connection.
					*/
					if(!m_threadStop){

						do {
							if ((rc = ::send(m_socket, data.c_str(), data.length(), 0)) != SOCKET_ERROR) {
								break;
							}
						}
						while (WSAGetLastError() == WSAEWOULDBLOCK);
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
			return (rc == SOCKET_ERROR) ? 1 : 0;
		}

		DWORD startServer(){
			long rc;
			SOCKADDR_IN addr;

			// Socket erstellen
			m_socket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(m_socket == INVALID_SOCKET) {
				printf("Server -> Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Server -> Socket erstellt!\n");
			}

			// Socket binden
			memset(&addr, 0, sizeof(SOCKADDR_IN));
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = ADDR_ANY;
			rc = ::bind(m_socket, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
			if(rc == SOCKET_ERROR) {
				printf("Server -> Fehler: bind, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Server -> Socket an port gebunden ...\n");
			}

			// In den listen Modus
			rc = ::listen(m_socket, 10);
			if(rc == SOCKET_ERROR) {
				printf("Server -> Fehler: listen, fehler code: %d\n",WSAGetLastError());
				return 1;
			} else {
				printf("Server -> listen Modus ...\n");
			}

			// Max: now it may get a bit confusing with the socket names...
			// Basti: yeahh got this ;-)  - we shall use only one socket in this class ...

			/*
			* need improvement
			*
			/* Verbindung annehmen, wenn gefordert*/
			//m_clientSocket = ::accept(m_socket, NULL, NULL);
			printf("Server -> Waiting for connections ...\n");

			// the implementation should handle more than one connect,
			// cause of network error or disconnected client
			// when not, u have to restart the whole simulation to start the server again
			m_clientSocket = accept(m_socket, NULL, NULL);

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

			if(m_clientSocket == INVALID_SOCKET) {
				printf("Server -> Fehler: accept, fehler code: %d\n",WSAGetLastError());
				return 1;
			} else {
				printf("Server -> Neue Verbindung wurde akzeptiert ...\n");
			}

			if(!startReceiveThread()) {
				printf("Server -> unable to start receiving thread");
				return 1;
			}

			//if u want only a one shot connection, the socket can be shutdown here ...
			//shutdown(m_clientSocket, SD_BOTH);

			return sendData();
		}

		/*
		* note that the socket name is dependent on the view, if u start server or client
		*/
		DWORD startClient(){
			long rc;
			SOCKADDR_IN addr;

			// Socket erstellen
			m_socket = ::socket(AF_INET, SOCK_STREAM, 0);
			if(m_socket == INVALID_SOCKET) {
				printf("Client -> Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Client -> Socket erstellt!\n");
			}

			int iOptVal = 0;
			int iOptLen = sizeof (int);
			
			BOOL bOptVal = TRUE;
			int bOptLen = sizeof (BOOL);

			// source: http://msdn.microsoft.com/en-us/library/windows/desktop/ee470551(v=vs.85).aspx
			int iResult = setsockopt(m_socket, SOL_SOCKET, SO_KEEPALIVE, (char *) &bOptVal, bOptLen);
			if (iResult == SOCKET_ERROR) {
				printf("Client -> setsockopt for SO_KEEPALIVE failed with error: %u\n", WSAGetLastError());
			} else
				printf("Client -> Set SO_KEEPALIVE: ON\n");

			iResult = getsockopt(m_socket, SOL_SOCKET, SO_KEEPALIVE, (char *) &iOptVal, &iOptLen);
			if (iResult == SOCKET_ERROR) {
				printf("Client -> getsockopt for SO_KEEPALIVE failed with error: %u\n", WSAGetLastError());
			} else
				printf("Client -> SO_KEEPALIVE Value: %ld\n", iOptVal);

			// Verbinden
			memset(&addr, 0, sizeof(SOCKADDR_IN)); // zuerst alles auf 0 setzten
			addr.sin_family = AF_INET;
			addr.sin_port = m_port;
			addr.sin_addr.s_addr = inet_addr(m_ip_client.c_str()); // zielrechner ist unser eigener

			rc = ::connect(m_socket, (SOCKADDR*)&addr, sizeof(SOCKADDR));
			if(rc == SOCKET_ERROR) {
				m_socket = INVALID_SOCKET;
				printf("Client -> Fehler: connect gescheitert, fehler code: %d\n", WSAGetLastError());
				return 1;
			} else {
				printf("Client -> verbunden mit Server ...\n");
			}

			// again that confusin socket stuff -> needs to be improved
			// m_clientSocket = m_serverSocket;
			if(!startReceiveThread()) {
				printf("Client -> unable to start receiving thread");
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
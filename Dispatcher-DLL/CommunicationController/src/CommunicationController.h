#pragma once

#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>

#pragma comment(lib, "Ws2_32.lib")


class CommunicationController{

	static const USHORT DEFAULT_PORT = 27015;
	static const int DEFAULT_BUFLEN = 512;

	public:

		CommunicationController(void){}
		~CommunicationController(void){}
		
		struct initialize{
			static const char* DEFAULT_CLIENT_IP;			
			};

		int startWinsocket(void) {
			WSADATA wsa;
			return WSAStartup(MAKEWORD(2,0),&wsa);
		}

		DWORD startServer(){
			long rc;
			SOCKET acceptSocket;
			SOCKET connectedSocket;
			SOCKADDR_IN addr;

			char buf[DEFAULT_BUFLEN];

			// Winsock starten
			rc = startWinsocket();
			if(rc!=0) {
				printf("Fehler: startWinsock, fehler code: %d\n",rc);
				system("pause");
				return 1;
			}
			else {
				printf("Winsock gestartet!\n");
			}

			// Socket erstellen
			acceptSocket=socket(AF_INET,SOCK_STREAM,0);
			if(acceptSocket==INVALID_SOCKET) {
				printf("Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n",WSAGetLastError());
				system("pause");
				return 1;
			}
			else {
				printf("Socket erstellt!\n");
			}

			// Socket binden
			memset(&addr,0,sizeof(SOCKADDR_IN));
			addr.sin_family=AF_INET;
			addr.sin_port= DEFAULT_PORT;
			addr.sin_addr.s_addr=ADDR_ANY;
			rc=bind(acceptSocket,(SOCKADDR*)&addr,sizeof(SOCKADDR_IN));
			if(rc==SOCKET_ERROR) {
				printf("Fehler: bind, fehler code: %d\n",WSAGetLastError());
				system("pause");
				return 1;
			}
			else {
				printf("Socket an port gebunden\n");
			}

			// In den listen Modus
			rc=listen(acceptSocket,10);
			if(rc==SOCKET_ERROR) {
				printf("Fehler: listen, fehler code: %d\n",WSAGetLastError());
				system("pause");
				return 1;
			}
			else {
				printf("acceptSocket ist im listen Modus....\n");
			}

			// Verbindung annehmen
			connectedSocket = accept(acceptSocket,NULL,NULL);
			if(connectedSocket==INVALID_SOCKET) {
				printf("Fehler: accept, fehler code: %d\n",WSAGetLastError());
				system("pause");
				return 1;
			}
			else {
				printf("Neue Verbindung wurde akzeptiert!\n");
			}

			// Daten austauschen
			while(rc!=SOCKET_ERROR) {
				rc=recv(connectedSocket,buf, DEFAULT_BUFLEN,0);
				if(rc==0) {
					printf("Server hat die Verbindung getrennt..\n");
					break;
				}
				if(rc==SOCKET_ERROR) {
					printf("Fehler: recv, fehler code: %d\n",WSAGetLastError());
					break;
				}
				buf[rc]='\0';
				printf("Client sendet: %s\n",buf);
				rc = send(connectedSocket,buf,strlen(buf),0);
			}
			closesocket(acceptSocket);
			closesocket(connectedSocket);
			WSACleanup();
  
			system("pause");
			return 0;
		}

		DWORD startClient(){
			long rc;
			SOCKET s;
			SOCKADDR_IN addr;
			char buf[DEFAULT_BUFLEN];

			// Winsock starten
			rc = startWinsocket();
			if(rc!=0) {
				printf("Fehler: startWinsock, fehler code: %d\n",rc);
				system("pause");
				return 1;
			}
			else {
			printf("Winsock gestartet!\n");
			}

			// Socket erstellen
			s=socket(AF_INET,SOCK_STREAM,0);
			if(s==INVALID_SOCKET) {
				printf("Fehler: Der Socket konnte nicht erstellt werden, fehler code: %d\n",WSAGetLastError());
				system("pause");
				return 1;
			}
			else {
				printf("Socket erstellt!\n");
			}

			// Verbinden
			memset(&addr,0,sizeof(SOCKADDR_IN)); // zuerst alles auf 0 setzten
			addr.sin_family=AF_INET;
			addr.sin_port= DEFAULT_PORT;
			addr.sin_addr.s_addr=inet_addr(initialize::DEFAULT_CLIENT_IP); // zielrechner ist unser eigener

			rc=connect(s,(SOCKADDR*)&addr,sizeof(SOCKADDR));
			if(rc==SOCKET_ERROR) {
				printf("Fehler: connect gescheitert, fehler code: %d\n",WSAGetLastError());
				system("pause");
				return 1;
			}
			else {
				printf("Client ist verbunden mit Server..\n");
			}


			// Daten austauschen
			while(rc!=SOCKET_ERROR) {
				printf("\nZeichenfolge eingeben [max DEFAULT_BUFLEN]: ");
				gets_s(buf);
				send(s,buf,strlen(buf),0);
				rc=recv(s,buf, DEFAULT_BUFLEN,0);
				if(rc==0) {
					printf("Server hat die Verbindung getrennt..\n");
					break;
				}
				if(rc==SOCKET_ERROR) {
					printf("Fehler: recv, fehler code: %d\n",WSAGetLastError());
					break;
				}

				buf[rc]='\0';
				printf("\nServer empfaengt: %s\n",buf);
			}

			closesocket(s);
			WSACleanup();

			system("pause");
			return 0;
		}

};
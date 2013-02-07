#pragma once

#include <winsock2.h>

#pragma comment(lib, "Ws2_32.lib")

class CommunicationController
{
	public:
		CommunicationController(void);
		~CommunicationController(void);
		int startServer(void);
		int startClient(void);
		int WsaServerInit(char *ip, char *port);
		int WsaClientInit(char *ip, char *port);
		int sendInitBuffer(SOCKET connectSocket, char *sendBuf);
		int setClientSocket(SOCKET ListenSocket);
		int receiveDataLoop(SOCKET clientSocket);
		int connectServer(SOCKET ConnectSocket);
		SOCKET createListenSocket(void);
		int bindSocket(SOCKET ListenSocket);
		SOCKET receiveOnClientSocket(SOCKET ListenSocket);
		int shutDownSocket(SOCKET ClientSocket);
};


#pragma once

#include "stdafx.h"
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>

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


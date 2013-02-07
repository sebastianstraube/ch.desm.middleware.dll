#include "stdafx.h"

/**
* 
**/
#ifndef WIN32_LEAN_AND_MEAN
	#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>

#include "CommunicationController.h"

#define DEFAULT_SERVER_IP "0.0.0.0"
#define DEFAULT_CLIENT_IP "127.0.0.0"
#define DEFAULT_PORT "27015"
#define DEFAULT_BUFLEN 512

/**
static const std::string DEFAULT_SERVER_IP = "127.0.0.1";
static const char* DEFAULT_SERVER_IP = "127.0.0.1";
**/

struct addrinfo *result = NULL, *ptr = NULL, hints;

CommunicationController::CommunicationController(){

}

CommunicationController::~CommunicationController(){

}

int CommunicationController::startServer(){
	
	//
	int iResult = WsaServerInit(DEFAULT_SERVER_IP, DEFAULT_PORT);

	//
	SOCKET listenSocket = createListenSocket();
	
	//
	iResult = bindSocket(listenSocket);

	//
	SOCKET clientSocket = receiveOnClientSocket(listenSocket);
	
	//
	iResult = shutDownSocket(clientSocket);

	//
	return iResult;
}

int CommunicationController::startClient(){
    SOCKET connectSocket = INVALID_SOCKET;
	//
	int iResult = WsaClientInit(DEFAULT_CLIENT_IP, DEFAULT_PORT);

	//
	iResult = connectServer(connectSocket);
	
	//remove struct in memory
    freeaddrinfo(result);

	//
	char *sendBuf = "This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! This is a test with the client! ";
	sendInitBuffer(connectSocket, sendBuf);

    // shutdown the connection since no more data will be sent
    iResult = shutDownSocket(connectSocket);

	//
	iResult = receiveDataLoop(connectSocket);

    return iResult;
}

int CommunicationController::WsaServerInit(char *ip, char *port){
	
	WSADATA wsaData;
	
	int iResult;

	// Initialize Winsock
	iResult = WSAStartup(MAKEWORD(2,2), &wsaData);
	if (iResult != 0) {
		printf("WSAStartup failed: %d\n", iResult);
		getchar();
		return 1;
		}

	ZeroMemory(&hints, sizeof (hints));
	hints.ai_family = AF_INET;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;
	hints.ai_flags = AI_PASSIVE;

	// Resolve the local address and port to be used by the server
	iResult = getaddrinfo(ip, port, &hints, &result);
	if (iResult != 0) {
		printf("getaddrinfo failed: %d\n", iResult);
		WSACleanup();

		getchar();
		return 1;
	}

return iResult;
}

int CommunicationController::WsaClientInit(char *ip, char *port){
	WSADATA wsaData;

	// Initialize Winsock
    int iResult = WSAStartup(MAKEWORD(2,2), &wsaData);
    if (iResult != 0) {
        printf("WSAStartup failed with error: %d\n", iResult);
		getchar();
        return 1;
    }

    ZeroMemory( &hints, sizeof(hints) );
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;

    // Resolve the server address and port
    iResult = getaddrinfo(ip, port, &hints, &result);
    if ( iResult != 0 ) {
        printf("getaddrinfo failed with error: %d\n", iResult);
        WSACleanup();
		getchar();
        return 1;
    }

	return iResult;
}

int CommunicationController::sendInitBuffer(SOCKET connectSocket, char *sendBuf){

    // Send an initial buffer
    int iResult = send( connectSocket, sendBuf, (int)strlen(sendBuf), 0 );
    if (iResult == SOCKET_ERROR) {
        printf("send failed with error: %d\n", WSAGetLastError());
        closesocket(connectSocket);
        WSACleanup();
		getchar();
        return 1;
    }

    printf("Bytes Sent: %ld\n", iResult);

	return iResult;
}

int CommunicationController::setClientSocket(SOCKET ListenSocket){

	SOCKET ClientSocket;
	ClientSocket = INVALID_SOCKET;

	// Accept a client socket
	ClientSocket = accept(ListenSocket, NULL, NULL);
	if (ClientSocket == INVALID_SOCKET) {
		printf("accept failed: %d\n", WSAGetLastError());
		closesocket(ListenSocket);
		WSACleanup();

		getchar();
		return 1;
	}

	return 0;
}

int CommunicationController::receiveDataLoop(SOCKET clientSocket){

	// Receive until the peer shuts down the connection
	char recvbuf[DEFAULT_BUFLEN];
	int recvbuflen = DEFAULT_BUFLEN;
	int iResult = 0;

	do {

		int iResult = recv(clientSocket, recvbuf, recvbuflen, 0);

		if (iResult > 0) {
			printf("Bytes received: %d\n", iResult);

			// Echo the buffer back to the sender
			int iSendResult = send(clientSocket, recvbuf, iResult, 0);
			if (iSendResult == SOCKET_ERROR) {
				printf("send failed: %d\n", WSAGetLastError());
				closesocket(clientSocket);
				WSACleanup();

				getchar();
				return 1;
			}
			printf("Bytes sent: %d\n", iSendResult);
		} else if (iResult == 0)
			printf("Connection closing...\n");
		else {
			printf("recv failed: %d\n", WSAGetLastError());
			closesocket(clientSocket);
			WSACleanup();

			getchar();
			return 1;
		}

		Sleep(3000);

	} while (iResult > 0);

	return iResult;
}

int CommunicationController::connectServer(SOCKET ConnectSocket){

	int iResult = 0;

    // Attempt to connect to an address until one succeeds
    for(ptr=result; ptr != NULL ;ptr=ptr->ai_next) {

        // Create a SOCKET for connecting to server
        ConnectSocket = socket(ptr->ai_family, ptr->ai_socktype, 
            ptr->ai_protocol);
        if (ConnectSocket == INVALID_SOCKET) {
            printf("socket failed with error: %ld\n", WSAGetLastError());
            WSACleanup();
			getchar();
            return 1;
        }

        // Connect to server.
        iResult = connect( ConnectSocket, ptr->ai_addr, (int)ptr->ai_addrlen);
        if (iResult == SOCKET_ERROR) {
            closesocket(ConnectSocket);
            ConnectSocket = INVALID_SOCKET;
            continue;
        }
        break;
    }

	if (ConnectSocket == INVALID_SOCKET) {
        printf("Unable to connect to server!\n");
        WSACleanup();
		getchar();
        return 1;
    }

	return iResult;
}

SOCKET CommunicationController::createListenSocket(){
	
	SOCKET ListenSocket = INVALID_SOCKET;

	ListenSocket = socket(result->ai_family, result->ai_socktype, result->ai_protocol);

	if (ListenSocket == INVALID_SOCKET) {
		printf("Error at socket(): %ld\n", WSAGetLastError());
		freeaddrinfo(result);
		WSACleanup();

		getchar();
		return 1;
	}

return ListenSocket;
}

int CommunicationController::bindSocket(SOCKET ListenSocket){

	// Setup the TCP listening socket
    int iResult = bind( ListenSocket, result->ai_addr, (int)result->ai_addrlen);
    if (iResult == SOCKET_ERROR) {
        printf("bind failed with error: %d\n", WSAGetLastError());
        freeaddrinfo(result);
        closesocket(ListenSocket);
        WSACleanup();

		getchar();
        return 1;
    }

	freeaddrinfo(result);

	if ( listen( ListenSocket, SOMAXCONN ) == SOCKET_ERROR ) {
    printf( "Listen failed with error: %ld\n", WSAGetLastError() );
    closesocket(ListenSocket);
    WSACleanup();

	getchar();
    return 1;
	}

	return iResult;
}

SOCKET CommunicationController::receiveOnClientSocket(SOCKET ListenSocket){

	SOCKET clientSocket = INVALID_SOCKET;
	int iResult = 0;

	// Accept a client socket
	clientSocket = accept(ListenSocket, NULL, NULL);
	if (clientSocket == INVALID_SOCKET) {
		printf("accept failed: %d\n", WSAGetLastError());
		closesocket(ListenSocket);
		WSACleanup();

		getchar();
		return 1;
	}

	char recvbuf[DEFAULT_BUFLEN];
	int recvbuflen = DEFAULT_BUFLEN;
	int iSendResult;
	

	// Receive until the peer shuts down the connection
	do {

		iResult = recv(clientSocket, recvbuf, recvbuflen, 0);

		if (iResult > 0) {
			printf("Bytes received: %d\n", iResult);

			// Echo the buffer back to the sender
			iSendResult = send(clientSocket, recvbuf, iResult, 0);
			if (iSendResult == SOCKET_ERROR) {
				printf("send failed: %d\n", WSAGetLastError());
				closesocket(clientSocket);
				WSACleanup();

				getchar();
				return 1;
			}
			printf("Bytes sent: %d\n", iSendResult);
		} else if (iResult == 0)
			printf("Connection closing...\n");
		else {
			printf("recv failed: %d\n", WSAGetLastError());
			closesocket(clientSocket);
			WSACleanup();

			getchar();
			return 1;
		}

		Sleep(3000);

	} while (iResult > 0);

	return clientSocket;
}

int CommunicationController::shutDownSocket(SOCKET ClientSocket){
	// shutdown the send half of the connection since no more data will be sent
	int iResult = shutdown(ClientSocket, SD_SEND);
	if (iResult == SOCKET_ERROR) {
		printf("shutdown failed: %d\n", WSAGetLastError());
		closesocket(ClientSocket);
		WSACleanup();

		getchar();
		return 1;
	}

	// cleanup
	closesocket(ClientSocket);
	WSACleanup();

	getchar();

	return iResult;
}

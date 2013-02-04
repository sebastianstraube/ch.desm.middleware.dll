// Winsocket.cpp : Defines the entry point for the console application.

#include "Winsocket.h"
#include "CommunicationController.h"

int _tmain(int argc, _TCHAR* argv[]){

	CommunicationController c;

	return c.startServer();
}
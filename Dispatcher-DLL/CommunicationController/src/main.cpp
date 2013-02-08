#include "stdafx.h"
#include <Windows.h> // Sleep()
#include "CommunicationController.h"

static const USHORT DEFAULT_PORT = 27015;

using namespace desm;

int _tmain(int argc, _TCHAR* argv[]) {
	std::string data;
	try {
		CommunicationController *cc_server = new CommunicationController(CommunicationController::MODE_SERVER, "127.0.0.1", 27017);
		CommunicationController *cc_client = new CommunicationController(CommunicationController::MODE_CLIENT, "127.0.0.1", 27017);
		// send a string once - will be bounced back and forth in the loop
		cc_client->send("muh");
		for(int i = 0; i < 5; ++i) {
			// server send+receiver
			while(cc_server->receive(data)) {
				std::cout << "server got data: " << data << std::endl;
				cc_server->send(data + "[server]");
			}
			// client send+receiver
			while(cc_client->receive(data)) {
				std::cout << "client got data: " << data << std::endl;
				cc_client->send(data + "[client]");
			}
			Sleep(500);
		}
		std::cout << "shutting down client" << std::endl;
		delete cc_client;
		std::cout << "shutting down server" << std::endl;
		delete cc_server;
	} catch(const std::bad_alloc& e) {
		std::cerr << "EXCEPTION: " << e.what() << std::endl;
		return 1;
	} catch(...) {
		std::cerr << "UNKNOWN EXCEPTION!" << std::endl;
		return 1;
	}

	system("pause");
    return 0;
}
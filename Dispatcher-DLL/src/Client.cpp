#include <Windows.h> // Sleep()
#include <iostream>
#include "CommunicationController.h"

static const USHORT DEFAULT_PORT = 27015;

using namespace desm;

int main(int argc, char** argv) {
	std::string data;
	try {
		CommunicationController *cc_client = new CommunicationController(CommunicationController::MODE_CLIENT, "127.0.0.1", 27017);
		int receivedMessages = 0;
		for(int i = 0; i < 10; ++i) {
			cc_client->send("Hello");
			Sleep(500);
			while(cc_client->receive(data)) {
				receivedMessages++;
				std::cout << "client got data: " << data << std::endl;
			}
		}
		std::cout << "received messages: " << receivedMessages << std::endl;
		std::cout << "shutting down client" << std::endl;
		delete cc_client;
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
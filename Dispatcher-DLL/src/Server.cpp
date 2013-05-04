#include <Windows.h> // Sleep()
#include <iostream>
#include "CommunicationController.h"

static const USHORT DEFAULT_PORT = 27015;

using namespace desm;

int main(int argc, char** argv) {
	std::string data;
	try {
		CommunicationController *cc = new CommunicationController(CommunicationController::MODE_SERVER, "127.0.0.1", 27017);
		while(true) {
			while(cc->receive(data)) {
				std::cout << "server got data: " << data << std::endl;
				cc->send(data + " World");
			}
			Sleep(50);
		}
		/*std::cout << "shutting down server" << std::endl;
		delete cc;*/
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
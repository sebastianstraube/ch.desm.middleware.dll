#include "stdafx.h"
#include <Windows.h> // Sleep()
#include "CommunicationController.h"

static const USHORT DEFAULT_PORT = 27015;

int _tmain(int argc, _TCHAR* argv[]) {
	try {
		CommunicationController cc_server(CommunicationController::MODE_SERVER, "127.0.0.1", 27017);
		CommunicationController cc_client(CommunicationController::MODE_CLIENT, "127.0.0.1", 27017);
		while(true) {
			cc_server.send("hallo client");
			Sleep(1000);
			std::string data;
			while(cc_client.receive(data)) {
				std::cout << "client got data: " << data << std::endl;
			}
		}
		// CommunicationController lifetime ends here
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
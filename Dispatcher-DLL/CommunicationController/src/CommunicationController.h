#pragma once

#include <winsock2.h>

#pragma comment(lib, "Ws2_32.lib")

class CommunicationController {
	private:
		int startWinsocket(void);

	public:
		CommunicationController(void);
		~CommunicationController(void);
		int startServer(void);
		int startClient(void);
};


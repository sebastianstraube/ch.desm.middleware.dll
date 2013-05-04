#include <Windows.h> // Sleep()
#include <iostream>
#include "MwDll.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		MwDll dll(L"LocsimDesmMiddlewarePlugin.dll");
		dll.stw_onStartProgramm("server");

		std::cout << "doing some stuff..." << std::endl;
		Sleep(1000);

		for(int i = 0; i < 30; ++i) {
			int kilometerDirection = 0;
			dll.stw_getKilometerDirection(kilometerDirection);
			std::cout << "kilometerDirection = " << kilometerDirection << std::endl;
			Sleep(200);
		}

		dll.stw_onStopProgramm();

	} catch(std::exception& e) {
		std::cerr << "EXCEPTION: " << e.what() << std::endl;
		return 1;
	} catch(...) {
		std::cerr << "UNKNOWN EXCEPTION!" << std::endl;
		return 1;
	}

	system("pause");
    return 0;
}
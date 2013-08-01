#include <Windows.h> // Sleep()
#include <iostream>
#include "MwDll.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		MwDll dll(L"LocsimDesmMiddlewarePlugin.dll");
		dll.onStartProgramm("locsim.json");

		std::cout << "doing some stuff..." << std::endl;
		Sleep(1000);
		std::cout << "setting Kilometer Distance to " << 0 << std::endl;
		dll.setKilometerDirection(0);
		Sleep(1000);
		std::cout << "setting Kilometer Distance to " << 1 << std::endl;
		dll.setKilometerDirection(1);
		Sleep(1000);
		std::cout << "setting Kilometer Distance to " << 2 << std::endl;
		dll.setKilometerDirection(2);
		Sleep(1000);

		dll.onStopProgramm();

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
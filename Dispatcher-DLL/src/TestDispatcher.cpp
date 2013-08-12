#include <Windows.h> // Sleep()
#include <iostream>
#include "Events.h"
#include "MwDll.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		MwDll dll(L"LocsimDesmMiddlewarePlugin.dll");
		dll.onStartProgramm("dispatcher.json");

		std::cout << "doing some stuff..." << std::endl;
		Sleep(1000);

		while(true) {
			std::vector<int> types;
			std::vector<int> ids;
			dll.getEvents(types, ids);
			for(size_t i = 0; i < types.size(); ++i) {
				switch(types[i]) {
				case EVT_KILOMETER_DIRECTION:
					int kmDir;
					dll.getKilometerDirection(kmDir);
					std::cout << "kilemeter direction: " << kmDir << std::endl;
					break;
				default:
					std::cout << "event " << types[i] << ": " << ids[i] << std::endl;
					break;
				}

			}
		}
		
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
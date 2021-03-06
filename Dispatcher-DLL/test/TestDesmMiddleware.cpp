#include <algorithm>
#include <iostream>
#include <iterator>
#include <string>
#include <Windows.h> // Sleep()

#include "Desm.h"
#include "MwDll.h"
#include "TestFunctionsMiddleware.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		MwDll dll(L"DesmMiddlewarePlugin.dll");
		TestFunctionsMiddleware dispatcher(dll);

		dll.onStartProgramm("dispatcher.json");
				
		std::cout << "role is: DESM_MIDDLEWARE" << std::endl;
		Sleep(1000);

		while(true) {
			std::vector<int> types;
			std::vector<std::vector<int>> paramList;
			dll.getEvents(types, paramList);
			for(size_t i = 0; i < types.size(); ++i) {
				switch(types[i]) {
				case ENUM_CMD_KILOMETER_DIRECTION:
					dispatcher.testGetKilometerDirection();
					break;
				case ENUM_CMD_BALISE:
					dispatcher.testGetBalise();
					break;
				case ENUM_CMD_ISOLIERSTOSS:
					dispatcher.testGetIsolierstoss();
					break;
				case ENUM_CMD_LOOP:
					dispatcher.testGetLoop();
					break;
				case ENUM_CMD_SIGNAL:
					dispatcher.testGetSignal();
					break;
				case ENUM_CMD_TRAINPOSITION:
					dispatcher.testGetTrainPosition();
					break;
				case ENUM_CMD_WEICHE:
					dispatcher.testGetWeiche();
					break;
				case ENUM_CMD_TRACK_CONNECTION:
					dispatcher.testGetTrackConnection();
					break;
				case ENUM_CMD_TRACK:
					dispatcher.testGetTrack();
					break;
				default:
					std::cout << "default command [type: " << types[i] << "]" << std::endl;
					break;
				}
			}
		}

		dll.onStopProgramm();

	} catch(std::exception& e) {
		std::cerr << "EXCEPTION: " << e.what() << std::endl;
		system("pause");
		return 1;
	} catch(...) {
		std::cerr << "UNKNOWN EXCEPTION!" << std::endl;
		system("pause");
		return 1;
	}

	system("pause");
	return 0;
}

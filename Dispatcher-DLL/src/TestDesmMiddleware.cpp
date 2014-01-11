#include <algorithm>
#include <iostream>
#include <iterator>
#include <string>
#include <Windows.h> // Sleep()
#include "Events.h"
#include "MwDll.h"
#include "Middleware.h"
#include "TestFunctionsMiddleware.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		TestFunctionsMiddleware dispatcher;
		MwDll dll(L"DesmMiddlewarePlugin.dll");
		dll.onStartProgramm("dispatcher.json");

		std::cout << "role is: DESM_MIDDLEWARE" << std::endl;
		Sleep(1000);

		while(true) {
			std::vector<int> types;
			std::vector<int> ids;
			dll.getEvents(types, ids);
			for(size_t i = 0; i < types.size(); ++i) {
				switch(types[i]) {
				case ENUM_CMD_KILOMETER_DIRECTION:
					dispatcher.testGetKilometerDirection(dll);
					break;
				case ENUM_CMD_BALISE:
					//dispatcher.testGetBalise(dll);
					break;
				case ENUM_CMD_ISOLIERSTOSS:
					//dispatcher.testGetIsolierstoss(dll);
					break;
				case ENUM_CMD_LOOP:
					//dispatcher.testGetLoop(dll);
					break;
				case ENUM_CMD_SIGNAL:
					//dispatcher.testGetSignal(dll);
					break;
				case ENUM_CMD_TRAINPOSITION:
					//dispatcher.testGetTrainPosition(dll);
					break;
				case ENUM_CMD_WEICHE:
					//dispatcher.testGetWeiche(dll);
					break;
				case ENUM_CMD_TRACK_CONNECTION:
					//dispatcher.testGetTrackConnection(dll);
					break;
				case ENUM_CMD_TRACK:
					//dispatcher.testGetTrack(dll);
					break;
				default:
					std::cout << "default command [type: " << types[i] << " id: " << ids[i] << "]" << std::endl;
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

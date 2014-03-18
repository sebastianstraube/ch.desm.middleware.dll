#include <Windows.h> // Sleep()
#include <iostream>
#include <iterator>
#include <string>
#include "MwDll.h"
#include "TestFunctionsMiddleware.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		MwDll dll(L"DesmMiddlewarePlugin.dll");
		TestFunctionsMiddleware locsim(dll);

		dll.onStartProgramm("locsim.json");

		std::cout << "role is: LOCSIM" << std::endl;
		Sleep(1000);
		std::cout << "starting tests ..." << std::endl;

		//start tests
		locsim.testInfoVersion();
		Sleep(500);
		locsim.testInfoName();
		Sleep(500);
		locsim.testInfoDescription();
		Sleep(500);
		locsim.testInfoConnectionStatus();
		Sleep(500);
		locsim.testSetKilometerDirection();
		Sleep(500);
		locsim.testSetBalise();
		Sleep(500);
		locsim.testSetIsolierstoss();
		Sleep(500);
		locsim.testSetLoop();
		Sleep(500);
		locsim.testSetSignal();
		Sleep(500);
		locsim.testSetTrainPosition();
		Sleep(500);
		locsim.testSetWeiche();
		Sleep(500);
		locsim.testSetTrackConnection();
		Sleep(500);
		locsim.testSetTrack();
		Sleep(500);
		//end tests

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
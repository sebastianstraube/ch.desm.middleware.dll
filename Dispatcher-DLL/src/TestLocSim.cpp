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
		locsim.testSetKilometerDirection();
		//TODO: locsim.testSetBalise();
		locsim.testSetIsolierstoss();
		locsim.testSetLoop();
		locsim.testSetSignal();
		locsim.testSetTrainPosition();
		locsim.testSetWeiche();
		locsim.testSetTrackConnection();
		locsim.testSetTrack();
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
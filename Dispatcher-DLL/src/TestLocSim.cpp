#include <Windows.h> // Sleep()
#include <iostream>
#include <iterator>
#include <string>
#include "MwDll.h"
#include "TestDesmMiddleware.h"

using namespace desm;

int main(int argc, char** argv) {
	try {
		//TestFunctionsMiddleware locsim;
		MwDll dll(L"DesmMiddlewarePlugin.dll");

		dll.onStartProgramm("locsim.json");

		std::cout << "role is: LOCSIM" << std::endl;
		Sleep(1000);
		std::cout << "starting tests ..." << std::endl;

		/*
		//start tests
		locsim.testSetKilometerDirection(dll);
		//TODO: locsim.testSetBalise(dll);
		locsim.testSetIsolierstoss(dll);
		locsim.testSetLoop(dll);
		locsim.testSetSignal(dll);
		locsim.testSetTrainPosition(dll);
		locsim.testSetWeiche(dll);
		locsim.testSetTrackConnection(dll);
		locsim.testSetTrack(dll);
		//end tests
		*/
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
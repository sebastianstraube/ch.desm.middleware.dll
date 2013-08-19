#include <Windows.h> // Sleep()
#include <iostream>
#include <iterator>
#include <string>
#include "MwDll.h"

using namespace desm;

void testTrainPosition(){

}

int main(int argc, char** argv) {
	try {
		MwDll dll(L"DesmMiddlewarePlugin.dll");
		dll.onStartProgramm("locsim.json");

		std::cout << "role is set to: LOCSIM" << std::endl;
		Sleep(1000);
		
		std::cout << "test[kilometerDirection] with parameter " << 0 << std::endl;
			dll.setKilometerDirection(0);
		Sleep(1000);
		
		{
			std::cout << "test[trainPosition] with parameter " << std::endl;
			int trainTyp = 1;
			int direction = 0;
			std::vector<double> positionList;
			positionList.push_back(1);
			positionList.push_back(2);
			positionList.push_back(3);
			positionList.push_back(4);
			std::vector<int> gleisList;
			gleisList.push_back(1);
			gleisList.push_back(9);
			gleisList.push_back(6);
			gleisList.push_back(4);
			gleisList.push_back(0);
			std::cout << "train typ: " << trainTyp << std::endl;
			std::cout << "direction: " << direction << std::endl;
			std::cout << "positions: ";
			std::copy(positionList.begin(), positionList.end(), std::ostream_iterator<double>(std::cout, " "));
			std::cout << std::endl;
			std::cout << "gleise: ";
			std::copy(gleisList.begin(), gleisList.end(), std::ostream_iterator<int>(std::cout, " "));
			std::cout << std::endl;
			dll.setTrainPosition(trainTyp, direction, positionList, gleisList);
			Sleep(1000);
		}

		std::cout << "test[signal] with parameter " << std::endl;
			int signalId = 324;
			int gleisId = 81734;
			double position = 23.123;
			int typ = 1;
			double hoehe = 15;
			double distanz = 120;
			std::string name = "Signal1";
			int direction = 1;
			std::cout << "signalId: " << signalId << std::endl;
			std::cout << "gleisId: " << gleisId << std::endl;
			std::cout << "typ: " << typ << std::endl;
			std::cout << "hoehe: " << hoehe << std::endl;
			std::cout << "distanz: " << distanz << std::endl;
			std::cout << "name: " << name << std::endl;
			std::cout << "direction: " << direction << std::endl;
			dll.setSignal(signalId, gleisId, position, typ, hoehe, distanz, name, direction);
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
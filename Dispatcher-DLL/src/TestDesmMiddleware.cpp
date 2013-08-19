#include <Windows.h> // Sleep()
#include <algorithm>
#include <iostream>
#include <iterator>
#include <string>
#include "Events.h"
#include "MwDll.h"
#include "TestDesmMiddleware.h"

using namespace desm;


//DOKU: OK
bool testKilometerDirection(MwDll& dll){
	int richtung;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testKilometerDirection" << std::endl;
	dll.getKilometerDirection(richtung);
	std::cout << richtung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
}

//DOKU: OK
bool testBalise(MwDll& dll){
	int baliseId = 42; // TODO was there a setter for this id before?
	int stellung;
	std::string protokoll;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testBalise" << std::endl;
	dll.getBalise(baliseId, stellung, protokoll);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung:" << stellung << std::endl;
	//TODO: String conversion
	std::cout << "protokoll:" << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

//DOKU: OK
bool testIsolierstoss(MwDll& dll){
	int baliseId = 42; // TODO was there a setter for this id before?
	int stellung;
	double position;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testIsolierstoss" << std::endl;
	dll.getIsolierstoss(baliseId, stellung, position);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung:" << stellung << std::endl;
	std::cout << "position:" << position << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

//DOKU: OK
bool testLoop(MwDll& dll){
	int baliseId = 42; // TODO was there a setter for this id before?
	int stellung;
	std::string protokoll;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testLoop" << std::endl;
	dll.getLoop(baliseId, stellung, protokoll);
	std::cout << "stellung:" << stellung << std::endl;
	std::cout << "protokoll:" << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
}

//DOKU: OK
bool testSignal(MwDll& dll){
	int signalId = 324; // TODO was there a setter for this id before?
	int stellung;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSignal" << std::endl;
	dll.getSignal(signalId, stellung);
	std::cout << "signalId:" << signalId << std::endl;
	std::cout << "stellung:" << stellung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

//DOKU: OK
bool testTrackConnection(MwDll& dll){
	int gleisId = 42; // TODO was there a setter for this id before?
	int gleis1;
	int gleis2;
	double von;
	double bis;
	std::string name;
	int weiche1Id;
	int weiche2Id;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testTrackConnection" << std::endl;
	dll.getTrackConnection(gleisId, gleis1, gleis2, von, bis, name, weiche1Id, weiche2Id);
	std::cout << "gleisId:" << gleisId << std::endl;
	std::cout << "gleis1:" << gleis1 << std::endl;
	std::cout << "gleis2:" << gleis2 << std::endl;
	std::cout << "von:" << von << std::endl;
	std::cout << "bis:" << bis << std::endl;
	std::cout << "name:" << name << std::endl;
	std::cout << "weiche1Id:" << weiche1Id << std::endl;
	std::cout << "weiche2Id:" << weiche2Id << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

//DOKU: OK
bool testTrainPosition(MwDll& dll){
	int trainTyp;
	int direction;
	std::vector<double> positionList;
	std::vector<int> gleisList;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testTrainPosition" << std::endl;
	dll.getTrainPosition(trainTyp, direction, positionList, gleisList);
	std::cout << "trainTyp: " << trainTyp << std::endl;
	std::cout << "direction: " << direction << std::endl;
	std::cout << "positions: ";
	std::copy(positionList.begin(), positionList.end(), std::ostream_iterator<double>(std::cout, " "));
	std::cout << std::endl;
	std::cout << "gleise: ";
	std::copy(gleisList.begin(), gleisList.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

//DOKU: OK
bool testWeiche(MwDll& dll){
	int weicheId = 42; // TODO was there a setter for this id before?
	int gleisId;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testWeiche" << std::endl;
	dll.getWeiche(weicheId, gleisId);
	std::cout << "weicheId:" << weicheId << std::endl;
	std::cout << "gleisId:" << gleisId << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

//DOKU: OK
bool testTrack(MwDll& dll){
	int gleisId = 42; // TODO was there a setter for this id before?
	double von;
	double bis;
	double abstand;
	std::string name;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testTrack" << std::endl;
	dll.getTrack(gleisId, von, bis, abstand, name);
	std::cout << "gleisId:" << gleisId << std::endl;
	std::cout << "von:" << von << std::endl;
	std::cout << "bis:" << bis << std::endl;
	std::cout << "abstand:" << abstand << std::endl;
	std::cout << "name:" << name << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}


int main(int argc, char** argv) {
	try {
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
					testKilometerDirection(dll);
					break;
				case ENUM_CMD_BALISE:
					testBalise(dll);
					break;
				case ENUM_CMD_ISOLIERSTOSS:
					testIsolierstoss(dll);
					break;
				case ENUM_CMD_LOOP:
					testLoop(dll);
					break;
				case ENUM_CMD_SIGNAL:
					testSignal(dll);
					break;
				case ENUM_CMD_TRAINPOSITION:
					testTrainPosition(dll);
					break;
				case ENUM_CMD_WEICHE:
					testWeiche(dll);
					break;
				case ENUM_CMD_TRACK_CONNECTION:
					testTrackConnection(dll);
					break;
				default:
					std::cout << "default command [type:" << types[i] << " id:" << ids[i] << "]" << std::endl;
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
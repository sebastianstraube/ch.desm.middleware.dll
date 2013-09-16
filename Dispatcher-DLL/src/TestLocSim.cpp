#include <Windows.h> // Sleep()
#include <iostream>
#include <iterator>
#include <string>
#include "MwDll.h"

using namespace desm;

bool testKilometerDirection(MwDll& dll){
	int setRichtung = 1;

	int richtung = setRichtung;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testKilometerDirection" << std::endl;
	dll.setKilometerDirection(setRichtung);
	//dll.getKilometerDirection(richtung);
	std::cout << "richtung: " << richtung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
}

bool testBalise(MwDll& dll){
	int setBaliseId = 12;
	int setGleisId = 2467;
	double setPosition = 124.12;
	int setStellung = 1;

	int baliseId = setBaliseId;
	int stellung = setStellung;
	//TODO: protokoll not set
	std::string protokoll = "NO SETTER";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testBalise" << std::endl;
	//dll.setBalise(setBaliseId, setGleisId, setPosition, setStellung);
	dll.getBalise(baliseId, stellung, protokoll);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "protokoll: " << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool testIsolierstoss(MwDll& dll){
	int setIsolierstossId= 42;
	int setGleisId = 1;
	double setPosition = 3421.23;

	int isolierstossId = setIsolierstossId;
	int gleisId = setGleisId;
	double position = setPosition;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testIsolierstoss" << std::endl;
	dll.setIsolierstoss(setIsolierstossId, setGleisId, setPosition);
	//dll.getIsolierstoss(isolierstossId, gleisId, position);
	std::cout << "isolierstossId: " << isolierstossId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "position: " << position << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool testLoop(MwDll& dll){
	int setBaliseId = 27321;
	int setGleisId = 2382;
	double setPositionVon = 89234.234;
	double setPositionBis = 23923.73;

	int baliseId = setBaliseId;
	//TODO: stellung not set
	int stellung = -1;
	std::string protokoll = "NO SETTER";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testLoop" << std::endl;
	dll.setLoop(setBaliseId, setGleisId, setPositionVon, setPositionBis);
	//dll.getLoop(baliseId, stellung, protokoll);
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "protokoll: " << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
}

bool testSignal(MwDll& dll){
	int setSignalId = 324;
	int setGleisId = 1;
	double setPosition =343.432;
	int setTyp = 1;
	double setHoehe = 133.34;
	double setDistanz = 1233.32;
	const std::string setName = "testSignal1";
	int setStellung = 1;

	int signalId = setSignalId;
	int stellung = setStellung;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSignal" << std::endl;
	dll.setSignal(setSignalId, setGleisId, setPosition, setTyp, setHoehe, setDistanz, setName, setStellung);
	//dll.getSignal(signalId, stellung);
	std::cout << "signalId: " << signalId << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool testTrackConnection(MwDll& dll){
	int setTrackConnectionId = 1242;
	int setGleisId = 42;
	int setGleis1 = 23423;
	int setGleis2 = 12422;
	double setVon = 12.435;
	double setBis = 12423.23;
	std::string setName = "TestTrackConnection";
	int setWeiche1Id = 1241;
	int setWeiche2Id = 1235;

	int trackConnectionId = setTrackConnectionId;
	int gleisId = setGleisId;
	int gleis1 = setGleis1;
	int gleis2 = setGleis2;
	double von = setVon;
	double bis = setBis;
	std::string name = setName;
	int weiche1Id = setWeiche1Id;
	int weiche2Id = setWeiche2Id;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testTrackConnection" << std::endl;
	dll.setTrackConnection(setTrackConnectionId, setGleisId, setGleis1, setGleis2, setVon, setBis, setName, setWeiche1Id, setWeiche2Id);
	//dll.getTrackConnection(trackConnectionId, gleisId, gleis1, gleis2, von, bis, name, weiche1Id, weiche2Id);
	std::cout << "trackConnectionId: " << trackConnectionId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "gleis1: " << gleis1 << std::endl;
	std::cout << "gleis2: " << gleis2 << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "weiche1Id: " << weiche1Id << std::endl;
	std::cout << "weiche2Id: " << weiche2Id << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool testTrainPosition(MwDll& dll){
	int setTrainTyp = 1;
	int setDirection = 0;
	std::vector<double> setPositionList;
	setPositionList.push_back(-1.356);
	setPositionList.push_back(2.232);
	setPositionList.push_back(3.215);
	setPositionList.push_back(4.112);
	std::vector<int> setGleisList;
	setGleisList.push_back(1);
	setGleisList.push_back(9);
	setGleisList.push_back(6);
	setGleisList.push_back(4);
	setGleisList.push_back(0);

	int trainTyp = setTrainTyp;
	int direction = setDirection;
	std::vector<double> positionList = setPositionList;
	std::vector<int> gleisList = setGleisList;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testTrainPosition" << std::endl;
	dll.setTrainPosition(setTrainTyp, setDirection, setPositionList, setGleisList);
	//dll.getTrainPosition(trainTyp, direction, positionList, gleisList);
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

bool testWeiche(MwDll& dll){
	int setWeicheId = 42;
	int setGleisId = 28713;

	int weicheId = setWeicheId;
	int gleisId = setGleisId;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testWeiche" << std::endl;
	dll.setWeiche(setWeicheId, setGleisId);
	//dll.getWeiche(weicheId, gleisId);
	std::cout << "weicheId: " << weicheId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool testTrack(MwDll& dll){
	int setGleisId = 138732;
	double setVon = 3243.234;
	double setBis = 23423.234;
	double setAbstand =2312.2;
	std::string setName = "testTrack1";

	int gleisId = setGleisId;
	double von = setVon;
	double bis = setBis;
	double abstand = setAbstand;
	std::string name = setName;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testTrack" << std::endl;
	dll.setTrack(setGleisId, setVon, setBis, setAbstand, setName);
	//dll.getTrack(gleisId, von, bis, abstand, name);
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "abstand: " << abstand << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

int main(int argc, char** argv) {
	try {
		MwDll dll(L"DesmMiddlewarePlugin.dll");
		dll.onStartProgramm("locsim.json");

		std::cout << "role is: LOCSIM" << std::endl;
		Sleep(1000);
		std::cout << "starting tests ..." << std::endl;

		//start tests
		testKilometerDirection(dll);
		//testBalise(dll);
		testIsolierstoss(dll);
		testLoop(dll);
		testSignal(dll);
		testTrainPosition(dll);
		testWeiche(dll);
		testTrackConnection(dll);
		testTrack(dll);
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
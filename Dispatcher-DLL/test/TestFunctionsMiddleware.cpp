#include <Windows.h> // Sleep()
#include <algorithm>
#include <iostream>
#include <iterator>
#include <string>

#include "TestFunctionsMiddleware.h"

using namespace desm;

TestFunctionsMiddleware::TestFunctionsMiddleware(MwDll& _dll)
	: dll(_dll)
{
}

bool TestFunctionsMiddleware::testInfoVersion(){
	std::string version = "...";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testInfoVersion" << std::endl;
	version = dll.infoVersion(version);
	std::cout << "version: " << version << std::endl; 
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
};

bool TestFunctionsMiddleware::testInfoName(){
	std::string name = "...";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testInfoName" << std::endl;
	name = dll.infoName(name);
	std::cout << "name: " << name << std::endl; 
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
};

bool TestFunctionsMiddleware::testInfoDescription(){
	std::string description = "...";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testInfoDescription" << std::endl;
	description = dll.infoDescription(description);
	std::cout << "description: " << description << std::endl; 
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
};

bool TestFunctionsMiddleware::testInfoConnectionStatus(){
	std::string status = "...";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testInfoConnectionStatus" << std::endl;
	status = dll.infoConnectionStatus(status);
	std::cout << "status: " << status << std::endl; 
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
	return true;
};

bool TestFunctionsMiddleware::testSetKilometerDirection(){
	int richtung = 1;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetKilometerDirection" << std::endl;
	dll.setKilometerDirection(richtung);
	std::cout << "richtung: " << richtung << std::endl; 
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
};

bool TestFunctionsMiddleware::testGetKilometerDirection(){
	int richtung = -1;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetKilometerDirection" << std::endl;
	bool isSuccess = dll.getKilometerDirection(richtung);
	std::cout << "richtung: " << richtung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetBalise(){
	int baliseId = 12;
	int stellung = 1;
	std::string protokoll = "protokoll1";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetBalise" << std::endl;
	dll.setBalise(baliseId, stellung, protokoll);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "protokoll: " << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool TestFunctionsMiddleware::testGetBalise(){
	int baliseId = 12;
	int stellung = 1;
	std::string protokoll = "protokoll1";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetBalise" << std::endl;
	bool isSuccess = dll.getBalise(baliseId, stellung, protokoll);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "protokoll: " << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetIsolierstoss(){
	int isolierstossId= 42;
	int gleisId = 1;
	double position = 3421.23;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetIsolierstoss" << std::endl;
	dll.setIsolierstoss(isolierstossId, gleisId, position);
	std::cout << "isolierstossId: " << isolierstossId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "position: " << position << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool TestFunctionsMiddleware::testGetIsolierstoss(){
	int isolierstossId= 42;
	int gleisId = -1;
	double position = -1.;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetIsolierstoss" << std::endl;
	bool isSuccess = dll.getIsolierstoss(isolierstossId, gleisId, position);
	std::cout << "isolierstossId: " << isolierstossId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "position: " << position << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetLoop(){
	int baliseId = 3;
	int stellung = 0;
	std::string protokoll = "protokoll2";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetLoop" << std::endl;
	dll.setLoop(baliseId, stellung, protokoll);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "protokoll: " << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool TestFunctionsMiddleware::testGetLoop(){
	int baliseId = 3;
	int stellung = -1;
	std::string protokoll = "-1";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetLoop" << std::endl;
	bool isSuccess = dll.getLoop(baliseId, stellung, protokoll);
	std::cout << "baliseId: " << baliseId << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "protokoll: " << protokoll << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetSignal(){
	int signalId = 324;
	int stellung = 3;
	const std::string name = "testSignal1";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetSignal" << std::endl;
	dll.setSignal(signalId, name, stellung);
	std::cout << "signalId: " << signalId << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool TestFunctionsMiddleware::testGetSignal(){
	int signalId = 324;
	int stellung = -1;
	std::string name = "-1";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetSignal" << std::endl;
	bool isSuccess = dll.getSignal(signalId, name, stellung);
	std::cout << "signalId: " << signalId << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "stellung: " << stellung << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetTrackConnection(){
	int gleisBasisId = 23423;
	int gleis1Id = 23423;
	int gleis2Id = 12422;
	double von = 12.435;
	double bis = 12423.23;
	std::string name = "TestSetTrackConnection";
	int weiche1Id = 1241;
	int weiche2Id = 1235;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetTrackConnection" << std::endl;
	dll.setTrackConnection(gleisBasisId, gleis1Id, gleis2Id, von, bis, name, weiche1Id, weiche2Id);
	std::cout << "gleisBasisId: " << gleisBasisId << std::endl;
	std::cout << "gleis1: " << gleis1Id << std::endl;
	std::cout << "gleis2: " << gleis2Id << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "weiche1Id: " << weiche1Id << std::endl;
	std::cout << "weiche2Id: " << weiche2Id << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool TestFunctionsMiddleware::testGetTrackConnection(){
	int gleisBasisId = -1;
	int gleis1Id = 23423;
	int gleis2Id = 12422;
	double von = -1.0;
	double bis = -1.0;
	std::string name = "-1";
	int weiche1Id = -1;
	int weiche2Id = -1;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetTrackConnection" << std::endl;
	bool isSuccess = dll.getTrackConnection(gleisBasisId, gleis1Id, gleis2Id, von, bis, name, weiche1Id, weiche2Id);
	std::cout << "gleisBasisId: " << gleisBasisId << std::endl;
	std::cout << "gleis1Id: " << gleis1Id << std::endl;
	std::cout << "gleis2Id: " << gleis2Id << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "weiche1Id: " << weiche1Id << std::endl;
	std::cout << "weiche2Id: " << weiche2Id << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetTrainPosition(){
	int trainTyp = 323;
	int direction = 0;
	std::vector<double> positionList;
	positionList.push_back(-1.356);
	positionList.push_back(2.232);
	positionList.push_back(3.215);
	positionList.push_back(4.112);
	std::vector<int> gleisList;
	gleisList.push_back(1);
	gleisList.push_back(9);
	gleisList.push_back(6);
	gleisList.push_back(4);
	gleisList.push_back(0);

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetTrainPosition" << std::endl;
	dll.setTrainPosition(trainTyp, direction, positionList, gleisList);
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

bool TestFunctionsMiddleware::testGetTrainPosition(){
	int trainTyp = 323;
	int direction = -1;
	std::vector<double> positionList;
	positionList.push_back(-1.);
	std::vector<int> gleisList;
	gleisList.push_back(-1);

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetTrainPosition" << std::endl;
	bool isSuccess = dll.getTrainPosition(trainTyp, direction, positionList, gleisList);
	std::cout << "trainTyp: " << trainTyp << std::endl;
	std::cout << "direction: " << direction << std::endl;
	std::cout << "positions: ";
	std::copy(positionList.begin(), positionList.end(), std::ostream_iterator<double>(std::cout, " "));
	std::cout << std::endl;
	std::cout << "gleise: ";
	std::copy(gleisList.begin(), gleisList.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

bool TestFunctionsMiddleware::testSetWeiche(){
	int weicheId = 42;
	int gleisId = 28713;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetWeiche" << std::endl;
	dll.setWeiche(weicheId, gleisId);
	std::cout << "weicheId: " << weicheId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}

bool TestFunctionsMiddleware::testGetWeiche(){
	int weicheId = 42;
	int gleisId = -1;

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetWeiche" << std::endl;
	bool isSuccess = dll.getWeiche(weicheId, gleisId);
	std::cout << "weicheId: " << weicheId << std::endl;
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}

#if 0
bool TestFunctionsMiddleware::testSetTrack(){
	int gleisId = 1;
	double von = 0.000000000000000;
	double bis = 97942.0000000000;
	double abstand = 0.000000000000000;
	std::string name = "";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetTrack" << std::endl;
	dll.setTrack(gleisId, von, bis, abstand, name);
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "abstand: " << abstand << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}
#else
bool TestFunctionsMiddleware::testSetTrack(){
	int gleisId = 2;
	double von = 15738.9000000000;
	double bis = 15988.1000000000;
	double abstand = 5.00000000000000;
	std::string name = "hans";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testSetTrack" << std::endl;
	dll.setTrack(gleisId, von, bis, abstand, name);
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "abstand: " << abstand << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return true;
}
#endif

bool TestFunctionsMiddleware::testGetTrack(){
	int gleisId = 2;
	double von = -1.;
	double bis = -1.;
	double abstand = -1.;
	std::string name = "testGetTrack";

	std::cout << "==================   START   ==================" << std::endl;
	std::cout << "testGetTrack" << std::endl;
	bool isSuccess = dll.getTrack(gleisId, von, bis, abstand, name);
	std::cout << "gleisId: " << gleisId << std::endl;
	std::cout << "von: " << von << std::endl;
	std::cout << "bis: " << bis << std::endl;
	std::cout << "abstand: " << abstand << std::endl;
	std::cout << "name: " << name << std::endl;
	std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

	return isSuccess;
}
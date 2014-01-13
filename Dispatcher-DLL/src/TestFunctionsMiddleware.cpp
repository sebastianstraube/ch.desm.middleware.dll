#include <Windows.h> // Sleep()
#include <algorithm>
#include <iostream>
#include <iterator>
#include <string>

#include "TestFunctionsMiddleware.h"

using namespace desm;

class TestFunctionsMiddleware{
	static MwDll* dll;
public:
	TestFunctionsMiddleware::TestFunctionsMiddleware(MwDll dll){
		this->dll = &dll;
	};

public:
	bool TestFunctionsMiddleware::testSetKilometerDirection(){
		int richtung = 1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testSetKilometerDirection" << std::endl;
		dll->setKilometerDirection(richtung);
		std::cout << "richtung: " << richtung << std::endl; 
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
		return true;
	};

	bool TestFunctionsMiddleware::testGetKilometerDirection(){
		int richtung = -1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetKilometerDirection" << std::endl;
		bool isSuccess = dll->getKilometerDirection(richtung);
		std::cout << "richtung: " << richtung << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
		return isSuccess;
	}

	bool TestFunctionsMiddleware::testSetBalise(){
		int baliseId = 12;
		int gleisId = 2467;
		double position = 124.12;
		int stellung = 1;
		std::string protokoll = "protokoll";

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testSetBalise" << std::endl;
		dll->setBalise(baliseId, gleisId, position, stellung);
		std::cout << "baliseId: " << baliseId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "position: " << position << std::endl;
		std::cout << "stellung: " << stellung << std::endl;
		std::cout << "protokoll: " << NULL << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return true;
	}

	bool TestFunctionsMiddleware::testGetBalise(){
		int baliseId = -1;
		int stellung = -1;
		std::string protokoll = "-1";

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetBalise" << std::endl;
		bool isSuccess = dll->getBalise(baliseId, stellung, protokoll);
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
		dll->setIsolierstoss(isolierstossId, gleisId, position);
		std::cout << "isolierstossId: " << isolierstossId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "position: " << position << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return true;
	}

	bool TestFunctionsMiddleware::testGetIsolierstoss(){
		int isolierstossId= -1;
		int gleisId = -1;
		double position = -1.;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetIsolierstoss" << std::endl;
		bool isSuccess = dll->getIsolierstoss(isolierstossId, gleisId, position);
		std::cout << "isolierstossId: " << isolierstossId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "position: " << position << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return isSuccess;
	}

	bool TestFunctionsMiddleware::testSetLoop(){
		int baliseId = 27321;
		int gleisId = 2382;
		double positionVon = 89234.234;
		double positionBis = 23923.73;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testSetLoop" << std::endl;
		dll->setLoop(baliseId, gleisId, positionVon, positionBis);
		std::cout << "baliseId: " << baliseId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "positionVon: " << positionVon << std::endl;
		std::cout << "positionBis: " << positionBis << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
		return true;
	}

	bool TestFunctionsMiddleware::testGetLoop(){
		int baliseId = -1;
		int gleisId = -1;
		double positionVon = -1;
		double positionBis = -1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetLoop" << std::endl;
		bool isSuccess = dll->getLoop(baliseId, gleisId, positionVon, positionBis);
		std::cout << "baliseId: " << baliseId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "positionVon: " << positionVon << std::endl;
		std::cout << "positionBis: " << positionBis << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;
	
		return isSuccess;
	}

	bool TestFunctionsMiddleware::testSetSignal(){
		int signalId = 324; // TODO: was there a setter for this id before?
		int gleisId = 1;
		double position =343.432;
		int typ = 1;
		double hoehe = 133.34;
		double distanz = 1233.32;
		const std::string name = "testSignal1";
		int stellung = 1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testSetSignal" << std::endl;
		dll->setSignal(signalId, gleisId, position, typ, hoehe, distanz, name, stellung);
		std::cout << "signalId: " << signalId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "position: " << position << std::endl;
		std::cout << "typ: " << typ << std::endl;
		std::cout << "hoehe: " << hoehe << std::endl;
		std::cout << "distanz: " << distanz << std::endl;
		std::cout << "name: " << name << std::endl;
		std::cout << "stellung: " << stellung << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return true;
	}

	bool TestFunctionsMiddleware::testGetSignal(){
		int signalId = -1;
		int gleisId = -1;
		double position = -1.;
		int typ = -1;
		double hoehe = -1;
		double distanz = -1;
		const std::string name = "-1";
		int stellung = -1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetSignal" << std::endl;
		bool isSuccess = dll->getSignal(signalId, stellung);
		std::cout << "signalId: " << signalId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "position: " << position << std::endl;
		std::cout << "typ: " << typ << std::endl;
		std::cout << "hoehe: " << hoehe << std::endl;
		std::cout << "distanz: " << distanz << std::endl;
		std::cout << "name: " << name << std::endl;
		std::cout << "stellung: " << stellung << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return isSuccess;
	}

	bool TestFunctionsMiddleware::testSetTrackConnection(){
		int trackConnectionId = 1;
		int gleisId = 42;
		int gleis1 = 23423;
		int gleis2 = 12422;
		double von = 12.435;
		double bis = 12423.23;
		std::string name = "TestSetTrackConnection";
		int weiche1Id = 1241;
		int weiche2Id = 1235;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testSetTrackConnection" << std::endl;
		dll->setTrackConnection(trackConnectionId, gleisId,gleis1,gleis2, von, bis, name, weiche1Id, weiche2Id);
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

	bool TestFunctionsMiddleware::testGetTrackConnection(){
		int trackConnectionId = -1;
		int gleisId = -1;
		int gleis1 = -1;
		int gleis2 = -1;
		double von = -1.;
		double bis = -1.;
		std::string name = "TestGetTrackConnection";
		int weiche1Id = -1;
		int weiche2Id = -1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetTrackConnection" << std::endl;
		bool isSuccess = dll->getTrackConnection(trackConnectionId, gleisId, gleis1, gleis2, von, bis, name, weiche1Id, weiche2Id);
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "gleis1: " << gleis1 << std::endl;
		std::cout << "gleis2: " << gleis2 << std::endl;
		std::cout << "von: " << von << std::endl;
		std::cout << "bis: " << bis << std::endl;
		std::cout << "name: " << name << std::endl;
		std::cout << "weiche1Id: " << weiche1Id << std::endl;
		std::cout << "weiche2Id: " << weiche2Id << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return isSuccess;
	}

	bool TestFunctionsMiddleware::testSetTrainPosition(){
		int trainTyp = 1;
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
		dll->setTrainPosition(trainTyp, direction, positionList, gleisList);
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
		int trainTyp = -1;
		int direction = -1;
		std::vector<double> positionList;
		positionList.push_back(-1.);
		std::vector<int> gleisList;
		gleisList.push_back(-1);

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetTrainPosition" << std::endl;
		bool isSuccess = dll->getTrainPosition(trainTyp, direction, positionList, gleisList);
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
		dll->setWeiche(weicheId, gleisId);
		std::cout << "weicheId: " << weicheId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return true;
	}

	bool TestFunctionsMiddleware::testGetWeiche(){
		int weicheId = -1;
		int gleisId = -1;

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetWeiche" << std::endl;
		bool isSuccess = dll->getWeiche(weicheId, gleisId);
		std::cout << "weicheId: " << weicheId << std::endl;
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return isSuccess;
	}

	bool TestFunctionsMiddleware::testSetTrack(){
		int gleisId = 138732;
		double von = 3243.234;
		double bis = 23423.234;
		double abstand =2312.2;
		std::string name = "testSetTrack";

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testSetTrack" << std::endl;
		dll->setTrack(gleisId, von, bis, abstand, name);
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "von: " << von << std::endl;
		std::cout << "bis: " << bis << std::endl;
		std::cout << "abstand: " << abstand << std::endl;
		std::cout << "name: " << name << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return true;
	}

	bool TestFunctionsMiddleware::testGetTrack(){
		int gleisId = -1;
		double von = -1.;
		double bis = -1.;
		double abstand = -1.;
		std::string name = "testGetTrack";

		std::cout << "==================   START   ==================" << std::endl;
		std::cout << "testGetTrack" << std::endl;
		bool isSuccess = dll->getTrack(gleisId, von, bis, abstand, name);
		std::cout << "gleisId: " << gleisId << std::endl;
		std::cout << "von: " << von << std::endl;
		std::cout << "bis: " << bis << std::endl;
		std::cout << "abstand: " << abstand << std::endl;
		std::cout << "name: " << name << std::endl;
		std::cout << "==================   ENDE    ==================" << std::endl << std::endl;

		return isSuccess;
	}
};
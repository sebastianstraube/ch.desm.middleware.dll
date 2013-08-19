#pragma once

#include <vector>
#include <Windows.h>

namespace desm {

	class TestDesmMiddleware{
	public:
		TestDesmMiddleware(std::string role);
		~TestDesmMiddleware();
	public:
		std::string testInfoVersion();
		std::string testInfoName();
		std::string testInfoDescription();
		
		void testOnStartProgramm(const std::string& configPath);
		void testOnStopProgramm();

		//ON command
		void testOnStartSimulation();
		void testOnStopSimulation();
		void testOnLoadStrecke();
		//SET command
		bool testSetTrack(int gleisId, double von, double bis, double abstand, const std::string& name);
		bool testSetTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		bool testSetSignal(int signalId, int gleisId, double position, int typ, double hoehe, double distanz, const std::string& name, int direction);
		bool testSetBalise(int gleisId, double position, int baliseId, int direction);
		bool testSetLoop(int baliseId, int gleisId, double positionVon, double positionBis);
		bool testSetIsolierstoss(int isolierstossId, int gleisId, double position);
		bool testSetKilometerDirection(int richtung);
		bool testSetTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		//GET command - ONLY VALID WHEN SET 
		bool testGetKilometerDirection(int& richtung);
		bool testGetEvents(std::vector<int>& typeList, std::vector<int>& idList);
		bool testGetSignal(int signalId, int& stellung);
		bool testGetBalise(int baliseId, int& stellung, std::string& protokoll);
		bool testGetLoop(int baliseId, int& stellung, std::string& protokoll);
		bool testGetWeiche(int weicheId, int& gleisId);
		
		// UNDOCUMENTED - MIDDLEWARE CLIENT FUNCTIONS
		bool testGetTrainPosition(int& train, int& direction, std::vector<double>& positionList, std::vector<int>& gleisList);
		bool testGetIsolierstoss(int& isolierstossId, int& gleisId, double& position);
		bool testGetTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		bool testGetTrack(int gleisId, double von, double bis, double abstand, const std::string& name);
	};
}
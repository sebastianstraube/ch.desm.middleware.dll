#pragma once

#include <string>
#include <vector>

#include "CommunicationController.h"
#include "Events.h"

namespace desm {

	static const int INVALID_ID = -999;

	class Middleware
	{
	public: // lifetime
		Middleware(const std::string& configPath);
		~Middleware();
	
	public:
		//ON command
		int onStartSimulation();
		int onStopSimulation();
		int onLoadStrecke();
		
		//SET command
		int setTrack(int gleisId, double von, double bis, double abstand, const std::string& name);
		int setTrackConnection(int trackConnectionId, int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		int setSignal(int signalId, int gleisId, double position, int typ, double hoehe, double distanz, const std::string& name, int stellung);
		int setBalise(int baliseId, int gleisId, double position, int stellung, const std::string &protokoll);
		int setLoop(int baliseId, int gleisId, double positionVon, double positionBis);
		int setIsolierstoss(int isolierstossId, int gleisId, double position);
		int setKilometerDirection(int richtung);
		int setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		
		//GET command - BEFORE USE, DO SET
		int getSignal(int signalId, int& gleisId, double& position, int& typ, double& hoehe, double& distanz, std::string& name, int& stellung);
		int getBalise(int baliseId, int& stellung, std::string& protokoll);
		int getLoop(int baliseId, int& gleisId, double& positionVon, double& positionBis);
		int getKilometerDirection(int& richtung);
		int getWeiche(int weicheId, int& gleisId);
		
		int getEvents(std::vector<int>& typeList, std::vector<int>& idList);

		// UNDOCUMENTED - MIDDLEWARE FUNCTIONS (HIGH LEVEL)
		int getTrack(int gleisId, double& von, double& bis, double& abstand, std::string& name);
		int getTrackConnection(int trackConnectionId, int& gleisId, int& gleis1, int& gleis2, double& von, double& bis, std::string& name, int& weiche1Id, int& weiche2Id);
		int getIsolierstoss(int isolierstossId, int& gleisId, double& position);
		int setWeiche(int weicheId, int gleisId);
		int getTrainPosition(int trainTyp, int& direction, std::vector<double>& positionList, std::vector<int>& gleisList);

	private:
		struct Impl;
		Impl* m_pImpl;
	};

};
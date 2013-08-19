#pragma once

#include <string>
#include <vector>

#include "CommunicationController.h"
#include "Events.h"

namespace desm {

	static const int INVALID_ID = std::numeric_limits<int>::max();

	class Middleware
	{
	public: // lifetime
		Middleware(const std::string& configPath);
		~Middleware();
	
	public: // api - track setup
		
		int onLoadStrecke();

		int setTrack(int gleisId, double von, double bis, double abstand, const std::string& name);
		int setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		int setSignal (int signalId, int gleisId, double position, int typ, double hoehe, double distanz, const std::string& name, int direction);
		int setBalise (int baliseId, int gleisId, double position, int stellung, const std::string& protokoll);
		int setLoop (int baliseId, int gleisId, double positionVon, double positionBis);
		int setIsolierstoss (int isolierstossId, int gleisId, double position);
		int setKilometerDirection(int richtung);
		int getKilometerDirection(int& richtung);
	
	public: // api - simulation
		int onStartSimulation();
		int getEvents(std::vector<int>& types, std::vector<int>& ids);
		int getSignal(int signalId, int& stellung);
		int getBalise(int baliseId, int& stellung, std::string& protokoll);
		int getLoop(int gleisId, int& baliseId, double& positionVon, double& positionBis);
		int getWeiche(int weicheId, int& gleisId);
		int setTrainPosition(int trainTyp, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		int onStopSimulation();

		// api - middleware
		int getTrainPosition(int& trainTyp, int& direction, std::vector<double>& positionList, std::vector<int>& gleisList);
		int getIsolierstoss (int isolierstossId, int& gleisId, double& position);
		// TODO we need a track connection id here!
		int getTrackConnection(int gleisId, int& gleis1, int& gleis2, double& von, double& bis, std::string& name, int& weiche1Id, int& weiche2Id);
		// TODO we need a track id here!
		int getTrack(int gleisId, double& von, double& bis, double& abstand, std::string& name);

	private:
		struct Impl;
		Impl* m_pImpl;
	};

};
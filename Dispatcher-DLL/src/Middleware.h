#pragma once

#include <string>
#include <vector>
#include "CommunicationController.h"

namespace desm {

	class Middleware
	{
	public: // types
		typedef std::vector<std::pair<int, int>> tTypeList;
	public: // lifetime
		Middleware(const std::string& configPath);
		~Middleware();
	public: // api - track setup
		int onLoadStrecke();
		int setTrack(int gleisId, double von, double bis, float abstand, const std::string& name);
		int setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		int setSignal (int signalId, int gleisId, double position, int typ, float hoehe, float distanz, const std::string& name, int direction);
		int setBalise (int gleisId, double position, int baliseId, int direction);
		int setLoop (int gleisId, double positionVon, double positionBis, int baliseId);
		int setIsolierstoss (int gleisId, double position);
		void setKilometerDirection(int direction);
		int getKilometerDirection();
	public: // api - simulation
		int onStartSimulation();
		int getEvents(tTypeList&);
		int getSignal(int signalId, int& stellung);
		int getBalise(int baliseId, int& stellung, std::string& protokoll);
		int getLoop(int baliseId, int& stellung, std::string& protokoll);
		int getWeiche(int weicheId, int& gleisId);
		int setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		int onStopSimulation();
	private:
		struct Impl;
		Impl* m_pImpl;
	};

};
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
		int setBalise (int baliseId, int gleisId, double position, int stellung, std::string protokoll);
		int setLoop (int baliseId, int gleisId, double positionVon, double positionBis);
		int setIsolierstoss (int gleisId, double position);
		int setKilometerDirection(int richtung);
		int getKilometerDirection(int& richtung);
	
	public: // api - simulation
		int onStartSimulation();
		int getEvents(std::vector<int>& types, std::vector<int>& ids);
		int getSignal(int signalId, int& stellung);
		int getBalise(int baliseId, int& stellung, std::string& protokoll);
		int getLoop(int baliseId, int& stellung, std::string& protokoll);
		int getWeiche(int weicheId, int& gleisId);
		int setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		int onStopSimulation();
	

		/**
		std::string infoVersion();
		std::string infoName();
		std::string infoDescription();
		
		void onStartProgramm(const std::string& configPath);
		void onStopProgramm();

		//TODO Sebastian
		//ON command
		void onStartSimulation();
		void onStopSimulation();
		void onLoadStrecke(void);
		//SET command
		bool setTrack(int gleisId, double von, double bis, double abstand, const std::string& name);
		bool setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		bool setSignal(int signalId, int gleisId, double position, int typ, double hoehe, double distanz, const std::string& name, int direction);
		bool setBalise(int gleisId, double position, int baliseId, int direction);
		bool setLoop(int baliseId, int gleisId, double positionVon, double positionBis);
		bool setIsolierstoss(int gleisId, double position);
		bool setKilometerDirection(int richtung);
		//GET command
		bool getKilometerDirection(int& richtung);
		bool getEvents(std::vector<int>& typeList, std::vector<int>& idList);
		bool getSignal(int signalId, int& stellung);
		bool getBalise(int baliseId, int& stellung, std::string& protokoll);
		bool getLoop(int baliseId, int& stellung, std::string& protokoll);
		bool getWeiche(int weicheId, int& gleisId);
		bool setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		*/
	private:
		struct Impl;
		Impl* m_pImpl;
	};

};
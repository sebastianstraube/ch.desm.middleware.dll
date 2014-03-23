#pragma once

#include <vector>
#include <Windows.h>

// TODO: convert fatal and api misuse error codes to exceptions?

namespace desm {

	class MwDll {
	public:
		MwDll(LPCWSTR dllName);
		~MwDll();
	public:

		//INFO no command
		std::string infoVersion(std::string& version);
		std::string infoName(std::string& name);
		std::string infoConnectionStatus(std::string& status);
		std::string infoDescription(std::string& desc);
		
		//ON command
		void onStartProgramm(const std::string& configPath);
		void onStopProgramm();
		void onStartSimulation();
		void onStopSimulation();
		void onLoadStrecke();
		
		//SET command
		bool setTrack(int gleisId, double von, double bis, double abstand, const std::string& name);
		bool setTrackConnection(int gleisBasisId, int gleis1Id, int gleis2Id, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		bool setSignal(int signalId,  const std::string& name, int stellung);
		bool setBalise(int baliseId, int stellung, const std::string& protokoll);
		bool setLoop(int baliseId, int stellung, const std::string& protokoll);
		bool setIsolierstoss(int isolierstossId, int gleisId, double position);
		bool setKilometerDirection(int richtung);
		bool setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
		bool setWeiche(int weicheId, int gleisId);
		
		//GET Command
		bool getSignal(int signalId, std::string& name, int& stellung);
		bool getBalise(int baliseId, int& stellung, std::string& protokoll);
		bool getLoop(int baliseId, int& stellung, std::string& protokoll);
		bool getKilometerDirection(int& richtung);
		bool getWeiche(int weicheId, int& gleisId);
		bool getEvents(std::vector<int>& typeList, std::vector<std::vector<int>>& paramList);
		bool getTrack(int gleisId, double& von, double& bis, double& abstand, std::string& name);
		bool getTrackConnection(int& gleisBasisId, int gleis1Id, int gleis2Id, double& von, double& bis, std::string& name, int& weiche1Id, int& weiche2Id);
		bool getIsolierstoss(int isolierstossId, int& gleisId, double& position);
		bool getTrainPosition(int trainTyp, int& direction, std::vector<double>& positionList, std::vector<int>& gleisList);

	private:
		struct Impl;
		Impl* m_pImpl;
	};
}

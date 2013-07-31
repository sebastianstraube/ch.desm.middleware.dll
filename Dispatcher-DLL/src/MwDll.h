#pragma once

#include <vector>
#include <Windows.h>

namespace desm {

	class MwDll {
	public:
		MwDll(LPCWSTR dllName);
		~MwDll();
	public:
		std::string infoVersion();
		std::string infoName();
		std::string infoDescription();
		int onStartProgramm(const std::string& configPath);
		int onStopProgramm();
		int onStartSimulation();
		int onStopSimulation();
		int setTrack(int gleisId, double von, double bis, float abstand, const std::string& name);
		int setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id);
		int setSignal(int signalId, int gleisId, double position, int typ, float hoehe, float distanz, const std::string& name, int direction);
		int setBalise(int gleisId, double position, int baliseId, int direction);
		int setLoop(int gleisId, double positionVon, double positionBis, int baliseId);
		int setIsolierstoss(int gleisId, double position);
		int setKilometerDirection(int direction);
		int getKilometerDirection(int& direction);
		int onLoadStrecke(void);
		int getEvents(std::vector<int>& typeList, std::vector<int>& idList);
		int getSignal(int signalId, int& stellung);
		int getBalise(int baliseId, int& stellung, std::string& protokoll);
		int getLoop(int baliseId, int& stellung, std::string& protokoll);
		int getWeiche(int weicheId, int& gleisId);
		int setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList);
	private:
		struct Impl;
		Impl* m_pImpl;
	};
}

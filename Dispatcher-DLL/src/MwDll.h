#pragma once

#include <Windows.h>

namespace desm {

	class MwDll {
	public:
		MwDll(LPCWSTR dllName);
		~MwDll();
	public:
		int stw_onStartProgramm(char* configPath);
		int stw_onStopProgramm(void);
		/*
		const char* stw_infoVersion(void);
		const char* stw_infoName(void);
		const char* stw_infoDescription(void);
		int stw_onStartSimulation(void);
		int stw_onStopSimulation(void);
		int stw_setTrack(int gleisId, double von, double bis, float abstand, char* name);
		int stw_setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, char* name, int weiche1Id, int weiche2Id);
		int stw_setSignal(int signalId, int gleisId, double position, int typ, float hoehe, float distanz, char* name, int direction);
		int stw_setBalise(int gleisId, double position, int baliseId, int direction);
		int stw_setLoop(int gleisId, double positionVon, double positionBis, int baliseId);
		int stw_setIsolierstoss(int gleisId, double position);
		*/
		int stw_setKilometerDirection(int direction);
		int stw_getKilometerDirection(int& direction);
		/*
		int stw_onLoadStrecke(void);
		int stw_getEvents(int* number, int** typeList, int** idList);
		int stw_getSignal(int signalId, int* stellung);
		int stw_getBalise(int baliseId, int* stellung, char** protokoll);
		int stw_getLoop(int baliseId, int* stellung, char** protokoll);
		int stw_getWeiche(int weicheId, int* gleisId);
		int stw_setTrainPosition(int train, int direction, double* positionList, int* gleisList);
		void stw_deallocate(void** p);
		*/
	private:
		struct Impl;
		Impl* m_pImpl;
	};
}

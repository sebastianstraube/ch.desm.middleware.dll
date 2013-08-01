#include <iostream>
#include <string>

#include "MwDll.h"
#include "ErrorCodes.h"

using namespace desm;

struct MwDll::Impl {

	// types

	typedef int (*t_stw_onStartProgramm)(char* configPath);
	typedef int (*t_stw_onStopProgramm)(void);
	typedef const char* (*t_stw_infoVersion)(void);
	typedef const char* (*t_stw_infoName)(void);
	typedef const char* (*t_stw_infoDescription)(void);
	typedef int (*t_stw_onStartSimulation)(void);
	typedef int (*t_stw_onStopSimulation)(void);
	typedef int (*t_stw_setTrack)(int gleisId, double von, double bis, float abstand, char* name);
	typedef int (*t_stw_setTrackConnection)(int gleisId, int gleis1, int gleis2, double von, double bis, char* name, int weiche1Id, int weiche2Id);
	typedef int (*t_stw_setSignal)(int signalId, int gleisId, double position, int typ, float hoehe, float distanz, char* name, int direction);
	typedef int (*t_stw_setBalise)(int gleisId, double position, int baliseId, int direction);
	typedef int (*t_stw_setLoop)(int gleisId, double positionVon, double positionBis, int baliseId);
	typedef int (*t_stw_setIsolierstoss)(int gleisId, double position);
	typedef int (*t_stw_setKilometerDirection)(int direction);
	typedef int (*t_stw_getKilometerDirection)(int *direction);
	typedef int (*t_stw_onLoadStrecke)(void);
	typedef int (*t_stw_getEvents)(int* number, int** typeList, int** idList);
	typedef int (*t_stw_getSignal)(int signalId, int* stellung);
	typedef int (*t_stw_getBalise)(int baliseId, int* stellung, char** protokoll);
	typedef int (*t_stw_getLoop)(int baliseId, int* stellung, char** protokoll);
	typedef int (*t_stw_getWeiche)(int weicheId, int* gleisId);
	typedef int (*t_stw_setTrainPosition)(int train, int direction, double* positionList, int* gleisList);
	typedef void (*t_stw_deallocate)(void** p);
	
	// member

	HINSTANCE m_hInstLibrary;

	t_stw_onStartProgramm m_stw_onStartProgramm;
	t_stw_onStopProgramm m_stw_onStopProgramm;
	t_stw_infoVersion m_stw_infoVersion;
	t_stw_infoName m_stw_infoName;
	t_stw_infoDescription m_stw_infoDescription;
	t_stw_onStartSimulation m_stw_onStartSimulation;
	t_stw_onStopSimulation m_stw_onStopSimulation;
	t_stw_setTrack m_stw_setTrack;
	t_stw_setTrackConnection m_stw_setTrackConnection;
	t_stw_setSignal m_stw_setSignal;
	t_stw_setBalise m_stw_setBalise;
	t_stw_setLoop m_stw_setLoop;
	t_stw_setIsolierstoss m_stw_setIsolierstoss;
	t_stw_setKilometerDirection m_stw_setKilometerDirection;
	t_stw_getKilometerDirection m_stw_getKilometerDirection;
	t_stw_onLoadStrecke m_stw_onLoadStrecke;
	t_stw_getEvents m_stw_getEvents;
	t_stw_getSignal m_stw_getSignal;
	t_stw_getBalise m_stw_getBalise;
	t_stw_getLoop m_stw_getLoop;
	t_stw_getWeiche m_stw_getWeiche;
	t_stw_setTrainPosition m_stw_setTrainPosition;
	t_stw_deallocate m_stw_deallocate;

	// lifetime

	Impl(LPCWSTR dllName)
		: m_hInstLibrary(NULL)
		, m_stw_onStartProgramm(NULL)
		, m_stw_onStopProgramm(NULL)
		, m_stw_setKilometerDirection(NULL)
	{
		m_hInstLibrary = LoadLibrary(dllName);
		if(!m_hInstLibrary) {
			throw std::exception("dll not found");
		}
		
#define IMPORT_DLL_FUNCTION(hInst, name) \
		if(NULL == (m_##name = (t_ ## name)GetProcAddress(hInst, #name))) \
			throw std::exception("unable to load function " #name); 

		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_onStartProgramm);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_onStopProgramm);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_infoVersion);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_infoName);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_infoDescription);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_onStartSimulation);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_onStopSimulation);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setTrack);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setTrackConnection);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setSignal);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setBalise);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setLoop);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setIsolierstoss);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setKilometerDirection);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_getKilometerDirection);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_onLoadStrecke);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_getEvents);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_getSignal);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_getBalise);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_getLoop);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_getWeiche);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_setTrainPosition);
		IMPORT_DLL_FUNCTION(m_hInstLibrary, stw_deallocate);

#undef IMPORT_DLL_FUNCTION

	}

	~Impl() {
		FreeLibrary(m_hInstLibrary);
	}

};

bool checkErrorCode(int err) {
	switch(err) {
	case desm::ERROR_OK:
		return true;
	case desm::ERROR_FATAL:
		throw std::exception("fatal error");
	case desm::ERROR_API_MISUSE:
		throw std::exception("api misuse!");
	case desm::ERROR_UNKNOWN_ID:
		return false;
	default:
		throw std::exception("dll returned unknown error code");
	}
}

MwDll::MwDll(LPCWSTR dllName)
	: m_pImpl(new MwDll::Impl(dllName))
{
}

MwDll::~MwDll() {
	delete m_pImpl;
}

std::string MwDll::infoVersion() {
	return std::string(m_pImpl->m_stw_infoVersion());
}

std::string MwDll::infoName() {
	return std::string(m_pImpl->m_stw_infoName());
}

std::string MwDll::infoDescription() {
	return std::string(m_pImpl->m_stw_infoDescription());
}

void MwDll::onStartProgramm(const std::string& configPath) {
	char* dup = _strdup(configPath.c_str());
	checkErrorCode(m_pImpl->m_stw_onStartProgramm(dup));
	free(dup);
}

void MwDll::onStopProgramm() {
	checkErrorCode(m_pImpl->m_stw_onStopProgramm());
}

bool MwDll::setTrack(int gleisId, double von, double bis, float abstand, const std::string& name) {
	char* dup = _strdup(name.c_str());
	bool success = checkErrorCode(m_pImpl->m_stw_setTrack(gleisId, von, bis, abstand, dup));
	free(dup);
	return success;
}

bool MwDll::setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id) {
	char* dup = _strdup(name.c_str());
	bool success = checkErrorCode(m_pImpl->m_stw_setTrackConnection(gleisId, gleis1, gleis2, von, bis, dup, weiche1Id, weiche2Id));
	free(dup);
	return success;
}

bool MwDll::setSignal(int signalId, int gleisId, double position, int typ, float hoehe, float distanz, const std::string& name, int direction) {
	char* dup = _strdup(name.c_str());
	bool success = checkErrorCode(m_pImpl->m_stw_setSignal(signalId, gleisId, position, typ, hoehe, distanz, dup, direction));
	free(dup);
	return success;
}

bool MwDll::setBalise(int gleisId, double position, int baliseId, int direction) {
	return checkErrorCode(m_pImpl->m_stw_setBalise(gleisId, position, baliseId, direction));
}

bool MwDll::setLoop(int gleisId, double positionVon, double positionBis, int baliseId) {
	return checkErrorCode(m_pImpl->m_stw_setLoop(gleisId, positionVon, positionBis, baliseId));
}

bool MwDll::setIsolierstoss(int gleisId, double position) {
	return checkErrorCode(m_pImpl->m_stw_setIsolierstoss(gleisId, position));
}

bool MwDll::setKilometerDirection(int direction) {
	return checkErrorCode(m_pImpl->m_stw_setKilometerDirection(direction));
}

bool MwDll::getKilometerDirection(int& direction) {
	return checkErrorCode(m_pImpl->m_stw_getKilometerDirection(&direction));
}

void MwDll::onLoadStrecke() {
	checkErrorCode(m_pImpl->m_stw_onLoadStrecke());
}

bool MwDll::getEvents(std::vector<int>& typeList, std::vector<int>& idList) {
	int num, *cTypeList, *cIdList;
	int ret = m_pImpl->m_stw_getEvents(&num, &cTypeList, &cIdList);
	if(ret == 0) {
		for(int i = 0; i < num; ++i) {
			typeList.push_back(cTypeList[i]);
			idList.push_back(cIdList[i]);
		}
		m_pImpl->m_stw_deallocate((void**)&cTypeList);
		m_pImpl->m_stw_deallocate((void**)&cIdList);
	}
	return ret;
}

bool MwDll::getSignal(int signalId, int& stellung) {
	return checkErrorCode(m_pImpl->m_stw_getSignal(signalId, &stellung));
}

bool MwDll::getBalise(int baliseId, int& stellung, std::string& protokoll) {
	char* cProtokoll;
	bool success = checkErrorCode(m_pImpl->m_stw_getBalise(baliseId, &stellung, &cProtokoll));
	if(success) {
		protokoll = std::string(cProtokoll);
		m_pImpl->m_stw_deallocate((void**)&cProtokoll);
	}
	return success;
}

bool MwDll::getLoop(int baliseId, int& stellung, std::string& protokoll) {
	char* cProtokoll;
	bool success = checkErrorCode(m_pImpl->m_stw_getLoop(baliseId, &stellung, &cProtokoll));
	if(success) {
		protokoll = std::string(cProtokoll);
		m_pImpl->m_stw_deallocate((void**)&cProtokoll);
	}
	return success;
}

bool MwDll::getWeiche(int weicheId, int& gleisId) {
	return checkErrorCode(m_pImpl->m_stw_getWeiche(weicheId, &gleisId));
}

bool MwDll::setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList) {
	size_t positionListLen = positionList.size();
	size_t gleisListLen = gleisList.size();
	double* cPositionList = (double*)malloc(sizeof(double) * (positionListLen + 1));
	int* cGleisList = (int*)malloc(sizeof(int) * (gleisListLen + 1));
	for(size_t i = 0; i < positionListLen; ++i) {
		cPositionList[i] = positionList[i];
	}
	for(size_t i = 0; i < gleisListLen; ++i) {
		cGleisList[i] = gleisList[i];
	}
	cPositionList[positionListLen] = NULL;
	cGleisList[gleisListLen] = NULL;
	bool success = checkErrorCode(m_pImpl->m_stw_setTrainPosition(train, direction, cPositionList, cGleisList));
	free(cPositionList);
	free(cGleisList);
	return success;
}

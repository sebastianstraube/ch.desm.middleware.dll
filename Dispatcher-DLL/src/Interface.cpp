#include <iostream>
#include <string>

#include "ErrorCodes.h"
#include "Middleware.h"

static desm::Middleware* s_middleware = NULL;
static const char* s_info_name = "DLL DESMMiddleware";
static const char* s_info_version = "0.13";
static const char* s_info_desc = "";

extern "C" {

	__declspec(dllexport) int stw_onStartProgramm(char* configPath) {
		//std::cout << "C INTERFACE: stw_onStartProgramm"<< std::endl;
		if(s_middleware != NULL) {
			return desm::ERROR_API_MISUSE;
		}
		try {
			s_middleware = new desm::Middleware(configPath);
			return desm::ERROR_OK;
		} catch(const std::bad_alloc& e) {
			s_middleware = NULL;
			std::cerr << "EXCEPTION: " << e.what() << std::endl;
			return desm::ERROR_FATAL;
		} catch(...) {
			s_middleware = NULL;
			std::cerr << "UNKNOWN EXCEPTION!" << std::endl;
			return desm::ERROR_FATAL;
		}
	}

	__declspec(dllexport) int stw_onStopProgramm(void) {
		//std::cout << "C INTERFACE: stw_onStopProgramm"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		delete s_middleware;
		s_middleware = NULL;
		return desm::ERROR_OK;
	}

	__declspec(dllexport) const char* stw_infoVersion(void) {
		//std::cout << "C INTERFACE: stw_infoVersion"<< std::endl;
		return s_info_version;
	}

	__declspec(dllexport) const char* stw_infoName(void) {
		//std::cout << "C INTERFACE: stw_infoName"<< std::endl;
		return s_info_name;
	}

	__declspec(dllexport) const char* stw_infoDescription(void) {
		//std::cout << "C INTERFACE: stw_infoDescription"<< std::endl;
		return s_info_desc;
	}

	__declspec(dllexport) int stw_onStartSimulation(void) {
		//std::cout << "C INTERFACE: stw_onStartSimulation"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->onStartSimulation();
	}

	__declspec(dllexport) int stw_onStopSimulation(void) {
		//std::cout << "C INTERFACE: stw_onStopSimulation"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->onStopSimulation();
	}

	__declspec(dllexport) int stw_setTrack(int gleisId, double von, double bis, double abstand, char* name) {
		//std::cout << "C INTERFACE: stw_setTrack"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setTrack(gleisId, von, bis, abstand, std::string(name));
	}

	__declspec(dllexport) int stw_getTrack(int gleisId, double von, double bis, double abstand, char* name) {
		//std::cout << "C INTERFACE: stw_getTrack"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->getTrack(gleisId, von, bis, abstand, std::string(name));
	}

	__declspec(dllexport) int stw_setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, char* name, int weiche1Id, int weiche2Id) {
		//std::cout << "C INTERFACE: stw_setTrackConnection"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setTrackConnection(gleisId, gleis1, gleis2, von, bis, std::string(name), weiche1Id, weiche2Id);
	}

	__declspec(dllexport) int stw_getTrackConnection(int gleisId, int* gleis1, int* gleis2, double* von, double* bis, char** cName, int* weiche1Id, int* weiche2Id) {
		//std::cout << "C INTERFACE: stw_getTrackConnection"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!gleis1 || !gleis2 || !von || !bis || !cName || !weiche1Id || !weiche2Id) {
			return desm::ERROR_API_MISUSE;
		}
		std::string name;
		int rc = s_middleware->getTrackConnection(gleisId, *gleis1, *gleis2, *von, *bis, name, *weiche1Id, *weiche2Id);
		if(rc == desm::ERROR_OK) {
			*cName = _strdup(name.c_str());
		}
		return rc;
	}

	__declspec(dllexport) int stw_setSignal(int signalId, int gleisId, double position, int typ, double hoehe, double distanz, char* name, int direction) {
		//std::cout << "C INTERFACE: stw_setSignal"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setSignal(signalId, gleisId, position, typ, hoehe, distanz, std::string(name), direction);
	}

	__declspec(dllexport) int stw_setBalise(int gleisId, double position, int baliseId, int stellung) {
		//std::cout << "C INTERFACE: stw_setBalise"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setBalise(baliseId, gleisId, position, stellung, NULL);
	}

	__declspec(dllexport) int stw_setLoop(int baliseId, int gleisId, double positionVon, double positionBis) {
		//std::cout << "C INTERFACE: stw_setLoop"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setLoop(baliseId, gleisId, positionVon, positionBis);
	}

	__declspec(dllexport) int stw_setIsolierstoss(int isolierstossId, int gleisId, double position) {
		//std::cout << "C INTERFACE: stw_setIsolierstoss"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setIsolierstoss(isolierstossId, gleisId, position);
	}

	__declspec(dllexport) int stw_getIsolierstoss(int* isolierstossId, int* gleisId, double* position) {
		//std::cout << "C INTERFACE: stw_getIsolierstoss"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->getIsolierstoss(*isolierstossId, *gleisId, *position);
	}
	
	__declspec(dllexport) int stw_setKilometerDirection(int richtung) {
		//std::cout << "C INTERFACE: stw_setKilometerDirection"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->setKilometerDirection(richtung);
	}

	__declspec(dllexport) int stw_getKilometerDirection(int *richtung) {
		//std::cout << "C INTERFACE: stw_getKilometerDirection"<< std::endl;
		if(!richtung) {
			return desm::ERROR_API_MISUSE;
		}
		if(s_middleware == NULL || richtung == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->getKilometerDirection(*richtung);
	}

	__declspec(dllexport) int stw_onLoadStrecke(void) {
		//std::cout << "C INTERFACE: stw_onLoadStrecke"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->onLoadStrecke();
	}

	__declspec(dllexport) int stw_getEvents(int* number, int** typeList, int** idList) {
		//std::cout << "C INTERFACE: stw_getEvents"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!number || !typeList || !idList) {
			return desm::ERROR_API_MISUSE;
		}
		std::vector<int> types;		
		std::vector<int> ids;		
		int rc = s_middleware->getEvents(types, ids);
		if(rc != desm::ERROR_OK) {
			return rc;
		}
		if(types.size() != ids.size()) {
			return desm::ERROR_FATAL;
		}
		if(types.empty()) {
			*number = 0;
			return desm::ERROR_OK;
		}
		*number = types.size();
		*typeList = (int*)::calloc(types.size(), sizeof(int));
		*idList = (int*)::calloc(ids.size(), sizeof(int));
		if(!*typeList || !*idList) {
			return desm::ERROR_API_MISUSE;
		}
		for(size_t i = 0; i < types.size(); ++i) {
			(*typeList)[i] = types[i];
			(*idList)[i] = ids[i];
		}
		return desm::ERROR_OK;
	}

	__declspec(dllexport) int stw_getSignal(int signalId, int* stellung) {
		//std::cout << "C INTERFACE: stw_getSignal"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!stellung) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->getSignal(signalId, *stellung);
	}

	__declspec(dllexport) int stw_getBalise(int baliseId, int* stellung, char** protokoll) {
		//std::cout << "C INTERFACE: stw_getBalise"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!baliseId || !stellung || !protokoll) {
			return desm::ERROR_API_MISUSE;
		}
		std::string protokollTmp;
		int rc = s_middleware->getBalise(baliseId, *stellung, protokollTmp);
		if(rc != desm::ERROR_OK) {
			return rc;
		}
		*protokoll = ::_strdup(protokollTmp.c_str());
		return desm::ERROR_OK;
	}

	__declspec(dllexport) int stw_getLoop(int baliseId, int gleisId, double positionVon, double positionBis) {
		//std::cout << "C INTERFACE: stw_getLoop"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!baliseId || !gleisId || !positionVon || !positionBis) {
			return desm::ERROR_API_MISUSE;
		}
		std::string protokollAsString;
		int rc = s_middleware->getLoop(baliseId, gleisId, positionVon, positionBis);
		if(rc != desm::ERROR_OK) {
			return rc;
		}
		return s_middleware->getLoop(baliseId, gleisId, positionVon, positionBis);
	}

	__declspec(dllexport) int stw_getWeiche(int weicheId, int* gleisId) {
		//std::cout << "C INTERFACE: stw_getWeiche"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!weicheId || !gleisId) {
			return desm::ERROR_API_MISUSE;
		}
		return s_middleware->getWeiche(weicheId, *gleisId);
	}

	__declspec(dllexport) int stw_setTrainPosition(int trainTyp, int direction, double* positionList, int positionListLen, int* gleisList, int gleisListLen) {
		//std::cout << "C INTERFACE: stw_setTrainPosition"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		std::vector<double> _positionList;
		for(int i = 0; i < positionListLen; ++i) {
			_positionList.push_back(positionList[i]);
		}
		std::vector<int> _gleisList;
		for(int i = 0; i < gleisListLen; ++i) {
			_gleisList.push_back(gleisList[i]);
		}
		return s_middleware->setTrainPosition(trainTyp, direction, _positionList, _gleisList);
	}
	
	__declspec(dllexport) int stw_getTrainPosition(int* trainTyp, int* direction, double** positionList, int* positionListLen, int** gleisList, int* gleisListLen) {
		//std::cout << "C INTERFACE: stw_getTrainPosition"<< std::endl;
		if(s_middleware == NULL) {
			return desm::ERROR_API_MISUSE;
		}
		if(!trainTyp || !direction || !positionList || !positionListLen || !gleisList || !gleisListLen) {
			return desm::ERROR_API_MISUSE;
		}

		std::vector<double> _positionList;
		std::vector<int> _gleisList;
		int rc = s_middleware->getTrainPosition(*trainTyp, *direction, _positionList, _gleisList);
		if(rc != desm::ERROR_OK) {
			return rc;
		}

		*positionListLen = _positionList.size();
		*positionList = (double*)malloc(sizeof(double) * _positionList.size());
		*gleisListLen = _gleisList.size();
		*gleisList = (int*)malloc(sizeof(int) * _gleisList.size());

		for(size_t i = 0; i < _positionList.size(); ++i) {
			(*positionList)[i] = _positionList[i];
		}
		for(size_t i = 0; i < _gleisList.size(); ++i) {
			(*gleisList)[i] = _gleisList[i];
		}

		return desm::ERROR_OK;
	}

	__declspec(dllexport) void stw_deallocate(void** p) {
		//std::cout << "C INTERFACE: stw_deallocate"<< std::endl;
		if(p && *p) {
			free(*p);
			*p = NULL;
		}
	}

};

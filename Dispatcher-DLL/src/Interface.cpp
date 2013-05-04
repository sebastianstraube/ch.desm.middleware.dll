#include <iostream>
#include <string>
#include "Middleware.h"

static desm::Middleware* s_middleware = NULL;
static const char* s_info_name = "DLL DESMMiddleware";
static const char* s_info_version = "0.13";
static const char* s_info_desc = "";

extern "C" {

	__declspec(dllexport) int stw_onStartProgramm(char* configPath) {
		std::cout << "stw_onStartProgramm"<< std::endl;
		if(s_middleware != NULL) {
			return 1; // TODO: correct error code
		}
		try {
			s_middleware = new desm::Middleware(configPath);
			return 0;
		} catch(const std::bad_alloc& e) {
			s_middleware = NULL;
			std::cerr << "EXCEPTION: " << e.what() << std::endl;
			return 1; // TODO error code
		} catch(...) {
			s_middleware = NULL;
			std::cerr << "UNKNOWN EXCEPTION!" << std::endl;
			return 1; // TODO error code
		}
	}

	__declspec(dllexport) int stw_onStopProgramm(void) {
		std::cout << "stw_onStopProgramm"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		delete s_middleware;
		s_middleware = NULL;
		return 0;
	}

	__declspec(dllexport) const char* stw_infoVersion(void) {
		std::cout << "stw_infoVersion"<< std::endl;
		return s_info_version;
	}

	__declspec(dllexport) const char* stw_infoName(void) {
		std::cout << "stw_infoName"<< std::endl;
		return s_info_name;
	}

	__declspec(dllexport) const char* stw_infoDescription(void) {
		std::cout << "stw_infoDescription"<< std::endl;
		return s_info_desc;
	}

	__declspec(dllexport) int stw_onStartSimulation(void) {
		std::cout << "stw_onStartSimulation"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->onStartSimulation();
	}

	__declspec(dllexport) int stw_onStopSimulation(void) {
		std::cout << "stw_onStopSimulation"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->onStopSimulation();
	}

	__declspec(dllexport) int stw_setTrack(int gleisId, double von, double bis, float abstand, char* name) {
		std::cout << "stw_setTrack"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->setTrack(gleisId, von, bis, abstand, std::string(name));
	}

	__declspec(dllexport) int stw_setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, char* name, int weiche1Id, int weiche2Id) {
		std::cout << "stw_setTrackConnection"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->setTrackConnection(gleisId, gleis1, gleis2, von, bis, std::string(name), weiche1Id, weiche2Id);
	}

	__declspec(dllexport) int stw_setSignal(int signalId, int gleisId, double position, int typ, float hoehe, float distanz, char* name, int direction) {
		std::cout << "stw_setSignal"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->setSignal(signalId, gleisId, position, typ, hoehe, distanz, std::string(name), direction);
	}

	__declspec(dllexport) int stw_setBalise(int gleisId, double position, int baliseId, int direction) {
		std::cout << "stw_setBalise"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->setBalise(gleisId, position, baliseId, direction);
	}

	__declspec(dllexport) int stw_setLoop(int gleisId, double positionVon, double positionBis, int baliseId) {
		std::cout << "stw_setLoop"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->setLoop(gleisId, positionVon, positionBis, baliseId);
	}

	__declspec(dllexport) int stw_setIsolierstoss(int gleisId, double position) {
		std::cout << "stw_setIsolierstoss"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->setIsolierstoss(gleisId, position);
	}
	
	__declspec(dllexport) int stw_setKilometerDirection(int direction) {
		std::cout << "stw_setKilometerDirection"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		s_middleware->setKilometerDirection(direction);
		return 0;
	}

	__declspec(dllexport) int stw_getKilometerDirection(int *direction) {
		std::cout << "stw_getKilometerDirection"<< std::endl;
		if(s_middleware == NULL || direction == NULL) {
			return 1; // TODO error code
		}
		*direction = s_middleware->getKilometerDirection();
		return 0;
	}

	__declspec(dllexport) int stw_onLoadStrecke(void) {
		std::cout << "stw_onLoadStrecke"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		return s_middleware->onLoadStrecke();
	}

	__declspec(dllexport) int stw_getEvents(int* number, int** typeList, int** idList) {
		std::cout << "stw_getEvents"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		if(!number || !typeList || !idList) {
			return 1; // TODO error code
		}
		desm::Middleware::tTypeList types;
		int rc = s_middleware->getEvents(types);
		if(rc != 0) {
			return rc;
		}
		if(types.empty()) {
			*number = 0;
			return 0;
		}
		*number = types.size();
		*typeList = (int*)::calloc(types.size(), sizeof(int));
		*idList = (int*)::calloc(types.size(), sizeof(int));
		if(!*typeList || !*idList) {
			return 1; // TODO error code
		}
		for(size_t i = 0; i < types.size(); ++i) {
			(*typeList)[i] = types[i].first;
			(*idList)[i] = types[i].second;
		}
		return 0;
	}

	__declspec(dllexport) int stw_getSignal(int signalId, int* stellung) {
		std::cout << "stw_getSignal"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		if(!stellung) {
			return 1; // TODO error code
		}
		return s_middleware->getSignal(signalId, *stellung);
	}

	__declspec(dllexport) int stw_getBalise(int baliseId, int* stellung, char** protokoll) {
		std::cout << "stw_getBalise"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		if(!stellung || !protokoll) {
			return 1; // TODO error code
		}
		std::string protokollTmp;
		int rc = s_middleware->getBalise(baliseId, *stellung, protokollTmp);
		if(rc != 0) {
			return rc;
		}
		*protokoll = ::_strdup(protokollTmp.c_str());
		return 0;
	}

	__declspec(dllexport) int stw_getLoop(int baliseId, int* stellung, char** protokoll) {
		std::cout << "stw_getLoop"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		if(!stellung || !protokoll) {
			return 1; // TODO error code
		}
		std::string protokollTmp;
		int rc = s_middleware->getLoop(baliseId, *stellung, protokollTmp);
		if(rc != 0) {
			return rc;
		}
		*protokoll = ::_strdup(protokollTmp.c_str());
		return 0;
	}

	__declspec(dllexport) int stw_getWeiche(int weicheId, int* gleisId) {
		std::cout << "stw_getWeiche"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		if(!gleisId) {
			return 1; // TODO error code
		}
		return s_middleware->getWeiche(weicheId, *gleisId);
	}

	// TODO are the two input lists NULL terminated?
	__declspec(dllexport) int stw_setTrainPosition(int train, int direction, double* positionList, int* gleisList) {
		std::cout << "stw_setTrainPosition"<< std::endl;
		if(s_middleware == NULL) {
			return 1; // TODO error code
		}
		std::vector<double> _positionList;
		for(double* it = positionList; *it; ++it) {
			_positionList.push_back(*it);
		}
		std::vector<int> _gleisList;
		for(int* it = gleisList; *it; ++it) {
			_gleisList.push_back(*it);
		}
		return s_middleware->setTrainPosition(train, direction, _positionList, _gleisList);
	}

	__declspec(dllexport) void stw_deallocate(void** p) {
		std::cout << "stw_deallocate"<< std::endl;
		if(p && *p) {
			free(*p);
			*p = NULL;
		}
	}

};

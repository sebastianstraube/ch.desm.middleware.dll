#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getEvents1(int* anzahlEvents, int** typeList, int*** paramList)
	{
		if(anzahlEvents) {
			try {
				*anzahlEvents = 0;
			} catch(...) {
				std::cerr << "Error while assigning anzahlEvents" << std::endl;
			}
		}
		// NOTE: deprecated!
		return desm::ERROR_OK;
	}

	__declspec(dllexport) int stw_getEvents(int* anzahlEvents, int** typeList, int*** paramList)
	{
		if(!anzahlEvents || !typeList || !paramList) {
			return desm::ERROR_API_MISUSE;
		}
		
		std::vector<int> types;
		std::vector<std::vector<int>> params;
		desm::Middleware::get().getEvents(types, params);
		
		if(types.size() != params.size()) {
			return desm::ERROR_FATAL;
		}

		if(types.empty()) {
			*anzahlEvents = 0;
			return desm::ERROR_OK;
		}

		*anzahlEvents = types.size();
		*typeList = (int*)::calloc(types.size(), sizeof(int));
		*paramList = (int**)::calloc(params.size(), sizeof(int));
		if(!*typeList || !*paramList) {
			return desm::ERROR_API_MISUSE;
		}

		for(size_t i = 0; i < types.size(); ++i) {
			(*typeList)[i] = types[i];
			std::vector<int> eventParams = params[i];
			(*paramList)[i] = (int*)::calloc(eventParams.size(), sizeof(int));
			for(size_t j = 0; j < eventParams.size(); ++j) {
				(*paramList)[i][j] = eventParams[j];
			}
		}

		return desm::ERROR_OK;
	}
	
	JNIEXPORT void JNICALL Java_ch_desm_Dll_getEvents0(JNIEnv* env, jobject obj, jobject al)
	{
		std::vector<int> types;
		std::vector<std::vector<int>> params;
		// NOTE: use getEvents() again when finally fixed!
		//desm::Middleware::get().getEvents(types, params);
		desm::Middleware::get().getEvents(types, params);

		if(types.size() != params.size()) {
			desm::util::jni::throwExceptionWithMessage(env, "malformed events received");
		}

		for(size_t i = 0;  i < types.size(); ++i) {
			jobject alEvt = desm::util::jni::newArrayList(env);
			desm::util::jni::addToArrayList<int>(env, alEvt, types[i]);
			const std::vector<int>& evtParams = params[i];
			for(size_t j = 0;  j < evtParams.size(); ++j) {
				desm::util::jni::addToArrayList<int>(env, alEvt, evtParams[j]);
			}
			desm::util::jni::addObjectToArrayList(env, al, alEvt);
		}
	}
};

#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
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
};

#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getEvents(int* anzahlEvents, int** typeList, int** idList)
	{
		if(!anzahlEvents || !typeList || !idList) {
			return desm::ERROR_API_MISUSE;
		}
		
		std::vector<int> types;
		std::vector<int> ids;
		desm::Middleware::get().getEvents(types, ids);
		
		if(types.size() != ids.size()) {
			return desm::ERROR_FATAL;
		}

		if(types.empty()) {
			*anzahlEvents = 0;
			return desm::ERROR_OK;
		}

		*anzahlEvents = types.size();
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
};

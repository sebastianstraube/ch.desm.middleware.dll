#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getSignal(int signalId, int* stellung)
	{
		if(!stellung) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*stellung = desm::util::jsonGet<int>(v, "stellung");

		return desm::ERROR_OK;
	}
};

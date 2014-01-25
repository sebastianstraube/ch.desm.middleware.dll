#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getLoop(int baliseId, int* gleisId, double* von, double* bis)
	{
		if(!gleisId || !von || !bis) {
			return desm::ERROR_API_MISUSE;
		}

		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_LOOP, baliseId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*gleisId = desm::util::jsonGet<int>(v, "gleisId");
		*von = desm::util::jsonGet<double>(v, "von");
		*bis = desm::util::jsonGet<double>(v, "bis");

		return desm::ERROR_OK;
	}
};
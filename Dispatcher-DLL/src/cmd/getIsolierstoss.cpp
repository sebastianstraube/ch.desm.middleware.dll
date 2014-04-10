#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getIsolierstoss(int isolierstossId, int* gleisId, double* position)
	{
		if(!gleisId || !position) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_ISOLIERSTOSS, isolierstossId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*gleisId = desm::util::jsonGet<int>(v, "gleisId");
		*position = desm::util::jsonGet<double>(v, "position");

		return desm::ERROR_OK;
	}
};

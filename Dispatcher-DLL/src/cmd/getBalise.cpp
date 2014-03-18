#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getBalise(int baliseId, int* gleisId, double* position,
		int* stellung, int* beeinflussendeSignalId1, int* beeinflussendeSignalId2)
	{
		if(!gleisId || !position || !stellung || !beeinflussendeSignalId1 || !beeinflussendeSignalId2) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_BALISE, baliseId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*gleisId = desm::util::jsonGet<int>(v, "gleisId");
		*position = desm::util::jsonGet<double>(v, "position");
		*stellung = desm::util::jsonGet<int>(v, "stellung");
		*beeinflussendeSignalId1 = desm::util::jsonGet<int>(v, "beeinflussendeSignalId1");
		*beeinflussendeSignalId2 = desm::util::jsonGet<int>(v, "beeinflussendeSignalId2");

		return desm::ERROR_OK;
	}
};

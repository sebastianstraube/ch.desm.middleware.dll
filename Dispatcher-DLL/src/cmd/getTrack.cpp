#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getTrack(int gleisId, double* von, double* bis,
		double* abstand, char* nameBuf, int nameBufLen, int* nameStrLen)
	{
		if(!von || !bis || !abstand || !nameBuf || !nameStrLen) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_TRACK, gleisId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*von = desm::util::jsonGet<double>(v, "von");
		*bis = desm::util::jsonGet<double>(v, "bis");
		*abstand = desm::util::jsonGet<double>(v, "abstand");
		std::string name = desm::util::jsonGet<std::string>(v, "name");
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}
};

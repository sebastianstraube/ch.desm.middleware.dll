#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getSignal(int signalId, int* gleisId, double* position, int* typ,
		double* hoehe, double* distanz, char* nameBuf, int nameBufLen, int* nameStrLen, int* stellung)
	{
		if(!gleisId || !position || !typ || !hoehe || !distanz || !nameBuf || !nameStrLen || !stellung) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*gleisId = desm::util::jsonGet<int>(v, "gleisId");
		*position = desm::util::jsonGet<double>(v, "position");
		*typ = desm::util::jsonGet<int>(v, "typ");
		*hoehe = desm::util::jsonGet<double>(v, "hoehe");
		*distanz = desm::util::jsonGet<double>(v, "distanz");
		*stellung = desm::util::jsonGet<int>(v, "stellung");
		std::string name = desm::util::jsonGet<std::string>(v, "name");
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}
};

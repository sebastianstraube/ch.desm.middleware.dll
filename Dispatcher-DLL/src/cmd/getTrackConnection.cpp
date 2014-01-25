#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getTrackConnection(int trackConnectionId, int* gleisId, int* gleis1, int* gleis2,
		double* von, double* bis, char* nameBuf, int nameBufLen, int* nameStrLen, int* weiche1Id, int* weiche2Id)
	{
		if(!trackConnectionId || !gleis1 || !gleis2 || !von || !bis || !nameBuf || !nameStrLen || !weiche1Id || !weiche2Id) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_TRACK_CONNECTION, trackConnectionId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*gleisId = desm::util::jsonGet<int>(v, "gleisId");
		*gleis1 = desm::util::jsonGet<int>(v, "gleis1");
		*gleis2 = desm::util::jsonGet<int>(v, "gleis2");
		*von = desm::util::jsonGet<double>(v, "von");
		*bis = desm::util::jsonGet<double>(v, "bis");
		*weiche1Id = desm::util::jsonGet<int>(v, "weiche1Id");
		*weiche2Id = desm::util::jsonGet<int>(v, "weiche2Id");
		std::string name = desm::util::jsonGet<std::string>(v, "name");
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}
};

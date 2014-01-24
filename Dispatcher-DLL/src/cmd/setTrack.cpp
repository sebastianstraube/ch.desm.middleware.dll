#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setTrack(int gleisId, double von, double bis, double abstand, char* name, int nameLen)
	{
		Json::Value v(Json::objectValue);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		v["abstand"] = Json::Value(abstand);
		v["name"] = Json::Value(std::string(name));
		// NOTE: ignore nameLen since name is null-terminated
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_TRACK, gleisId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setLoop(int baliseId, int gleisId, double von, double bis)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_LOOP, baliseId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};
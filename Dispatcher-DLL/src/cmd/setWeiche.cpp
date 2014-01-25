#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setWeiche(int weicheId, int gleisId)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_WEICHE, weicheId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

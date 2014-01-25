#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setIsolierstoss(int isolierstossId, int gleisId, double position)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["position"] = Json::Value(position);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_ISOLIERSTOSS, isolierstossId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setBalise(int baliseId, int gleisId,
		double position, int stellung, int beeinflussendeSignalId1, int beeinflussendeSignalId2)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["position"] = Json::Value(position);
		v["stellung"] = Json::Value(stellung);
		v["beeinflussendeSignalId1"] = Json::Value(beeinflussendeSignalId1);
		v["beeinflussendeSignalId2"] = Json::Value(beeinflussendeSignalId2);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_BALISE, baliseId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

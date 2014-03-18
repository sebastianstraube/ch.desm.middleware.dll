#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setLoop(int baliseId, int gleisId,
		double von, double bis, int stellung, int beeinflussendeSignalId1, int beeinflussendeSignalId2)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		v["stellung"] = Json::Value(stellung);
		v["beeinflussendeSignalId1"] = Json::Value(beeinflussendeSignalId1);
		v["beeinflussendeSignalId2"] = Json::Value(beeinflussendeSignalId2);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_LOOP, baliseId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};
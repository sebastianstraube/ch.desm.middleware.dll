#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setTrackConnection(int trackConnectionId, int gleisId, int gleis1, int gleis2,
		double von, double bis, char* name, int nameLen, int weiche1Id, int weiche2Id)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["gleis1"] = Json::Value(gleis1);
		v["gleis2"] = Json::Value(gleis2);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		v["name"] = Json::Value(std::string(name));
		v["weiche1Id"] = Json::Value(weiche1Id);
		v["weiche2Id"] = Json::Value(weiche2Id);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_TRACK_CONNECTION, trackConnectionId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

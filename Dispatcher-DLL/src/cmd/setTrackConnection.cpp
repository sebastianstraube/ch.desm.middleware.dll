#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setTrackConnection(int gleis1Id, int gleis2Id,
		double von, double bis, char* name, int nameLen, int weiche1Id, int weiche2Id)
	{
		Json::Value v(Json::objectValue);
		v["gleis1Id"] = Json::Value(gleis1Id);
		v["gleis2Id"] = Json::Value(gleis2Id);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		v["name"] = Json::Value(std::string(name));
		v["weiche1Id"] = Json::Value(weiche1Id);
		v["weiche2Id"] = Json::Value(weiche2Id);
		
		std::vector<int> params;
		params.push_back(gleis1Id);
		params.push_back(gleis2Id);

		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_TRACK_CONNECTION, params, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

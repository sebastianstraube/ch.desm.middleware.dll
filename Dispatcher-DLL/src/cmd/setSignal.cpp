#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setSignal(int signalId, int gleisId, double position,
		int typ, double hoehe, double distanz, char* name, int nameLen, int stellung)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["position"] = Json::Value(position);
		v["typ"] = Json::Value(typ);
		v["hoehe"] = Json::Value(hoehe);
		v["distanz"] = Json::Value(distanz);
		v["name"] = Json::Value(std::string(name));
		v["stellung"] = Json::Value(stellung);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

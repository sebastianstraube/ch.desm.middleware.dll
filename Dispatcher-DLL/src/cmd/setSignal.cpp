#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setSignal(int signalId, char* name, int stellung)
	{
		Json::Value v(Json::objectValue);
		v["stellung"] = Json::Value(stellung);
		v["name"] = Json::Value(std::string(name));

		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

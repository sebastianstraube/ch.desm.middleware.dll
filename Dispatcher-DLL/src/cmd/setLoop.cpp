#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/Json.h"

extern "C" {
	__declspec(dllexport) int stw_setLoop(int baliseId, int stellung, char* protokoll, int protokollLen)
	{
		Json::Value v(Json::objectValue);
		v["baliseId"] = Json::Value(baliseId);
		v["stellung"] = Json::Value(stellung);
		v["protokoll"] = Json::Value(std::string(protokoll));
		// NOTE: ignore Len since name is null-terminated
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_LOOP, baliseId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};
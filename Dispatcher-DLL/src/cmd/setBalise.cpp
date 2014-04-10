#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setBalise(int baliseId, int stellung, char* protokoll, int protokollLen)
	{
		Json::Value v(Json::objectValue);
		v["baliseId"] = Json::Value(baliseId);
		v["stellung"] = Json::Value(stellung);
		v["protokoll"] = Json::Value(std::string(protokoll));
		// NOTE: ignore Len since name is null-terminated
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_BALISE, baliseId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

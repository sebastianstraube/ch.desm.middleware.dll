#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

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

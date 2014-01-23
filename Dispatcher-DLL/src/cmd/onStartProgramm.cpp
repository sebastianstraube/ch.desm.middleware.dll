#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"

extern "C" {
	__declspec(dllexport) int stw_onStartProgramm(char* configPath) {
		std::string _configPath(configPath);
		if(!desm::Middleware::init(_configPath)) {
			return desm::ERROR_FATAL;
		}
		
		// TODO: is it really start simulation command?
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_STARTSIMULATION, desm::INVALID_ID, Json::Value())) {
			return desm::ERROR_API_MISUSE;
		}
		
		return desm::ERROR_OK;
	}
};

#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"

extern "C" {
	__declspec(dllexport) int stw_onStartProgramm(char* configPath)
	{
		std::string _configPath(configPath);
		if(!desm::Middleware::init(_configPath)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

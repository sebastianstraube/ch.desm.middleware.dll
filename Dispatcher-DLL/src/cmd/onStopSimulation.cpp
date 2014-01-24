#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"

extern "C" {
	__declspec(dllexport) int stw_onStopSimulation()
	{
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_STOPSIMULATION)) {
			return desm::ERROR_API_MISUSE;
		}
		return desm::ERROR_OK;
	}
};

#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"

extern "C" {
	__declspec(dllexport) int stw_onLoadStrecke()
	{
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_LOADSTRECKE)) {
			return desm::ERROR_API_MISUSE;
		}
		return desm::ERROR_OK;
	}
};

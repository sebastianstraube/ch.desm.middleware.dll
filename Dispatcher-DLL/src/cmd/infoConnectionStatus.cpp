#include <stdafx.h>

#include "Desm.h"

extern "C" {
	__declspec(dllexport) const char* stw_infoConnectionStatus(int* infoConnectionStatusLen)
	{
		*infoConnectionStatusLen = strlen(desm::info::connectionStatus);
		return desm::info::connectionStatus;
	}
};

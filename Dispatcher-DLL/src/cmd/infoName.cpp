#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoName(char* nameBuf, int nameBufLen, int* nameStrLen)
	{
		std::string name = desm::info::name;
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}
};

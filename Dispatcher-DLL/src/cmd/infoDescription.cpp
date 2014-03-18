#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoDescription(char* descBuf, int descBufLen, int* descStrLen)
	{
		std::string desc = desm::info::desc;
		*descStrLen = desm::util::strlcpy(descBuf, desc.c_str(), descBufLen);

		return desm::ERROR_OK;
	}
};

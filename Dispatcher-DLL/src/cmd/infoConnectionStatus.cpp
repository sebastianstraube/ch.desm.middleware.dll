#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoConnectionStatus(char* statusBuf, int statusBufLen, int* statusStrLen)
	{
		std::string status = "-NOT IMPLEMENTED-";
		*statusStrLen = desm::util::strlcpy(statusBuf, status.c_str(), statusBufLen);

		return desm::ERROR_OK;
	}
};

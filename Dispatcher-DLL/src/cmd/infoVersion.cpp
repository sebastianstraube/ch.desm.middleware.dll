#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoVersion(char* versionBuf, int versionBufLen, int* versionStrLen)
	{
		std::string version = desm::info::version;
		*versionStrLen = desm::util::strlcpy(versionBuf, version.c_str(), versionBufLen);

		return desm::ERROR_OK;
	}
};

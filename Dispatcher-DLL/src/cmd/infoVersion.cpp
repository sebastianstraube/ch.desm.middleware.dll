#include <stdafx.h>

#include "Desm.h"

extern "C" {
	__declspec(dllexport) const char* stw_infoVersion(int* versionLen)
	{
		*versionLen = strlen(desm::info::version);
		return desm::info::version;
	}
};

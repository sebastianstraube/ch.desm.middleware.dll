#include <stdafx.h>

#include "Desm.h"

extern "C" {
	__declspec(dllexport) const char* stw_infoName(int* infoNameLen) {
		*infoNameLen = strlen(desm::info::name);
		return desm::info::name;
	}
};

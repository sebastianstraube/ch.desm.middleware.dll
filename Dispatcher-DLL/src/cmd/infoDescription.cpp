#include <stdafx.h>

#include "Desm.h"

extern "C" {
	__declspec(dllexport) const char* stw_infoDescription(int* descriptionLen) {
		*descriptionLen = strlen(desm::info::desc);
		return desm::info::desc;
	}
};

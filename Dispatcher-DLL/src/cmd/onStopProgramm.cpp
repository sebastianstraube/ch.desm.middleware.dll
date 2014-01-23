#include <stdafx.h>

#include "Desm.h"
#include "Middleware.h"

extern "C" {
	__declspec(dllexport) int stw_onStopProgramm() {
		desm::Middleware::deinit();
		return desm::ERROR_OK;
	}
};

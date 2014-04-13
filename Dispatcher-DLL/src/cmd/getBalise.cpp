#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getBalise(int baliseId, int* stellung, char* protokollBuf, int protokollBufLen, int* protokollStrLen)
	{
		if(!baliseId || !stellung || !protokollBuf || !protokollStrLen) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_BALISE, baliseId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*stellung = desm::util::jsonGet<int>(v, "stellung");		
		std::string protokoll = desm::util::jsonGet<std::string>(v, "protokoll");
		*protokollStrLen = desm::util::strlcpy(protokollBuf, protokoll.c_str(), protokollBufLen);

		return desm::ERROR_OK;
	}
};

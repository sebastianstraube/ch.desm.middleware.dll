#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"

extern "C" {
	__declspec(dllexport) int stw_onLoadStrecke()
	{
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_LOADSTRECKE)) {
			return desm::ERROR_API_MISUSE;
		}
		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_desm_Dll_onLoadStrecke(JNIEnv* env, jobject obj)
	{
		desm::util::jni::checkReturnCode(env, stw_onLoadStrecke());
	}
};

#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"

extern "C" {
	__declspec(dllexport) int stw_onStopProgramm()
	{
		desm::Middleware::deinit();
		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_desm_Dll_onStopProgramm(JNIEnv* env, jobject obj)
	{
		desm::util::jni::checkReturnCode(env, stw_onStopProgramm());
	}
};

#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"

extern "C" {
	__declspec(dllexport) int stw_onStartProgramm(char* configPath)
	{
		std::string _configPath(configPath);
		if(!desm::Middleware::init(_configPath)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_desm_Dll_onStartProgramm(JNIEnv* env, jobject obj, jstring configPath)
	{
		const char* path = env->GetStringUTFChars(configPath, NULL);
		int rc = stw_onStartProgramm(const_cast<char*>(path));
		desm::util::jni::checkReturnCode(env, rc);
	}
};

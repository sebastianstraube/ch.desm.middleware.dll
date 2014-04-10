#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoDescription(char* descBuf, int descBufLen, int* descStrLen)
	{
		std::string desc = desm::info::desc;
		*descStrLen = desm::util::strlcpy(descBuf, desc.c_str(), descBufLen);

		return desm::ERROR_OK;
	}
	
	JNIEXPORT jstring JNICALL Java_desm_Dll_infoDescription(JNIEnv* env, jobject obj)
	{
		char info[1024];
		int infoLen;
		int rc = stw_infoDescription(info, 1024, &infoLen);
		desm::util::jni::checkReturnCode(env, rc);
		jstring result = env->NewStringUTF(info);
		return result;
	}
};

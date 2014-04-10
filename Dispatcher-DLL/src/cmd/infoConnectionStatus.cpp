#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoConnectionStatus(char* statusBuf, int statusBufLen, int* statusStrLen)
	{
		std::string status = "-NOT IMPLEMENTED-";
		*statusStrLen = desm::util::strlcpy(statusBuf, status.c_str(), statusBufLen);

		return desm::ERROR_OK;
	}

	JNIEXPORT jstring JNICALL Java_desm_Dll_infoConnectionStatus(JNIEnv* env, jobject obj)
	{
		char info[1024];
		int infoLen;
		int rc = stw_infoConnectionStatus(info, 1024, &infoLen);
		desm::util::jni::checkReturnCode(env, rc);
		jstring result = env->NewStringUTF(info);
		return result;
	}
};

#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoVersion(char* versionBuf, int versionBufLen, int* versionStrLen)
	{
		std::string version = desm::info::version;
		*versionStrLen = desm::util::strlcpy(versionBuf, version.c_str(), versionBufLen);

		return desm::ERROR_OK;
	}

	JNIEXPORT jstring JNICALL Java_ch_desm_Dll_infoVersion(JNIEnv* env, jobject obj)
	{
		char info[1024];
		int infoLen;
		int rc = stw_infoVersion(info, 1024, &infoLen);
		desm::util::jni::checkReturnCode(env, rc);
		jstring result = env->NewStringUTF(info);
		return result;
	}
};

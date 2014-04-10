#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_infoName(char* nameBuf, int nameBufLen, int* nameStrLen)
	{
		std::string name = desm::info::name;
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}

	JNIEXPORT jstring JNICALL Java_desm_Dll_infoName(JNIEnv* env, jobject obj)
	{
		char info[1024];
		int infoLen;
		int rc = stw_infoName(info, 1024, &infoLen);
		desm::util::jni::checkReturnCode(env, rc);
		jstring result = env->NewStringUTF(info);
		return result;
	}
};

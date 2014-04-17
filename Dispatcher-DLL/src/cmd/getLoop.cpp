#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getLoop(int baliseId, int* stellung, char* protokollBuf, int protokollBufLen, int* protokollStrLen)
	{
		if(!baliseId || !stellung || !protokollBuf || !protokollStrLen) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_LOOP, baliseId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*stellung = desm::util::jsonGet<int>(v, "stellung");		
		std::string protokoll = desm::util::jsonGet<std::string>(v, "protokoll");
		*protokollStrLen = desm::util::strlcpy(protokollBuf, protokoll.c_str(), protokollBufLen);

		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_ch_desm_Dll_getLoop0(JNIEnv* env, jobject obj, jint baliseId, jobject al)
	{
		int stellung;
		char protokoll[1024];
		int protokollLen;
		int rc = stw_getLoop(baliseId, &stellung, &protokoll[0], 1024, &protokollLen);
		desm::util::jni::checkReturnCode(env, rc);
		desm::util::jni::addToArrayList<int>(env, al, stellung);
		desm::util::jni::addToArrayList<std::string>(env, al, std::string(protokoll));
	}

};
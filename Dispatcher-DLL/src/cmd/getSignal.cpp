#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getSignal(int signalId, char* nameBuf, int nameBufLen, int* nameStrLen, int* stellung)
	{
		if(!stellung) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*stellung = desm::util::jsonGet<int>(v, "stellung");
		std::string name = desm::util::jsonGet<std::string>(v, "name");
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_ch_desm_Dll_getSignal0(JNIEnv* env, jobject obj, jint signalId, jobject al)
	{
		char name[1024];
		int nameLen;
		int stellung;
		int rc = stw_getSignal(signalId, &name[0], 1024, &nameLen, &stellung);
		desm::util::jni::checkReturnCode(env, rc);
		desm::util::jni::addToArrayList<std::string>(env, al, std::string(name));
		desm::util::jni::addToArrayList<int>(env, al, stellung);
	}

};

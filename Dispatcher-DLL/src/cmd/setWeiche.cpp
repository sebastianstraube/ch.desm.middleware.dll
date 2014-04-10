#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setWeiche(int weicheId, int gleisId)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_WEICHE, weicheId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_desm_Dll_setWeiche(JNIEnv* env, jobject obj, jint weicheId, jint gleisId)
	{
		desm::util::jni::checkReturnCode(env, stw_setWeiche(weicheId, gleisId));
	}
};

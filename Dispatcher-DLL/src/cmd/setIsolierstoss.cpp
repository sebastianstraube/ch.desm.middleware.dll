#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setIsolierstoss(int isolierstossId, int gleisId, double position)
	{
		// NOTE: deprecated!
		return desm::ERROR_OK;
	}

	__declspec(dllexport) int stw_setIsolierstoss2(int isolierstossId, int gleisId, double position)
	{
		Json::Value v(Json::objectValue);
		v["gleisId"] = Json::Value(gleisId);
		v["position"] = Json::Value(position);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_ISOLIERSTOSS, isolierstossId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
	
	JNIEXPORT void JNICALL Java_ch_desm_Dll_setIsolierstoss(JNIEnv* env, jobject obj, jint isolierstossId, jint gleisId, jdouble position)
	{
		desm::util::jni::checkReturnCode(env, stw_setIsolierstoss(isolierstossId, gleisId, position));
	}
};

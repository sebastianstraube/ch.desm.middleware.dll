#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setKilometerDirection(int richtung)
	{
		Json::Value v(Json::objectValue);
		v["richtung"] = Json::Value(richtung);
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_KILOMETER_DIRECTION, desm::INVALID_ID, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_desm_Dll_setKilometerDirection(JNIEnv* env, jobject obj, jint richtung)
	{
		desm::util::jni::checkReturnCode(env, stw_setKilometerDirection(richtung));
	}
};
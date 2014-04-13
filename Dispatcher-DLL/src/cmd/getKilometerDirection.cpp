#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getKilometerDirection(int* richtung)
	{
		if(!richtung) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_KILOMETER_DIRECTION, desm::INVALID_ID, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*richtung = desm::util::jsonGet<int>(v, "richtung");

		return desm::ERROR_OK;
	}

	JNIEXPORT jint JNICALL Java_ch_desm_Dll_getKilometerDirection(JNIEnv* env, jobject obj)
	{
		int richtung;
		int rc = stw_getKilometerDirection(&richtung);
		desm::util::jni::checkReturnCode(env, rc);
		return richtung;
	}

};

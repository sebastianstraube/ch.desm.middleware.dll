#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setTrack(int gleisId, double von, double bis, double abstand, char* name, int nameLen)
	{
		Json::Value v(Json::objectValue);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		v["abstand"] = Json::Value(abstand);
		v["name"] = Json::Value(std::string(name));
		// NOTE: ignore nameLen since name is null-terminated
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_TRACK, gleisId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
	
	JNIEXPORT void JNICALL Java_ch_desm_Dll_setTrack(JNIEnv* env, jobject obj, jint gleisId, jdouble von, jdouble bis, jdouble abstand, jstring name)
	{
		const char* nameStr = env->GetStringUTFChars(name, 0);
		desm::util::jni::checkReturnCode(env, stw_setTrack(gleisId, von, bis, abstand, const_cast<char*>(nameStr), 0));
		env->ReleaseStringUTFChars(name, nameStr);
	}
};

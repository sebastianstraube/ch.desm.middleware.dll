#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setSignal(int signalId, int gleisId, double position, int typ, double hoehe, double distanz, char* name, int nameLen, int stellung)
	{
		Json::Value v(Json::objectValue);
		v["stellung"] = Json::Value(stellung);
		v["name"] = Json::Value(std::string(name));

		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}

	__declspec(dllexport) int stw_setSignal2(int signalId, char* name, int stellung)
	{
		Json::Value v(Json::objectValue);
		v["stellung"] = Json::Value(stellung);
		v["name"] = Json::Value(std::string(name));

		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_SIGNAL, signalId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}

	JNIEXPORT void JNICALL Java_ch_desm_Dll_setSignal(JNIEnv* env, jobject obj, jint signalId, jstring name, jint stellung)
	{
		const char* nameStr = env->GetStringUTFChars(name, 0);
		desm::util::jni::checkReturnCode(env, stw_setSignal2(signalId, const_cast<char*>(nameStr), stellung));
		env->ReleaseStringUTFChars(name, nameStr);
	}
};

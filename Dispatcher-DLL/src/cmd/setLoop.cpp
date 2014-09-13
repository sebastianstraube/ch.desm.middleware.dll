#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setLoop(int baliseId, int gleisId, double von, double bis, int stellung, int beeinflussendeSignalId1, int beeinflussendeSignalId2)
	{
		// NOTE: deprecated!
		return desm::ERROR_OK;
	}

	__declspec(dllexport) int stw_setLoop2(int baliseId, int stellung, char* protokoll, int protokollLen)
	{
		Json::Value v(Json::objectValue);
		v["baliseId"] = Json::Value(baliseId);
		v["stellung"] = Json::Value(stellung);
		v["protokoll"] = Json::Value(std::string(protokoll));
		// NOTE: ignore Len since name is null-terminated
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_LOOP, baliseId, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
	
	JNIEXPORT void JNICALL Java_ch_desm_Dll_setLoop(JNIEnv* env, jobject obj, jint baliseId, jint stellung, jstring protokoll)
	{
		const char* protokollStr = env->GetStringUTFChars(protokoll, 0);
		desm::util::jni::checkReturnCode(env, stw_setLoop2(baliseId, stellung, const_cast<char*>(protokollStr), 0));
		env->ReleaseStringUTFChars(protokoll, protokollStr);
	}
};
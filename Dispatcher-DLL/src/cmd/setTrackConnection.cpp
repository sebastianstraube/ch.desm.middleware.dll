#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setTrackConnection(int gleisBasisId, int gleis1Id, int gleis2Id,
		double von, double bis, char* name, int nameLen, int weiche1Id, int weiche2Id)
	{
		Json::Value v(Json::objectValue);
		v["gleisBasisId"] = Json::Value(gleis1Id);
		v["gleis1Id"] = Json::Value(gleis1Id);
		v["gleis2Id"] = Json::Value(gleis2Id);
		v["von"] = Json::Value(von);
		v["bis"] = Json::Value(bis);
		v["name"] = Json::Value(std::string(name));
		v["weiche1Id"] = Json::Value(weiche1Id);
		v["weiche2Id"] = Json::Value(weiche2Id);
		
		std::vector<int> params;
		params.push_back(gleis1Id);
		params.push_back(gleis2Id);

		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_TRACK_CONNECTION, params, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
	
	JNIEXPORT void JNICALL Java_ch_desm_Dll_setTrackConnection(JNIEnv* env, jobject obj, jint gleisBasisId,
		jint gleis1Id, jint gleis2Id, jdouble von, jdouble bis, jstring name, jint weiche1Id, jint weiche2Id)
	{
		const char* nameStr = env->GetStringUTFChars(name, 0);
		desm::util::jni::checkReturnCode(env, stw_setTrackConnection(gleisBasisId, gleis1Id, gleis2Id, von, bis, const_cast<char*>(nameStr), 0, weiche1Id, weiche2Id));
		env->ReleaseStringUTFChars(name, nameStr);
	}
};

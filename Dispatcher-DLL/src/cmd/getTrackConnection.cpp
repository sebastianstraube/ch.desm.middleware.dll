#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	// TODO: is param order really correct?
	__declspec(dllexport) int stw_getTrackConnection(int* gleisBasisId, int gleis1Id, int gleis2Id,
		double* von, double* bis, char* nameBuf, int nameBufLen, int* nameStrLen, int* weiche1Id, int* weiche2Id)
	{
		if(!gleisBasisId ||!gleis1Id || !gleis2Id || !von || !bis || !nameBuf || !nameStrLen || !weiche1Id || !weiche2Id) {
			return desm::ERROR_API_MISUSE;
		}
		
		std::vector<int> params;
		params.push_back(gleis1Id);
		params.push_back(gleis2Id);

		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_TRACK_CONNECTION, params, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*gleisBasisId = desm::util::jsonGet<int>(v, "gleisBasisId");
		*von = desm::util::jsonGet<double>(v, "von");
		*bis = desm::util::jsonGet<double>(v, "bis");
		*weiche1Id = desm::util::jsonGet<int>(v, "weiche1Id");
		*weiche2Id = desm::util::jsonGet<int>(v, "weiche2Id");
		std::string name = desm::util::jsonGet<std::string>(v, "name");
		*nameStrLen = desm::util::strlcpy(nameBuf, name.c_str(), nameBufLen);

		return desm::ERROR_OK;
	}
	
	JNIEXPORT void JNICALL Java_ch_desm_Dll_getTrackConnection0(JNIEnv* env, jobject obj, jint gleis1Id, jint gleis2Id, jobject al)
	{
		int gleisBasisId;
		double von;
		double bis;
		char name[1024];
		int nameLen;
		int weiche1Id;
		int weiche2Id;
		int rc = stw_getTrackConnection(&gleisBasisId, gleis1Id, gleis2Id, &von, &bis, &name[0], 1024, &nameLen, &weiche1Id, &weiche2Id);
		desm::util::jni::checkReturnCode(env, rc);
		desm::util::jni::addToArrayList<double>(env, al, gleisBasisId);
		desm::util::jni::addToArrayList<double>(env, al, von);
		desm::util::jni::addToArrayList<double>(env, al, bis);
		desm::util::jni::addToArrayList<std::string>(env, al, std::string(name));
		desm::util::jni::addToArrayList<int>(env, al, weiche1Id);
		desm::util::jni::addToArrayList<int>(env, al, weiche2Id);
	}

};

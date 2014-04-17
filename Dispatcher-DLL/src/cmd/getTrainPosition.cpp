#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

static int getTrainPositionImpl(int trainTyp, int& direction, std::vector<double>& positions, std::vector<int>& gleisList) {
	Json::Value v;
	if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_TRAINPOSITION, trainTyp, v)) {
		return desm::ERROR_API_MISUSE;
	}
	direction = desm::util::jsonGet<int>(v, "direction");
	positions = desm::util::jsonGet<std::vector<double>>(v, "positionList");
	gleisList = desm::util::jsonGet<std::vector<int>>(v, "gleisList"); 
	return desm::ERROR_OK;
}

extern "C" {
	__declspec(dllexport) int stw_getTrainPosition(int trainTyp, int* direction,
		double** positionList, int* positionListLen, int** gleisList, int* gleisListLen)
	{
		if(!direction || !positionList || !positionListLen || !gleisList || !gleisListLen) {
			return desm::ERROR_API_MISUSE;
		}
		
		std::vector<double> _positionList;
		std::vector<int> _gleisList;
		int rc = getTrainPositionImpl(trainTyp, *direction, _positionList, _gleisList);
		if(rc != desm::ERROR_OK) {
			return rc;
		}
		
		*positionListLen = _positionList.size();
		*gleisListLen = _gleisList.size();

		*positionList = (double*)malloc(sizeof(double) * _positionList.size());
		*gleisList = (int*)malloc(sizeof(int) * _gleisList.size());

		for(size_t i = 0; i < _positionList.size(); ++i) {
			(*positionList)[i] = _positionList[i];
		}
		for(size_t i = 0; i < _gleisList.size(); ++i) {
			(*gleisList)[i] = _gleisList[i];
		}

		return desm::ERROR_OK;
	}
	
	// NOTE: code duplication 
	JNIEXPORT void JNICALL Java_ch_desm_Dll_getTrainPosition0(JNIEnv* env, jobject obj, jint trainTyp, jobject alParams, jobject alPositions, jobject alGleisList)
	{
		
		int direction;
		std::vector<double> positions;
		std::vector<int> gleisList;
		int rc = getTrainPositionImpl(trainTyp, direction, positions, gleisList);
		desm::util::jni::checkReturnCode(env, rc);
		
		desm::util::jni::addToArrayList<int>(env, alParams, direction);
		
		for(size_t i = 0; i < positions.size(); ++i) {
			desm::util::jni::addToArrayList<double>(env, alPositions, positions[i]);
		}

		for(size_t i = 0; i < gleisList.size(); ++i) {
			desm::util::jni::addToArrayList<int>(env, alGleisList, gleisList[i]);
		}
	}

};

#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_getTrainPosition(int trainTyp, int* direction,
		double** positionList, int* positionListLen, int** gleisList, int* gleisListLen)
	{
		if(!direction || !positionList || !positionListLen || !gleisList || !gleisListLen) {
			return desm::ERROR_API_MISUSE;
		}
		
		Json::Value v;
		if(!desm::Middleware::get().getCommand(desm::ENUM_CMD_TRAINPOSITION, trainTyp, v)) {
			return desm::ERROR_API_MISUSE;
		}

		*direction = desm::util::jsonGet<int>(v, "direction");
		std::vector<double> _positionList = desm::util::jsonGet<std::vector<double>>(v, "positionList");
		std::vector<int> _gleisList = desm::util::jsonGet<std::vector<int>>(v, "gleisList"); 

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
};

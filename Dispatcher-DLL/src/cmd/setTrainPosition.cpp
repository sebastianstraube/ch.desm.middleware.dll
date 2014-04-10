#include <stdafx.h>

#include <jni.h>

#include "Desm.h"
#include "Middleware.h"
#include "util/JavaJni.h"
#include "util/Json.h"
#include "util/String.h"

extern "C" {
	__declspec(dllexport) int stw_setTrainPosition(int trainTyp, int direction,
		double* positionList, int positionListLen, int* gleisList, int gleisListLen)
	{
		std::vector<double> _positionList;
		for(int i = 0; i < positionListLen; ++i) {
			_positionList.push_back(positionList[i]);
		}
		std::vector<int> _gleisList;
		for(int i = 0; i < gleisListLen; ++i) {
			_gleisList.push_back(gleisList[i]);
		}

		Json::Value v(Json::objectValue);
		v["direction"] = Json::Value(direction);
		v["positionList"] = desm::util::stlContainerToJsonArray(_positionList);
		v["gleisList"] = desm::util::stlContainerToJsonArray(_gleisList);  
		
		if(!desm::Middleware::get().sendCommand(desm::ENUM_CMD_TRAINPOSITION, trainTyp, v)) {
			return desm::ERROR_FATAL;
		}
		
		return desm::ERROR_OK;
	}
};

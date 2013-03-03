#include <stdafx.h>
#include <iostream>
#include <string>

extern "C" {

	__declspec(dllexport) int stw_onStartProgramm (char* configPath){
		std::cout << "stw_onStartProgramm"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_onStopProgramm (void){
		std::cout << "stw_onStopProgramm"<< std::endl;
		return 0;
	}

	__declspec(dllexport) const char* stw_infoVersion(void){
		std::cout << "stw_infoVersion"<< std::endl;
		return "0.13";
	}

	__declspec(dllexport) char* stw_infoName(void){
		std::cout << "DLL DESMMiddleware"<< std::endl;
		return 0;
	}

	__declspec(dllexport) char* stw_infoDescription(void){
		std::cout << "stw_infoDescription"<< std::endl;
		return "stw_infoDescription";
	}

	__declspec(dllexport) int stw_onStartSimulation (void){
		std::cout << "stw_onStartSimulation"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_onStopSimulation (void){
		std::cout << "stw_onStopSimulation"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setTrack (int gleisId, double von, double bis, float abstand, char* name){
		std::cout << "stw_setTrack"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setTrackConnection (int gleisId, int gleis1, int gleis2, double von, double bis, char* name, int weiche1Id, int weiche2Id){
		std::cout << "stw_setTrackConnection"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setSignal (int signalId, int gleisId, double position, int typ, float hoehe, float distanz, char* name, int direction){
		std::cout << "stw_setSignal"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setBalise (int gleisId, double position, int baliseId, int direction){
		std::cout << "stw_setBalise"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setLoop (int gleisId, double positionVon, double positionBis, int baliseId){
		std::cout << "stw_setLoop"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setIsolierstoss (int gleisId, double position){
		std::cout << "stw_setIsolierstoss"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setKilometerDirection (int direction){
		std::cout << "stw_setKilometerDirection"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_onLoadStrecke (void){
		std::cout << "stw_onLoadStrecke"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_getEvents(int* number, int** typeList, int** idList){
		std::cout << "stw_getEvents"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_getSignal (int signalId, int* stellung){
		std::cout << "stw_getSignal"<< std::endl;
		return 0;
	}


	__declspec(dllexport) int stw_getBalise (int baliseId, int* stellung, char** protokoll){
		std::cout << "stw_getBalise"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_getLoop (int baliseId, int* stellung, char** protokoll){
		std::cout << "stw_getLoop"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_getWeiche (int weicheId, int* gleisId){
		std::cout << "stw_getWeiche"<< std::endl;
		return 0;
	}

	__declspec(dllexport) int stw_setTrainPosition (int train, int direction, double** positionList, int** gleisList){
		std::cout << "stw_setTrainPosition"<< std::endl;
		return 0;
	}

	__declspec(dllexport) void stw_deallocate(void**){
		std::cout << "stw_deallocate"<< std::endl;
	}

};

int main()
{
	std::cout << "DLL compilation successfull!" << std::endl;
	system("PAUSE");

	return 0;
}
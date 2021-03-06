#pragma once

#include "Desm.h"
#include "MwDll.h"

namespace desm {
	class TestFunctionsMiddleware{
	private:
		MwDll& dll;

	public:
		TestFunctionsMiddleware(MwDll& _dll);

	public:
		bool testInfoVersion();
		bool testInfoName();
		bool testInfoDescription();
		bool testInfoConnectionStatus();

		void testOnStartProgramm();
		void testOnStopProgramm();

		//ON command
		void testOnStartSimulation();
		void testOnStopSimulation();
		void testOnLoadStrecke();
		//SET command
		bool testSetTrack();
		bool testSetTrackConnection();
		bool testSetSignal();
		bool testSetBalise();
		bool testSetLoop();
		bool testSetIsolierstoss();
		bool testSetKilometerDirection();
		bool testSetTrainPosition();
		//GET command - ONLY VALID WHEN SET 
		bool testGetKilometerDirection();
		bool testGetEvents();
		bool testGetSignal();
		bool testGetBalise();
		bool testGetLoop();
		bool testGetWeiche();
		
		// UNDOCUMENTED - MIDDLEWARE FUNCTIONS
		bool testGetTrainPosition();
		bool testGetIsolierstoss();
		bool testGetTrackConnection();
		bool testGetTrack();
		bool testSetWeiche();
	};
}
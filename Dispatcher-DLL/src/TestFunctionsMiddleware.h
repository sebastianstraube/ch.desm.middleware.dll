#pragma once

#include "Events.h"
#include "MwDll.h"

namespace desm {
	class TestFunctionsMiddleware{

	public:
		TestFunctionsMiddleware(MwDll dll);

	public:
		std::string testInfoVersion();
		std::string testInfoName();
		std::string testInfoDescription();
		
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
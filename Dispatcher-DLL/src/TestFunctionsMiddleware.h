#pragma once

namespace desm {
	class TestFunctionsMiddleware{
	public:
		TestFunctionsMiddleware(void);
		~TestFunctionsMiddleware(void);

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
		bool testSetTrack(MwDll dll);
		bool testSetTrackConnection(MwDll dll);
		bool testSetSignal(MwDll dll);
		bool testSetBalise(MwDll dll);
		bool testSetLoop(MwDll dll);
		bool testSetIsolierstoss(MwDll dll);
		bool testSetKilometerDirection(MwDll dll);
		bool testSetTrainPosition(MwDll dll);
		//GET command - ONLY VALID WHEN SET 
		bool testGetKilometerDirection(MwDll dll);
		bool testGetEvents(MwDll dll);
		bool testGetSignal(MwDll dll);
		bool testGetBalise(MwDll dll);
		bool testGetLoop(MwDll dll);
		bool testGetWeiche(MwDll dll);
		
		// UNDOCUMENTED - MIDDLEWARE FUNCTIONS
		bool testGetTrainPosition(MwDll dll);
		bool testGetIsolierstoss(MwDll dll);
		bool testGetTrackConnection(MwDll dll);
		bool testGetTrack(MwDll dll);
		bool testSetWeiche(MwDll dll);
	};
}
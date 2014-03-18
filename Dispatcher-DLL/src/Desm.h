#pragma once

#include <cassert>
#include <tchar.h>
#include <stdio.h>
#include <string.h>

#include <iostream>
#include <limits>
#include <list>
#include <map>
#include <set>
#include <vector>

#include <json/json.h>

namespace desm {

	namespace info {
		static const char* name = "DLL DESM-Middleware";
		static const char* version = "0.17";
		static const char* desc = "This DLL provides a server to client communication via tcp/ip protocol.";
	};
	
	static const int INVALID_ID = -999;
	
	static const unsigned int DEFAULT_TIMEOUT = 10 * 1000;
	static const unsigned short MAX_PORT = 49151;

	enum eMode {
		MODE_SERVER = 1,
		MODE_CLIENT = 2,
	};

	enum eErrorCode {
		ERROR_OK = 0,
		ERROR_FATAL = 1,
		ERROR_API_MISUSE = 2,
		ERROR_UNKNOWN_ID = 3,
	};

	enum eEvent {
		ENUM_CMD_TRACK = 1,
		ENUM_CMD_TRACK_CONNECTION = 2,
		ENUM_CMD_ISOLIERSTOSS = 3,
		ENUM_CMD_KILOMETER_DIRECTION = 4,
		ENUM_CMD_BALISE = 5,
		ENUM_CMD_SIGNAL = 6,
		ENUM_CMD_LOOP = 7,
		ENUM_CMD_WEICHE = 8,
		ENUM_CMD_TRAINPOSITION = 9,
		ENUM_CMD_STARTSIMULATION = 10,
		ENUM_CMD_STOPSIMULATION = 11,
		ENUM_CMD_LOADSTRECKE = 12
	};                     
};
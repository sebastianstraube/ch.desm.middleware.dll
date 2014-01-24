#pragma once

#include <map>
#include <string>
#include <vector>

#include <json/json.h>

#include "Desm.h"
#include "util/Thread.h"
#include "util/SecureQueue.h"

namespace desm {

	class CommunicationController;

	class Middleware
	{
	public: // singleton
		static bool init(const std::string& configPath);
		static void deinit();
		static Middleware& get();
	private:
		static Middleware* s_singleton;

	private: // lifetime
		Middleware(const std::string& configPath);
	public:
		~Middleware();

	public:
		void getEvents(std::vector<int>& types, std::vector<int>& ids);
		bool sendCommand(int type, int id = INVALID_ID, const Json::Value& v = Json::Value());
		bool getCommand(int type, int id, Json::Value& v);

	private: // types
		typedef Thread<typename Middleware, void*> tFetchThread;

		typedef std::pair<int, int> tChangeInfo;
		typedef SecureQueue<tChangeInfo> tChangeList;

		typedef std::map<int, Json::Value> tCommandMap;
		typedef std::map<int, tCommandMap> tState;

	private: // communication helper
		DWORD fetch(void*);
		void parseMessage(const std::string& msg);
		void storeCommand(int type, int id, const Json::Value& v);
		bool sendMessage(const Json::Value& v);
		void resetState();
		
	private: // member
		CommunicationController* m_cc;
		tFetchThread* m_fetchThread;
		bool m_fetchThreadStop;
		tState m_state;
		tChangeList m_incomingChanges;
	};

};
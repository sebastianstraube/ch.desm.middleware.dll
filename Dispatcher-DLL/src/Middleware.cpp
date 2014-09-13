#include <stdafx.h>

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

#include "Config.h"
#include "Middleware.h"
#include "CommunicationController.h"
#include "util/Thread.h"
#include "util/Json.h"
#include "util/SecureQueue.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {
	
	Middleware* Middleware::s_singleton = NULL;

	bool Middleware::init(const std::string& configPath) {
		assert(Middleware::s_singleton == NULL);
		if(Middleware::s_singleton) {
			std::cerr << "duplicate middleware initialization forbidden!" << std::endl;
			return false;
		}
		try {
			Middleware::s_singleton = new Middleware(configPath);
			return true;
		} catch(const std::bad_alloc& e) {
			Middleware::s_singleton = NULL;
			std::cerr << "EXCEPTION: " << e.what() << std::endl;
			return false;
		} catch(...) {
			Middleware::s_singleton = NULL;
			std::cerr << "UNKNOWN EXCEPTION!" << std::endl;
			return false;
		}
	}
	
	//TODO
	// at locsim initialization
	// assert failure middleware:s_singleton != NULL
	void Middleware::deinit() {
		assert(Middleware::s_singleton != NULL);
		delete Middleware::s_singleton;
		Middleware::s_singleton = NULL;
	}

	
	//TODO
	// at locsim initialization
	// assert failure middleware:s_singleton != NULL
	Middleware& Middleware::get() {
		assert(Middleware::s_singleton != NULL);
		return *Middleware::s_singleton;
	}
	
	Middleware::Middleware(const std::string& configPath)
		: m_cc(NULL)
		, m_fetchThread(NULL)
		, m_fetchThreadStop(false)
	{
		resetState();

		Config cfg(configPath);
		m_cc = new CommunicationController(cfg.getMode(), cfg.getHost(), cfg.getPort(), cfg.getTimeout());

		m_fetchThread = new tFetchThread(this, &Middleware::fetch);
		if(!m_fetchThread || !m_fetchThread->start()) {
			throw std::exception("unable to start fetch thread");
		}
	}

	Middleware::~Middleware() {
		m_fetchThreadStop = true;
		m_fetchThread->interrupt();
		m_fetchThread->join();
		delete m_fetchThread;
		delete m_cc;
	}
	
	void Middleware::getEvents(std::vector<int>& types, std::vector<std::vector<int>>& params) {
		tChangeList cmds;
		m_incomingChanges.moveTo(cmds);
		tChangeInfo change;
		while(cmds.pop(change)) {
			types.push_back(change.first);
			params.push_back(change.second);
		}
	}

	bool Middleware::sendCommand(int type, int id, const Json::Value& v) {
		std::vector<int> params;
		params.push_back(id);
		return sendCommand(type, params, v);
	}
	
	bool Middleware::sendCommand(int type, const std::vector<int>& params, const Json::Value& v) {
		storeCommand(type, params, v);

		Json::Value jsonParams(Json::arrayValue);
		jsonParams.resize(params.size());
		for(size_t i = 0; i < params.size(); ++i) {
			jsonParams[i] = params[i];
		}
		
		Json::Value root;
		root["t"] = type;
		root["p"] = jsonParams;
		root["v"] = v;
		return sendMessage(root);
	}

	static bool paramsEqual(const std::vector<int>& p1, const std::vector<int>& p2) {
		if(p1.size() != p2.size()) {
			return false;
		}
		for(size_t i = 0; i < p1.size(); ++i) {
			if(p1[i] != p2[i]) {
				return false;
			}
		}
		return true;
	}

	bool Middleware::getCommand(int type, int id, Json::Value& v) {
		std::vector<int> params;
		params.push_back(id);
		return getCommand(type, params, v);
	}

	bool Middleware::getCommand(int type, const std::vector<int>& params, Json::Value& v) {
		tState::const_iterator it = m_state.find(type);
		if(it == m_state.end()) {
			return false;
		}
		const tCommandList& cmdList = it->second;
		tCommandList::const_iterator cmdIt = cmdList.begin();
		for(; cmdIt != cmdList.end(); ++cmdIt) {
			const tParams& cmdParams = cmdIt->first;
			if(paramsEqual(cmdParams, params)) {
				v = cmdIt->second;
				return true;
			}
		}
		return false;
	}

	DWORD Middleware::fetch(void*) {
		std::string msg;
		while(!m_fetchThreadStop) {
			while(m_cc->receive(msg)) {
				parseMessage(msg);
			}
			Sleep(25);
		}
		return 0;
	}

	void Middleware::parseMessage(const std::string& msg) {
		Json::Value root;
		Json::Reader reader;
		if(!reader.parse(msg, root)) {
			std::cerr << "INVALID MESSAGE RECEIVED!" << reader.getFormatedErrorMessages() << std::endl;
			return;
		}
		int type = util::jsonGet<int>(root, "t");
		std::vector<int> params = util::jsonGet<std::vector<int>>(root, "p");
		Json::Value value = root.get("v", Json::Value());
		storeCommand(type, params, value);
		m_incomingChanges.push(std::make_pair(type, params));
	}

	void Middleware::storeCommand(int type, const std::vector<int>& params, const Json::Value& value) {
		tState::iterator it = m_state.find(type);
		if(it == m_state.end()) {
			m_state[type] = tCommandList();
		}
		tCommandList& cmdList = m_state[type];
		// NOTE: will silently overwrite existing values!
		cmdList.push_back(std::make_pair(params, value));
	}

	bool Middleware::sendMessage(const Json::Value& v) {
		return m_cc->send(Json::FastWriter().write(v));
	}

	void Middleware::resetState() {
		m_state.clear();
		m_incomingChanges.moveTo(tChangeList());
	}
};
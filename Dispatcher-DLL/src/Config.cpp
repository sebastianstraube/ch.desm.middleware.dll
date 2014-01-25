#include <stdafx.h>

#include <fstream>

#include <json/json.h>

#include "Config.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {
		
	Config::Config(const std::string& fname)
		: m_mode(MODE_CLIENT)
		, m_host("")
		, m_port(0)
		, m_timeout(DEFAULT_TIMEOUT)
	{
		if(!loadConfig(fname)) {
			throw std::exception("unable to load config");
		}
	}
	
	eMode Config::getMode() const {
		return m_mode;
	}

	const std::string Config::getHost() const {
		return m_host;
	}

	unsigned short Config::getPort() const {
		return m_port;
	}

	unsigned int Config::getTimeout() const {
		return m_timeout;
	}

	bool Config::loadConfig(const std::string& fname) {
		std::ifstream ifs;
		ifs.open(fname.c_str(), std::ifstream::in);
		if(!ifs.is_open()) {
			std::cerr << "unable to open config file at path " << fname << std::endl;
			return false;
		}
		Json::Value root;
		Json::Reader reader;
		if(!reader.parse(ifs, root)) {
			std::cerr << "error while parsing config: " << reader.getFormatedErrorMessages() << std::endl;
			return false;
		}

		std::string mode = root.get("mode", "UNKNOWN").asString();
		std::string host = root.get("host", "UNKNOWN").asString();
		unsigned int port = root.get("port", Json::Value::maxUInt).asUInt();
		unsigned int timeout = root.get("timeout", Json::Value::maxUInt).asUInt();
			
		if(mode != "server" && mode != "client") {
			std::cerr << "Config: invalid mode specified" << std::endl;
			return false;
		}

		if(host == "UNKNOWN") {
			std::cerr << "Config: invalid host " << host << " specified" << std::endl;
			return false;
		}
			
		if(port < 1 || port > MAX_PORT) {
			std::cerr << "Config: invalid port " << port << " specified" << std::endl;
			return false;
		}

		if(timeout == Json::Value::maxUInt) {
			std::cerr << "Config: timeout missing or invalid" << std::endl;
			return false;
		}

		m_mode = (mode == "server") ? MODE_SERVER : MODE_CLIENT;
		m_host = host;
		m_port = port;
		m_timeout = timeout;
		return true;
	}
};
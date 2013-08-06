#include <fstream>

#include <json/json.h>

#include "Config.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	struct Config::Impl {

		////////////////////////////////////////////////////////////////////////
		// types

		typedef CommunicationController::eMode  eMode;

		////////////////////////////////////////////////////////////////////////
		// member

		static const unsigned int s_DEFAULT_TIMEOUT = 10 * 1000;
		static const unsigned short s_MAX_PORT = 49151;

		eMode           m_mode;
		std::string     m_host;
		unsigned short  m_port;
		unsigned int    m_timeout;

		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(const std::string& fname)
			: m_mode(CommunicationController::MODE_CLIENT)
			, m_host("")
			, m_port(0)
			, m_timeout(s_DEFAULT_TIMEOUT)
		{
			if(!loadConfig(fname)) {
				throw std::exception("unable to load config");
			}
		}

		bool loadConfig(const std::string& fname) {
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
			
			if(port < 1 || port > s_MAX_PORT) {
				std::cerr << "Config: invalid port " << port << " specified" << std::endl;
				return false;
			}

			if(timeout == Json::Value::maxUInt) {
				std::cerr << "Config: timeout missing or invalid" << std::endl;
				return false;
			}

			m_mode = (mode == "server") ? CommunicationController::MODE_SERVER : CommunicationController::MODE_CLIENT;
			m_host = host;
			m_port = port;
			m_timeout = timeout;
			return true;
		}

	};

	////////////////////////////////////////////////////////////////////////////

	Config::Config(const std::string& fname)
		: pImpl(new Impl(fname))
	{
	}

	CommunicationController::eMode Config::getMode() const {
		return pImpl->m_mode;
	}

	const std::string Config::getHost() const {
		return pImpl->m_host;
	}

	unsigned short Config::getPort() const {
		return pImpl->m_port;
	}

	unsigned int Config::getTimeout() const {
		return pImpl->m_timeout;
	}

};
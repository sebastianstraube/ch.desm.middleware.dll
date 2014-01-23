#pragma once

#include "Desm.h"

namespace desm {

	class Config
	{
	public:
		Config(const std::string& name);
		eMode getMode() const;
		const std::string getHost() const;
		unsigned short getPort() const;
		unsigned int getTimeout() const;
	private:
		bool loadConfig(const std::string& fname);
	private:
		eMode m_mode;
		std::string m_host;
		unsigned short m_port;
		unsigned int m_timeout;
	};

};
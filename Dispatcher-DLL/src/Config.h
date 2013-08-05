#pragma once

#include "CommunicationController.h"

namespace desm {

	class Config
	{
	public:
		Config(const std::string& name);
		CommunicationController::eMode getMode() const;
		const std::string getHost() const;
		unsigned short getPort() const;
		unsigned int getTimeout() const;
	private:
		struct Impl;
		Impl* pImpl;
	};

};
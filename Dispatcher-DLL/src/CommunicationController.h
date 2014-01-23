#pragma once

#include <string>

#include "Desm.h"

namespace desm {

	class CommunicationController
	{
	public:
		CommunicationController(eMode mode, const std::string& host, unsigned short port, unsigned int timeout);
		~CommunicationController();
		// TODO: use std::vector<char> to avoid complications with NUL bytes?
		bool receive(std::string& data);
		bool send(const std::string& data);
		bool connected();
	private:
		struct Impl;
		Impl* pimpl;
	};

};
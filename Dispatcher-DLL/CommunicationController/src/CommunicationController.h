#pragma once

#include <string>

namespace desm {

	class CommunicationController
	{
	public:
		enum eMode {
			MODE_SERVER,
			MODE_CLIENT,
		};
		CommunicationController(eMode mode, const std::string& host, const std::string& client, unsigned short port);
		~CommunicationController();
		// TODO: use std::vector<char> to avoid complications with NULL bytes?
		bool receive(std::string& data);
		bool send(const std::string& data);
	private:
		struct Impl;
		Impl* pimpl;
	};

};
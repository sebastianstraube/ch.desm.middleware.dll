#include <tchar.h>
#include <stdio.h>
#include <string.h>

#include <czmq.h>

#include <list>
#include <queue>
#include <sstream>

#include "CommunicationController.h"
#include "CriticalSection.h"
#include "SecureQueue.h"
#include "Thread.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	namespace {
		static int POLLING_SLEEP_MS = 25;
		static char* PING_MESSAGE = "PING";
	};

	struct CommunicationController::Impl {

		////////////////////////////////////////////////////////////////////////
		// types

		typedef struct Connection;

		typedef Thread<typename CommunicationController::Impl, void*>        tMainThread;
		typedef CommunicationController::eMode                               eMode;
		typedef SecureQueue<std::string>                                     tQueue;

		////////////////////////////////////////////////////////////////////////
		// member

		eMode                   m_mode;
		std::string             m_host;
		unsigned short          m_port;
		unsigned int            m_timeout;

		bool                    m_connected;
		int64_t                 m_lastPingTs;

		tQueue                  m_sendQueue;
		tQueue                  m_recvQueue;

		tMainThread*            m_mainThread;
		bool                    m_mainThreadStop;

		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(eMode mode, const std::string& host, unsigned short port, unsigned int timeout)
			: m_mode(mode)
			, m_host(host)
			, m_port(port)
			, m_timeout(timeout)
			, m_connected(false)
			, m_lastPingTs(0)
			, m_sendQueue()
			, m_recvQueue()
			, m_mainThread(NULL)
			, m_mainThreadStop(false)
		{
			DWORD (Impl::*fct)(void*) = NULL;
			switch(mode) {
			case MODE_SERVER:
				fct = &Impl::runServer;
				break;
			case MODE_CLIENT:
				fct = &Impl::runClient;
				break;
			default:
				throw std::exception("invalid mode");
			}
			m_mainThread = new tMainThread(this, fct, NULL);
			if(!m_mainThread || !m_mainThread->start()) {
				throw std::exception("unable to start main thread");
			}
		}

		~Impl() {
			m_recvQueue.clear();
			m_sendQueue.clear();
			m_mainThreadStop = true;
			m_mainThread->interrupt();
			m_mainThread->join();
			delete m_mainThread;
		}

		////////////////////////////////////////////////////////////////////////
		// getter

		bool isConnected() {
			int64_t now = zclock_time();
			return m_connected && (now - m_lastPingTs < (int64_t)m_timeout);
		}

		////////////////////////////////////////////////////////////////////////
		// send/receive

		bool sendMessages(void* socket) {
			zmsg_t* msg = zmsg_new();
			zmsg_addstr(msg, PING_MESSAGE);

			std::string outMsg;
			while(m_sendQueue.pop(outMsg)) {
				zmsg_addstr(msg, const_cast<char*>(outMsg.c_str()));
			}

			int rc = zmsg_send(&msg, socket);
			if(rc < 0) {
				fprintf(stderr, "ERROR: %s\n", zmq_strerror(errno));
				return false;
			}

			return true;
		}

		bool receiveMessages(void* socket) {
			zmsg_t* msg = zmsg_recv(socket);
			if(!msg) {
				return true;
			}

			char* str;
			while((str = zmsg_popstr(msg)) != NULL) {
				if(strcmp(PING_MESSAGE, str) == 0) {
					m_connected = true;
					m_lastPingTs = zclock_time();
				} else {
					m_recvQueue.push(std::string(str));
				}
			}

			zmsg_destroy(&msg);

			return true;
		}

		////////////////////////////////////////////////////////////////////////
		// socket thread helper

		DWORD runClient(void*) {
			int rc = 0;
			zctx_t* ctx = zctx_new();
			void *req = zsocket_new((zctx_t*)ctx, ZMQ_REQ);

			rc = zsocket_connect(req, "tcp://%s:%d", m_host.c_str(), m_port);
			if(rc < 0)
				return 1;

			m_connected = true;

			while(!zctx_interrupted && !m_mainThreadStop && rc >= 0) {
				if(!sendMessages(req))
					break;
				if(!receiveMessages(req))
					break;
				zclock_sleep(POLLING_SLEEP_MS);
			}

			m_connected = false;

			zsocket_destroy((zctx_t*)ctx, req);
			zctx_destroy(&ctx);

			return (rc < 0) ? 1 : 0;
		}

		DWORD runServer(void*) {
			int rc = 0;
			zctx_t* ctx = zctx_new();
			void *rep = zsocket_new((zctx_t*)ctx, ZMQ_REP);

			rc = zsocket_bind(rep, "tcp://*:%d", m_port);
			if(rc < 0)
				return 1;

			m_connected = true;						

			while(!m_mainThreadStop && rc >= 0) {
				if(!receiveMessages(rep))
					break;
				if(!sendMessages(rep))
					break;
				zclock_sleep(POLLING_SLEEP_MS);
			}

			m_connected = false;						
			zsocket_destroy((zctx_t*)ctx, rep);
			zctx_destroy(&ctx);

			return (rc < 0) ? 1 : 0;
		}

	};

	////////////////////////////////////////////////////////////////////////////

	CommunicationController::CommunicationController(CommunicationController::eMode mode, const std::string& host, unsigned short port, unsigned int timeout)
		: pimpl(new Impl(mode, host, port, timeout))
	{
	}

	CommunicationController::~CommunicationController()
	{
		delete pimpl;
	}

	bool CommunicationController::receive(std::string& data)
	{
		return pimpl->m_recvQueue.pop(data);
	}

	bool CommunicationController::send(const std::string& data)
	{
		pimpl->m_sendQueue.push(data);
		return true;
	}

	bool CommunicationController::connected()
	{
		return pimpl->isConnected();
	}

};
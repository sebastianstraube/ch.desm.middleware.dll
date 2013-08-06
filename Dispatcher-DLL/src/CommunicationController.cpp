#include <tchar.h>
#include <stdio.h>
#include <string.h>

#include <zmq.h>
#include <zhelpers.h>

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

		void*                   m_zmqCtx;
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
			, m_zmqCtx(zmq_ctx_new())
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
			m_mainThread = new tMainThread(this, fct, m_zmqCtx);
			if(!m_mainThread || !m_mainThread->start()) {
				throw std::exception("unable to start main thread");
			}
		}

		~Impl() {
			m_recvQueue.clear();
			m_sendQueue.clear();
			m_mainThreadStop = true;
			zmq_ctx_destroy(m_zmqCtx); // triggers termination on connected zeromq sockets
			m_mainThread->interrupt();
			m_mainThread->join();
			delete m_mainThread;
		}
		
		////////////////////////////////////////////////////////////////////////
		// getter

		bool isConnected() {
			int64_t now = zmqh_clock();
			return m_connected && (now - m_lastPingTs < (int64_t)m_timeout);
		}

		////////////////////////////////////////////////////////////////////////
		// zero mq url helper

		std::string getServerUrl() {
			std::ostringstream ss;
			ss << "tcp://" << m_host << ":" << m_port;
			return ss.str();
		}
		
		std::string getListenUrl() {
			std::ostringstream ss;
			ss << "tcp://*:" << m_port;
			return ss.str();
		}
		
		////////////////////////////////////////////////////////////////////////
		// send/receive

		bool sendMessages(void* socket) {
			int rc = 0;
			std::string outMsg;
			while(m_sendQueue.pop(outMsg)) {
				rc = zmqh_sendmore(socket, const_cast<char*>(outMsg.c_str()));
				if(rc < 0) {
					fprintf(stderr, "ERROR: %s\n", zmq_strerror(errno));
					return false;
				}
			}
			rc = zmqh_send(socket, PING_MESSAGE);
			if(rc < 0) {
				fprintf(stderr, "ERROR: %s\n", zmq_strerror(errno));
				return false;
			}
			return true;
		}

		bool receiveMessages(void* socket) {
			do {
				char* inMsg = zmqh_recv(socket);
				if(!inMsg)
					return false;

				if(strcmp(PING_MESSAGE, inMsg) == 0) {
					m_connected = true;
					m_lastPingTs = zmqh_clock();
				} else {
					m_recvQueue.push(std::string(inMsg));
				}
				
				free(inMsg);

			} while(zmqh_has_more(socket));

			return true;
		}

		////////////////////////////////////////////////////////////////////////
		// socket thread helper

		DWORD runClient(void* ctx) {
			int rc = 0;
			void *req = zmq_socket(ctx, ZMQ_REQ);
			
			std::string url = getServerUrl();
			rc = zmq_connect(req, url.c_str());
			if(rc < 0)
				return 1;

			m_connected = true;

			while(!m_mainThreadStop && rc >= 0) {
				if(!sendMessages(req))
					break;
				if(!receiveMessages(req))
					break;
				zmqh_sleep(POLLING_SLEEP_MS);
			}

			m_connected = false;
			zmq_close(req);

			return (rc < 0) ? 1 : 0;
		}

		DWORD runServer(void* ctx) {
			int rc = 0;
			void *rep = zmq_socket(ctx, ZMQ_REP);
			
			std::string url = getListenUrl();
			rc = zmq_bind(rep, url.c_str());
			if(rc < 0)
				return 1;

			m_connected = true;						
			
			while(!m_mainThreadStop && rc >= 0) {
				if(!receiveMessages(rep))
					break;
				if(!sendMessages(rep))
					break;
				zmqh_sleep(POLLING_SLEEP_MS);
			}

			m_connected = false;						
			zmq_close(rep);

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
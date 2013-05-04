#include <tchar.h>
#include <stdio.h>
#include <string.h>

#include <iostream>
#include <sstream>
#include "Middleware.h"
#include "Thread.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	struct Middleware::Impl {

		////////////////////////////////////////////////////////////////////////
		// types

		typedef Thread<typename Middleware::Impl, void*> tFetchThread;

		////////////////////////////////////////////////////////////////////////
		// member

		CommunicationController* m_cc;
		
		tFetchThread* m_fetchThread;
		bool m_fetchThreadStop;

		// the state...
		int m_kilometerDirection;

		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(const std::string& configPath)
			: m_cc(NULL)
			, m_fetchThread(NULL)
			, m_fetchThreadStop(false)
			, m_kilometerDirection(0)
		{
			// TODO: load config
			CommunicationController::eMode mode = (configPath == "server")
				? CommunicationController::MODE_SERVER
				: CommunicationController::MODE_CLIENT;
			m_cc = new CommunicationController(mode, "127.0.0.1", 27017);

			m_fetchThread = new tFetchThread(this, &Impl::fetch);
			if(!m_fetchThread || !m_fetchThread->start()) {
				throw std::bad_alloc("unable to start fetch thread");
			}
		}

		~Impl() {
			m_fetchThreadStop = true;
			m_fetchThread->interrupt();
			m_fetchThread->join();
			delete m_fetchThread;
			delete m_cc;
		}

		DWORD fetch(void*) {
			std::string msg;
			while(!m_fetchThreadStop) {
				while(m_cc->receive(msg)) {
					parseMessage(msg);
				}
				Sleep(25);
			}
			return 0;
		}

		void parseMessage(const std::string& msg) {
			if(msg.substr(0, 2) == "KD") {
				std::string kd = msg.substr(2);
				m_kilometerDirection = atoi(kd.c_str());
				std::cout << "updated Kilometer Direction to " << m_kilometerDirection << std::endl;
			} else {
				std::cerr << "INVALID MESSAGE RECEIVED!" << std::endl;
			}
		}

		template<class T>
		void broadcastUpdate(const std::string& id, const T& v) {
			std::ostringstream ss;
			ss << id << v;
			m_cc->send(ss.str());
		}

	};

	////////////////////////////////////////////////////////////////////////////

	Middleware::Middleware(const std::string& configPath)
		: m_pImpl(new Impl(configPath))
	{
	}

	Middleware::~Middleware() {
		delete m_pImpl;
	}

	int Middleware::onLoadStrecke() {
		return 0;
	}

	int Middleware::setTrack(int gleisId, double von, double bis, float abstand, const std::string& name) {
		return 0;
	}

	int Middleware::setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id) {
		return 0;
	}

	int Middleware::setSignal (int signalId, int gleisId, double position, int typ, float hoehe, float distanz, const std::string& name, int direction) {
		return 0;
	}

	int Middleware::setBalise (int gleisId, double position, int baliseId, int direction) {
		return 0;
	}

	int Middleware::setLoop (int gleisId, double positionVon, double positionBis, int baliseId) {
		return 0;
	}

	int Middleware::setIsolierstoss (int gleisId, double position) {
		return 0;
	}
	
	void Middleware::setKilometerDirection(int direction) {
		m_pImpl->m_kilometerDirection = direction;
		m_pImpl->broadcastUpdate("KD", direction);
	}

	int Middleware::getKilometerDirection() {
		return m_pImpl->m_kilometerDirection;
	}

	int Middleware::onStartSimulation() {
		return 0;
	}

	int Middleware::getEvents(tTypeList&) {
		return 0;
	}

	int Middleware::getSignal(int signalId, int& stellung) {
		return 0;
	}

	int Middleware::getBalise(int baliseId, int& stellung, std::string& protokoll) {
		return 0;
	}

	int Middleware::getLoop(int baliseId, int& stellung, std::string& protokoll) {
		return 0;
	}

	int Middleware::getWeiche(int weicheId, int& gleisId) {
		return 0;
	}

	int Middleware::setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList) {
		return 0;
	}

	int Middleware::onStopSimulation() {
		return 0;
	}

};
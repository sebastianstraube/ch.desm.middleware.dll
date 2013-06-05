#include <tchar.h>
#include <stdio.h>
#include <string.h>

#include <iostream>
#include <limits>
#include <list>
#include <map>
#include <set>

#include <json/json.h>

#include "Middleware.h"
#include "Thread.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	enum MESSAGE_TYPE {
		MESSAGE_TYPE_SET_KILOMETER_DIRECTION = 1,
		MESSAGE_TYPE_SET_ISOLIERSTOSS
	};

	struct Middleware::Impl {

		////////////////////////////////////////////////////////////////////////
		// types

		typedef Thread<typename Middleware::Impl, void*> tFetchThread;

		struct tGleis {
			tGleis(int _id = std::numeric_limits<int>::max()) : id(_id) {}
			bool isValid() const { return this->id != std::numeric_limits<int>::max(); }
			int id;
			std::set<double> isolierstoesse;
		};

		struct tState {
			int kilometerDirection;
			std::map<int, tGleis> gleise;
		};

		////////////////////////////////////////////////////////////////////////
		// member

		CommunicationController* m_cc;
		
		tFetchThread* m_fetchThread;
		bool m_fetchThreadStop;

		tState m_state;

		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(const std::string& configPath)
			: m_cc(NULL)
			, m_fetchThread(NULL)
			, m_fetchThreadStop(false)
		{
			resetState();
			
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

		void resetState() {
			m_state.kilometerDirection = 0;
		}

		void sendMessage(int msgType, const Json::Value& v) {
			Json::FastWriter writer;
			Json::Value root;
			root["t"] = msgType;
			root["v"] = v;
			m_cc->send(writer.write(root));
		}

		void parseMessage(const std::string& msg) {
			Json::Value root;
			Json::Reader reader;
			if(!reader.parse(msg, root)) {
				std::cerr << "INVALID MESSAGE RECEIVED!" << reader.getFormatedErrorMessages() << std::endl;
				return;
			}
			unsigned msgType = root.get("t", Json::Value::maxInt).asInt();
			if(msgType == Json::Value::maxInt) {
				std::cerr << "INVALID MESSAGE (no message type available)" << std::endl;
				return;
			}
			parseJsonValue(msgType, root.get("v", Json::Value()));
		}

		void parseJsonValue(int msgType, const Json::Value& v) {
			switch(msgType) {
			case MESSAGE_TYPE_SET_KILOMETER_DIRECTION:
				if(v.isInt()) {
					m_state.kilometerDirection = v.asInt();
				}
				break;
			case MESSAGE_TYPE_SET_ISOLIERSTOSS:
				if(v.isObject()) {
					int gleisId = v.get("gleisId", Json::Value::maxInt).asInt();
					double position = v.get("position", std::numeric_limits<double>::max()).asDouble();
					if(gleisId != Json::Value::maxInt && position != std::numeric_limits<double>::max()) {
						if(m_state.gleise.find(gleisId) != m_state.gleise.end()) {
							m_state.gleise[gleisId].isolierstoesse.insert(position);
						}
					}
				}
				break;
			default:
				std::cerr << "RECEIVED UNKNOWN MESSAGE " << msgType << std::endl;
				break;
			}
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
		m_pImpl->resetState();
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
		if(m_pImpl->m_state.gleise.find(gleisId) == m_pImpl->m_state.gleise.end()) {
			// TODO fehlercode für unbekanntes gleis
			return EXIT_FAILURE;
		}
		if(m_pImpl->m_state.gleise[gleisId].isolierstoesse.find(position) == m_pImpl->m_state.gleise[gleisId].isolierstoesse.end()) {
			m_pImpl->m_state.gleise[gleisId].isolierstoesse.insert(position);
			Json::Value v(Json::objectValue);
			v["gleisId"] = Json::Value(gleisId);
			v["position"] = Json::Value(position);
			m_pImpl->sendMessage(MESSAGE_TYPE_SET_ISOLIERSTOSS, v);
		}
		return 0;
	}
	
	void Middleware::setKilometerDirection(int direction) {
		m_pImpl->m_state.kilometerDirection = direction;
		m_pImpl->sendMessage(MESSAGE_TYPE_SET_KILOMETER_DIRECTION, Json::Value(direction));
	}

	int Middleware::getKilometerDirection() {
		return m_pImpl->m_state.kilometerDirection;
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
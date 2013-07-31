#include <tchar.h>
#include <stdio.h>
#include <string.h>

#include <iostream>
#include <limits>
#include <list>
#include <map>
#include <set>
#include <vector>

#include <json/json.h>

#include "ErrorCodes.h"
#include "Middleware.h"
#include "Thread.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	enum MESSAGE_TYPE {
		MESSAGE_TYPE_SET_KILOMETER_DIRECTION = 1,
		MESSAGE_TYPE_SET_BALISE,
		MESSAGE_TYPE_SET_ISOLIERSTOSS
	};

	namespace {

		template<class T>
		void deleteContainerEntries(T& begin, T& end) {
			for(; begin != end; ++begin) {
				delete *begin;
			}
		}
		
		struct Balise {
			Balise(int _id, double _position, int _direction) : id(_id), position(_position), direction(_direction) {}
			int id;
			double position;
			int direction;
		};

		struct Gleis {
			Gleis(int _id = std::numeric_limits<int>::max()) : id(_id) {}
			~Gleis() { deleteContainerEntries(balise.begin(), balise.end()); }
			bool isValid() const { return this->id != std::numeric_limits<int>::max(); }
			int id;
			std::set<double> isolierstoesse;
			std::vector<Balise*> balise;
		};

		struct tState {
			int kilometerDirection;
			std::map<int, Gleis> gleise;
		};
	};

	struct Middleware::Impl {

		////////////////////////////////////////////////////////////////////////
		// types

		typedef Thread<typename Middleware::Impl, void*> tFetchThread;

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

#pragma region begin message parsing

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
			case MESSAGE_TYPE_SET_BALISE:
				parseSetBalise(v);
				break;
			case MESSAGE_TYPE_SET_ISOLIERSTOSS:
				parseSetIsolierstoss(v);
				break;
			default:
				std::cerr << "RECEIVED UNKNOWN MESSAGE " << msgType << std::endl;
				break;
			}
		}
		
		void parseSetBalise(const Json::Value& v) {
			if(!v.isObject()) {
				return;
			}
			int gleisId = v.get("gleisId", Json::Value::maxInt).asInt();
			int baliseId = v.get("baliseId", Json::Value::maxInt).asInt();
			int direction = v.get("direction", Json::Value::maxInt).asInt();
			double position = v.get("position", std::numeric_limits<double>::max()).asDouble();
			if(gleisId == Json::Value::maxInt || 
				baliseId == Json::Value::maxInt || 
				direction == Json::Value::maxInt || 
				position == std::numeric_limits<double>::max()) {
				return;
			}
			if(setBaliseImpl(gleisId, baliseId, position, direction)) {
				// TODO add balise event to event list for stw_getEvents();
			}
		}

		void parseSetIsolierstoss(const Json::Value& v) {
			if(!v.isObject()) {
				return;
			}
			int gleisId = v.get("gleisId", Json::Value::maxInt).asInt();
			double position = v.get("position", std::numeric_limits<double>::max()).asDouble();
			if(gleisId == Json::Value::maxInt || position == std::numeric_limits<double>::max()) {
				return;
			}
			if(setIsolierstossImpl(gleisId, position)) {
				// TODO add isolierstoss event to event list for stw_getEvents();
			}
		}

#pragma endregion

#pragma region stats checks

		bool isValidGleisId(int gleisId) const {
			return m_state.gleise.find(gleisId) != m_state.gleise.end();
		}

#pragma endregion

#pragma region state update

		bool setIsolierstossImpl(int gleisId, double position) {
			if(!isValidGleisId(gleisId)) {
				return false;
			}
			if(m_state.gleise[gleisId].isolierstoesse.find(position) != m_state.gleise[gleisId].isolierstoesse.end()) {
				return false;
			}
			m_state.gleise[gleisId].isolierstoesse.insert(position);
			return true;
		}

		bool setBaliseImpl(int gleisId, double position, int baliseId, int direction) {
			if(!isValidGleisId(gleisId)) {
				return false;
			}
			// TODO check whether baliseId already exists. maybe switch from vector to map<id, Balise*> for that?
			m_state.gleise[gleisId].balise.push_back(new Balise(baliseId, position, direction));
			return true;
		}

#pragma endregion

	};

	////////////////////////////////////////////////////////////////////////////

	Middleware::Middleware(const std::string& configPath)
		: m_pImpl(new Impl(configPath)) {}

	Middleware::~Middleware() {
		delete m_pImpl;
	}

	int Middleware::onLoadStrecke() {
		m_pImpl->resetState();
		return 0;
	}

	int Middleware::setTrack(int gleisId, double von, double bis, float abstand, const std::string& name) {
		if(!m_pImpl->isValidGleisId(gleisId)) {
			return desm::ERROR_UNKNOWN_ID;
		}
		return desm::ERROR_OK;
	}

	int Middleware::setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id) {
		if(!m_pImpl->isValidGleisId(gleisId)) {
			return desm::ERROR_UNKNOWN_ID;
		}
		return 0;
	}

	int Middleware::setSignal (int signalId, int gleisId, double position, int typ, float hoehe, float distanz, const std::string& name, int direction) {
		if(!m_pImpl->isValidGleisId(gleisId)) {
			return desm::ERROR_UNKNOWN_ID;
		}
		return 0;
	}

	int Middleware::setBalise (int gleisId, double position, int baliseId, int direction) {
		if(!m_pImpl->isValidGleisId(gleisId)) {
			return desm::ERROR_UNKNOWN_ID;
		}
		if(m_pImpl->setBaliseImpl(gleisId, position, baliseId, direction)) {
			Json::Value v(Json::objectValue);
			v["gleisId"] = Json::Value(gleisId);
			v["position"] = Json::Value(position);
			v["baliseId"] = Json::Value(baliseId);
			v["direction"] = Json::Value(direction);
			m_pImpl->sendMessage(MESSAGE_TYPE_SET_BALISE, v);
		}
		return 0;
	}

	int Middleware::setLoop (int gleisId, double positionVon, double positionBis, int baliseId) {
		if(!m_pImpl->isValidGleisId(gleisId)) {
			return desm::ERROR_UNKNOWN_ID;
		}
		return 0;
	}

	int Middleware::setIsolierstoss (int gleisId, double position) {
		if(!m_pImpl->isValidGleisId(gleisId)) {
			return desm::ERROR_UNKNOWN_ID;
		}
		if(m_pImpl->setIsolierstossImpl(gleisId, position)) {
			Json::Value v(Json::objectValue);
			v["gleisId"] = Json::Value(gleisId);
			v["position"] = Json::Value(position);
			m_pImpl->sendMessage(MESSAGE_TYPE_SET_ISOLIERSTOSS, v);
		}
		return desm::ERROR_OK;
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
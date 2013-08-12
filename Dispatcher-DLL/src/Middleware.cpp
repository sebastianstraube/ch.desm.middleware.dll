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

#include "Config.h"
#include "ErrorCodes.h"
#include "Events.h"
#include "Middleware.h"
#include "SimulationState.h"
#include "Thread.h"
#include "SecureQueue.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

#pragma region json helper

	namespace {
		template<class T> static T jsonGetAs(const Json::Value& v);
		template<> static int jsonGetAs<int>(const Json::Value& v) { return v.asInt(); }
		template<> static double jsonGetAs<double>(const Json::Value& v) { return v.asDouble(); }
		template<> static unsigned jsonGetAs<unsigned>(const Json::Value& v) { return v.asUInt(); }
		template<> static std::string jsonGetAs<std::string>(const Json::Value& v) { return v.asString(); }
		
		template<class T> static bool jsonIsOfType(const Json::Value& v);
		template<> static bool jsonIsOfType<int>(const Json::Value& v) { return v.isInt(); }
		template<> static bool jsonIsOfType<double>(const Json::Value& v) { return v.isDouble(); }
		template<> static bool jsonIsOfType<unsigned>(const Json::Value& v) { return v.isUInt(); }
		template<> static bool jsonIsOfType<std::string>(const Json::Value& v) { return v.isString(); }

		template<class T> static T jsonGet(const Json::Value& v, const char* key) {
			if(!v.isObject()) {
				throw std::exception("invalid object provided for parsing");
			}
			Json::Value x = v.get(key, Json::Value::null);
			if(!jsonIsOfType<T>(x) || x.isNull()) {
				throw std::exception("json parser error");
			}
			return jsonGetAs<T>(x);
		}
	}

#pragma endregion

	struct Middleware::Impl {

		////////////////////////////////////////////////////////////////////////
		// types

		typedef Thread<typename Middleware::Impl, void*> tFetchThread;
		
		struct CommandBase;
		typedef SecureQueue<CommandBase*> tCommandList;

		////////////////////////////////////////////////////////////////////////
		// member

		CommunicationController* m_cc;

		tFetchThread* m_fetchThread;
		bool m_fetchThreadStop;

		SimulationState m_state;
		tCommandList m_incomingCommands;

		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(const std::string& configPath)
			: m_cc(NULL)
			, m_fetchThread(NULL)
			, m_fetchThreadStop(false)
		{
			resetState();

			Config cfg(configPath);
			m_cc = new CommunicationController(cfg.getMode(), cfg.getHost(), cfg.getPort(), cfg.getTimeout());

			m_fetchThread = new tFetchThread(this, &Impl::fetch);
			if(!m_fetchThread || !m_fetchThread->start()) {
				throw std::exception("unable to start fetch thread");
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
			Json::Value root;
			Json::Reader reader;
			if(!reader.parse(msg, root)) {
				std::cerr << "INVALID MESSAGE RECEIVED!" << reader.getFormatedErrorMessages() << std::endl;
				return;
			}
			int type = jsonGet<int>(root, "t");
			Json::Value v = root.get("v", Json::Value());
			CommandBase* cmd = CommandBase::fromJson(type, v);
			if(!cmd) {
				std::cerr << "UNKNOWN MESSAGE TYPE " << type << std::endl;
				return;
			}
			if(cmd->updateState(m_state)) {
				m_incomingCommands.push(cmd);
			}
		}
		
		void sendMessage(int msgType, const Json::Value& v) {
			Json::Value root;
			root["t"] = msgType;
			root["v"] = v;
			m_cc->send(Json::FastWriter().write(root));
		}

		void resetState() {
			m_state.reset();
			tCommandList cmds;
			m_incomingCommands.moveTo(cmds);
			CommandBase* cmd;
			while(cmds.pop(cmd)) {
				delete cmd;
			}
		}
		
#pragma region begin commands

		struct CommandBase {
			eEvent type;
			CommandBase(eEvent _type) : type(_type) {}
			virtual int getId() const = 0;
			virtual bool isValid(const SimulationState&) const = 0;
			virtual Json::Value toJson() const = 0;
			virtual bool updateState(SimulationState&) const = 0;
			static CommandBase* fromJson(int type, const Json::Value& v) {
				switch(type) {
				case EVT_TRACK: return Command<EVT_TRACK>::fromJson(v);
				case EVT_ISOLIERSTOSS: return Command<EVT_ISOLIERSTOSS>::fromJson(v);
				case EVT_KILOMETER_DIRECTION: return Command<EVT_KILOMETER_DIRECTION>::fromJson(v);
				default: return NULL;
				}
			}
		};

		template<int> struct Command {};

		template<> struct Command<EVT_TRACK> : CommandBase {
			typedef Command<EVT_TRACK> tThisCommand;

			int gleisId;
			double von;
			double bis;
			double abstand;
			std::string name;
			
			Command(int _gleisId, double _von, double _bis, double _abstand, const std::string& _name)
				: CommandBase(EVT_TRACK), gleisId(_gleisId), von(_von), bis(_bis), abstand(_abstand), name(_name) {
			}
			int getId() const {
				return gleisId;
			}
			bool isValid(const SimulationState& state) const {
				return state.isValidGleisId(gleisId);
			}
			bool updateState(SimulationState& state) const {
				Gleis gleis = { gleisId, von, bis, abstand, name };
				state.gleise[gleisId] = gleis;
				return true;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["gleisId"] = Json::Value(gleisId);
				v["von"] = Json::Value(von);
				v["bis"] = Json::Value(bis);
				v["abstand"] = Json::Value(abstand);
				v["name"] = Json::Value(name);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int gleisId = jsonGet<int>(v, "gleisId");
				double von = jsonGet<double>(v, "von");
				double bis = jsonGet<double>(v, "bis");
				double abstand = jsonGet<double>(v, "abstand");
				std::string name = jsonGet<std::string>(v, "name");
				return new tThisCommand(gleisId, von, bis, abstand, name);
			}
		};

		template<> struct Command<EVT_TRACK_CONNECTION> : CommandBase {
			typedef Command<EVT_TRACK_CONNECTION> tThisCommand;

			int gleisId;
			int gleis1;
			int gleis2;
			double von;
			double bis;
			std::string name;
			int weiche1Id;
			int weiche2Id;
			
			Command(int _gleisId, int _gleis1, int _gleis2, double _von, double _bis, const std::string& _name, int _weiche1Id, int _weiche2Id)
				: CommandBase(EVT_TRACK_CONNECTION), gleisId(_gleisId), gleis1(_gleis1), gleis2(_gleis2), von(_von), bis(_bis), name(_name), weiche1Id(_weiche1Id), weiche2Id(_weiche2Id) {
			}
			int getId() const {
				return gleisId;
			}
			bool isValid(const SimulationState& state) const {
				return state.isValidGleisId(gleisId);
			}
			bool updateState(SimulationState& state) const {
				if(!state.isValidGleisId(gleisId)) {
					return false;
				}
				// TODO
				return true;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["gleisId"] = Json::Value(gleisId);
				v["gleis1"] = Json::Value(gleis1);
				v["gleis2"] = Json::Value(gleis2);
				v["von"] = Json::Value(von);
				v["bis"] = Json::Value(bis);
				v["name"] = Json::Value(name);
				v["weiche1Id"] = Json::Value(weiche1Id);
				v["weiche2Id"] = Json::Value(weiche2Id);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int gleisId = jsonGet<int>(v, "gleisId");
				int gleis1 = jsonGet<int>(v, "gleis1");
				int gleis2 = jsonGet<int>(v, "gleis2");
				double von = jsonGet<double>(v, "von");
				double bis = jsonGet<double>(v, "bis");
				std::string name = jsonGet<std::string>(v, "name");
				int weiche1Id = jsonGet<int>(v, "weiche1Id");
				int weiche2Id = jsonGet<int>(v, "weiche2Id");
				return new tThisCommand(gleisId, gleis1, gleis2, von, bis, name, weiche1Id, weiche2Id);
			}
		};
		
		template<> struct Command<EVT_ISOLIERSTOSS> : CommandBase {
			typedef Command<EVT_ISOLIERSTOSS> tThisCommand;

			int gleisId;
			double position;
			
			Command(int _gleisId, double _position)
				: CommandBase(EVT_ISOLIERSTOSS), gleisId(_gleisId), position(_position) {
			}
			int getId() const {
				// TODO is gleisId correct? i think we need the position as well to identify the isolierstoss.
				return gleisId;
			}
			bool isValid(const SimulationState& state) const {
				return state.isValidGleisId(gleisId);
			}
			bool updateState(SimulationState& state) const {
				if(!state.isValidGleisId(gleisId)) {
					return false;
				}
				state.gleise[gleisId].isolierstoesse.insert(position);
				return true;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["gleisId"] = Json::Value(gleisId);
				v["position"] = Json::Value(position);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int gleisId = jsonGet<int>(v, "gleisId");
				double position = jsonGet<double>(v, "position");
				return new tThisCommand(gleisId, position);
			}
		};
		
		template<> struct Command<EVT_KILOMETER_DIRECTION> : CommandBase {
			typedef Command<EVT_KILOMETER_DIRECTION> tThisCommand;

			int direction;
			
			Command(int _direction)
				: CommandBase(EVT_KILOMETER_DIRECTION), direction(_direction) {
			}
			int getId() const {
				return INVALID_ID;
			}
			bool isValid(const SimulationState& state) const {
				return true;
			}
			bool updateState(SimulationState& state) const {
				state.kilometerDirection = direction;
				return true;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["direction"] = Json::Value(direction);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int direction = jsonGet<int>(v, "direction");
				return new tThisCommand(direction);
			}
		};
		
#pragma endregion
		
#pragma region begin command related helper
		
		void sendEvent(const CommandBase& cmd) {
			sendMessage(cmd.type, cmd.toJson());
		}

		int applyLocalCommand(CommandBase* cmd) {
			if(!cmd->isValid(m_state)) {
				return desm::ERROR_API_MISUSE;
			}
			if(cmd->updateState(m_state)) {
				sendEvent(*cmd);
			}
			return desm::ERROR_OK;
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

	int Middleware::setTrack(int gleisId, double von, double bis, double abstand, const std::string& name) {
		return m_pImpl->applyLocalCommand(new Impl::Command<EVT_TRACK>(gleisId, von, bis, abstand, name));
	}

	int Middleware::setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id) {
		return 0;
	}

	int Middleware::setSignal (int signalId, int gleisId, double position, int typ, double hoehe, double distanz, const std::string& name, int direction) {
		return 0;
	}

	int Middleware::setBalise (int gleisId, double position, int baliseId, int direction) {
		return 0;
	}

	int Middleware::setLoop (int gleisId, double positionVon, double positionBis, int baliseId) {
		return 0;
	}

	int Middleware::setIsolierstoss (int gleisId, double position) {
		return m_pImpl->applyLocalCommand(new Impl::Command<EVT_ISOLIERSTOSS>(gleisId, position));
	}

	void Middleware::setKilometerDirection(int direction) {
		/*return */m_pImpl->applyLocalCommand(new Impl::Command<EVT_KILOMETER_DIRECTION>(direction));
	}

	int Middleware::getKilometerDirection() {
		return m_pImpl->m_state.kilometerDirection;
	}

	int Middleware::onStartSimulation() {
		return 0;
	}

	int Middleware::getEvents(std::vector<int>& types, std::vector<int>& ids) {
		Impl::tCommandList cmds;
		m_pImpl->m_incomingCommands.moveTo(cmds);

		Impl::CommandBase* cmd;
		while(cmds.pop(cmd)) {
			if(!cmd) {
				break;
			}
			types.push_back(cmd->type);
			ids.push_back(cmd->getId());
			delete cmd; // command not needed any longer, delete him.
		}
		
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
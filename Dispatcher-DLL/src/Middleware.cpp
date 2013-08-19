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
#include "Thread.h"
#include "SecureQueue.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

#pragma region json helper

	namespace {
		template<class TContainer> static Json::Value stlContainerToJsonArray(const TContainer& container) {
			Json::Value res = Json::Value(Json::arrayValue);
			TContainer::const_iterator it = container.begin();
			for(; it != container.end(); ++it) {
				res.append(Json::Value(*it));
			}
			return res;
		}

		template <class TContainer> static TContainer jsonArrayToStlContainer(const Json::Value& v) {
			TContainer res;
			for(Json::Value::iterator it = v.begin(); it != v.end(); ++it) {
				res.push_back(jsonGetAs<TContainer::value_type>(*it));
			}
			return res;
		}

		template<class T> static T jsonGetAs(const Json::Value& v);
		template<> static int jsonGetAs<int>(const Json::Value& v) { return v.asInt(); }
		template<> static double jsonGetAs<double>(const Json::Value& v) { return v.asDouble(); }
		template<> static unsigned jsonGetAs<unsigned>(const Json::Value& v) { return v.asUInt(); }
		template<> static std::string jsonGetAs<std::string>(const Json::Value& v) { return v.asString(); }
		template<> static std::vector<int> jsonGetAs<std::vector<int>>(const Json::Value& v) { return jsonArrayToStlContainer<std::vector<int>>(v); }
		template<> static std::vector<double> jsonGetAs<std::vector<double>>(const Json::Value& v) { return jsonArrayToStlContainer<std::vector<double>>(v); }

		template<class T> static bool jsonIsOfType(const Json::Value& v);
		template<> static bool jsonIsOfType<int>(const Json::Value& v) { return v.isInt(); }
		template<> static bool jsonIsOfType<double>(const Json::Value& v) { return v.isDouble(); }
		template<> static bool jsonIsOfType<unsigned>(const Json::Value& v) { return v.isUInt(); }
		template<> static bool jsonIsOfType<std::string>(const Json::Value& v) { return v.isString(); }
		template<> static bool jsonIsOfType<std::vector<int>>(const Json::Value& v) { return v.isArray(); }
		template<> static bool jsonIsOfType<std::vector<double>>(const Json::Value& v) { return v.isArray(); }

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

		typedef std::pair<eEvent, int> tChangeInfo;
		typedef SecureQueue<tChangeInfo> tChangeList;

		struct CommandBase;
		typedef std::map<int, CommandBase*> tCommandMap;
		typedef std::map<eEvent, tCommandMap> tState;

		////////////////////////////////////////////////////////////////////////
		// member

		CommunicationController* m_cc;

		tFetchThread* m_fetchThread;
		bool m_fetchThreadStop;

		tState m_state;
		tChangeList m_incomingChanges;

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

			storeCommand(cmd);
			m_incomingChanges.push(std::make_pair(cmd->type, cmd->getId()));
		}

		void sendMessage(int msgType, const Json::Value& v) {
			Json::Value root;
			root["t"] = msgType;
			root["v"] = v;
			m_cc->send(Json::FastWriter().write(root));
		}

		void resetState() {
			m_state.clear(); // TODO: memory leak - in tCommandMap sind pointer
			m_incomingChanges.moveTo(tChangeList());
		}

#pragma region begin commands

		struct CommandBase {
			eEvent type;
			CommandBase(eEvent _type) : type(_type) {}
			virtual int getId() const = 0;
			virtual Json::Value toJson() const = 0;
			static CommandBase* fromJson(int type, const Json::Value& v) {
				switch(type) {
					case ENUM_CMD_BALISE : return Command<ENUM_CMD_BALISE>::fromJson(v);
					case ENUM_CMD_TRACK: return Command<ENUM_CMD_TRACK>::fromJson(v);
					case ENUM_CMD_TRACK_CONNECTION: return Command<ENUM_CMD_TRACK_CONNECTION>::fromJson(v);
					case ENUM_CMD_ISOLIERSTOSS : return Command<ENUM_CMD_ISOLIERSTOSS>::fromJson(v);
					case ENUM_CMD_KILOMETER_DIRECTION: return Command<ENUM_CMD_KILOMETER_DIRECTION>::fromJson(v);
					case ENUM_CMD_SIGNAL : return Command<ENUM_CMD_SIGNAL>::fromJson(v);
					case ENUM_CMD_LOOP : return Command<ENUM_CMD_LOOP>::fromJson(v);
					case ENUM_CMD_WEICHE : return Command<ENUM_CMD_WEICHE>::fromJson(v);
					case ENUM_CMD_TRAINPOSITION : return Command<ENUM_CMD_TRAINPOSITION>::fromJson(v);
					case ENUM_CMD_STARTSIMULATION : return Command<ENUM_CMD_STARTSIMULATION>::fromJson(v);
					case ENUM_CMD_STOPSIMULATION : return Command<ENUM_CMD_STOPSIMULATION>::fromJson(v);
					case ENUM_CMD_LOADSTRECKE : return Command<ENUM_CMD_LOADSTRECKE>::fromJson(v);
				default: return NULL;
				}
			}
		};

		template<int> struct Command {};

		template<> struct Command<ENUM_CMD_TRACK> : CommandBase {
			typedef Command<ENUM_CMD_TRACK> tThisCommand;

			int gleisId;
			double von;
			double bis;
			double abstand;
			std::string name;

			Command(int _gleisId, double _von, double _bis, double _abstand, const std::string& _name)
				: CommandBase(ENUM_CMD_TRACK), gleisId(_gleisId), von(_von), bis(_bis), abstand(_abstand), name(_name) {
			}
			int getId() const {
				return gleisId;
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

		template<> struct Command<ENUM_CMD_TRACK_CONNECTION> : CommandBase {
			typedef Command<ENUM_CMD_TRACK_CONNECTION> tThisCommand;

			int gleisId;
			int gleis1;
			int gleis2;
			double von;
			double bis;
			std::string name;
			int weiche1Id;
			int weiche2Id;

			Command(int _gleisId, int _gleis1, int _gleis2, double _von, double _bis, const std::string& _name, int _weiche1Id, int _weiche2Id)
				: CommandBase(ENUM_CMD_TRACK_CONNECTION), gleisId(_gleisId), gleis1(_gleis1), gleis2(_gleis2), von(_von), bis(_bis), name(_name), weiche1Id(_weiche1Id), weiche2Id(_weiche2Id) {
			}
			int getId() const {
				return gleisId;
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

		template<> struct Command<ENUM_CMD_ISOLIERSTOSS> : CommandBase {
			typedef Command<ENUM_CMD_ISOLIERSTOSS> tThisCommand;

			int isolierstossId;
			int gleisId;
			double position;

			Command(int _isolierstossId, int _gleisId, double _position)
				: CommandBase(ENUM_CMD_ISOLIERSTOSS), isolierstossId(_isolierstossId), gleisId(_gleisId), position(_position) {
			}
			int getId() const {
				// TODO: is gleisId correct, i think we need the position as well to identify the isolierstoss.
				return gleisId;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["isolierstossId"] = Json::Value(isolierstossId);
				v["gleisId"] = Json::Value(gleisId);
				v["position"] = Json::Value(position);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int isolierstossId = jsonGet<int>(v, "isolierstossId");
				int gleisId = jsonGet<int>(v, "gleisId");
				double position = jsonGet<double>(v, "position");
				return new tThisCommand(isolierstossId, gleisId, position);
			}
		};

		template<> struct Command<ENUM_CMD_KILOMETER_DIRECTION> : CommandBase {
			typedef Command<ENUM_CMD_KILOMETER_DIRECTION> tThisCommand;

			int richtung;

			Command(int _richtung)
				: CommandBase(ENUM_CMD_KILOMETER_DIRECTION), richtung(_richtung) {
			}
			int getId() const {
				return INVALID_ID;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["richtung"] = Json::Value(richtung);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int richtung = jsonGet<int>(v, "richtung");
				return new tThisCommand(richtung);
			}
		};

		template<> struct Command<ENUM_CMD_LOOP> : CommandBase {
			typedef Command<ENUM_CMD_LOOP> tThisCommand;

			int gleisId;
			int baliseId;
			double positionVon;
			double positionBis;

			Command(int _gleisId, int _baliseId, double _positionVon, double _positionBis)
				: CommandBase(ENUM_CMD_LOOP), gleisId(_gleisId), baliseId(_baliseId), positionVon(_positionVon), positionBis(_positionBis) {
			}
			int getId() const {
				// TODO loop id!
				return INVALID_ID;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["gleisId"] = Json::Value(gleisId);
				v["baliseId"] = Json::Value(baliseId);
				v["positionVon"] = Json::Value(positionVon);
				v["positionBis"] = Json::Value(positionBis);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int gleisId = jsonGet<int>(v, "gleisId");
				int baliseId = jsonGet<int>(v, "baliseId");
				double positionVon = jsonGet<double>(v, "positionVon");
				double positionBis= jsonGet<double>(v, "positionBis");
				return new tThisCommand(gleisId, baliseId, positionVon, positionBis);
			}
		};

		template<> struct Command<ENUM_CMD_BALISE> : CommandBase {
			typedef Command<ENUM_CMD_BALISE> tThisCommand;

			int baliseId;
			int gleisId;
			double position;
			int stellung;
			//TODO: needs review, only needed for getBalise
			std::string protokoll;

			Command(int _baliseId, int _gleisId, double _position, int _stellung, const std::string& _protokoll)
				: CommandBase(ENUM_CMD_BALISE), baliseId(_baliseId), gleisId(_gleisId), position(_position), stellung(_stellung), protokoll(_protokoll) {
			}
			int getId() const {
				return baliseId;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["baliseId"] = Json::Value(baliseId);
				v["gleisId"] = Json::Value(gleisId);
				v["position"] = Json::Value(position);
				v["stellung"] = Json::Value(stellung);
				v["protokoll"] = Json::Value(protokoll);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int baliseId = jsonGet<int>(v, "baliseId");
				int gleisId = jsonGet<int>(v, "gleisId");
				double position = jsonGet<double>(v, "position");
				int stellung = jsonGet<int>(v, "stellung");
				std::string protokoll = jsonGet<std::string>(v, "protokoll");
				return new tThisCommand(baliseId, gleisId, position, stellung, protokoll);
			}
		};

		template<> struct Command<ENUM_CMD_SIGNAL> : CommandBase {
			typedef Command<ENUM_CMD_SIGNAL> tThisCommand;

			int signalId;
			int gleisId;
			double position;
			int typ;
			double hoehe;
			double distanz;
			std::string name;
			int stellung;

			Command(int _signalId, int _gleisId, double _position, int _typ, double _hoehe, double _distanz, const std::string& _name, int _stellung)
				: CommandBase(ENUM_CMD_SIGNAL), signalId(_signalId), gleisId(_gleisId), position(_position),  typ(_typ), hoehe(_hoehe), distanz(_distanz), name(_name), stellung(_stellung){
			}
			int getId() const {
				return signalId;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["signalId"] = Json::Value(signalId);
				v["gleisId"] = Json::Value(gleisId);
				v["position"] = Json::Value(position);
				v["typ"] = Json::Value(typ);
				v["hoehe"] = Json::Value(hoehe);
				v["distanz"] = Json::Value(distanz);
				v["name"] = Json::Value(name);
				v["stellung"] = Json::Value(stellung);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int signalId = jsonGet<int>(v, "signalId");
				int gleisId = jsonGet<int>(v, "gleisId");
				double position = jsonGet<double>(v, "position");
				int typ = jsonGet<int>(v, "typ");
				double hoehe = jsonGet<double>(v, "hoehe");
				double distanz = jsonGet<double>(v, "distanz");
				std::string name = jsonGet<std::string>(v, "name");
				int stellung = jsonGet<int>(v, "stellung");
				return new tThisCommand(signalId, gleisId, position, typ, hoehe, distanz, name, stellung);
			}
		};

		template<> struct Command<ENUM_CMD_WEICHE> : CommandBase {
			typedef Command<ENUM_CMD_WEICHE> tThisCommand;

			int weicheId;
			int& gleisId;

			Command(int _weicheId, int _gleisId)	
				: CommandBase(ENUM_CMD_WEICHE), weicheId(_weicheId), gleisId(_gleisId) {
			}
			int getId() const {
				// TODO: is gleisId correct, i think we need the position as well to identify the isolierstoss.
				return gleisId;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["weicheId"] = Json::Value(weicheId);
				v["gleisId"] = Json::Value(gleisId);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int weicheId = jsonGet<int>(v, "weicheId");
				int gleisId = jsonGet<int>(v, "gleisId");
				return new tThisCommand(weicheId, gleisId);
			}
		};

		template<> struct Command<ENUM_CMD_TRAINPOSITION> : CommandBase {
			typedef Command<ENUM_CMD_TRAINPOSITION> tThisCommand;

			int trainTyp;
			int direction;
			std::vector<double> positionList;
			std::vector<int> gleisList;

			Command(int _trainTyp, int _direction, const std::vector<double>& _positionList, const std::vector<int>& _gleisList)	
				: CommandBase(ENUM_CMD_TRAINPOSITION), trainTyp(_trainTyp), direction(_direction), positionList(_positionList), gleisList(_gleisList){
			}
			int getId() const {
				return INVALID_ID;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				v["trainTyp"] = Json::Value(trainTyp);
				v["direction"] = Json::Value(direction);
				v["positionList"] = stlContainerToJsonArray(positionList);
				v["gleisList"] = stlContainerToJsonArray(gleisList);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				int trainTyp = jsonGet<int>(v, "trainTyp");
				int direction = jsonGet<int>(v, "direction");
				std::vector<double> positionList = jsonGet<std::vector<double>>(v, "positionList");
				std::vector<int> gleisList = jsonGet<std::vector<int>>(v, "gleisList");
				return new tThisCommand(trainTyp, direction, positionList, gleisList);
			}
		};

		template<> struct Command<ENUM_CMD_STARTSIMULATION> : CommandBase {
			typedef Command<ENUM_CMD_STARTSIMULATION> tThisCommand;

			Command()	
				: CommandBase(ENUM_CMD_STARTSIMULATION){
			}
			int getId() const {
				return INVALID_ID;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				return new tThisCommand();
			}
		};

		template<> struct Command<ENUM_CMD_STOPSIMULATION> : CommandBase {
			typedef Command<ENUM_CMD_STOPSIMULATION> tThisCommand;

			Command()	
				: CommandBase(ENUM_CMD_STOPSIMULATION){
			}
			int getId() const {
				return INVALID_ID;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				return new tThisCommand();
			}
		};

		template<> struct Command<ENUM_CMD_LOADSTRECKE> : CommandBase {
			typedef Command<ENUM_CMD_LOADSTRECKE> tThisCommand;
			
			Command()	
				: CommandBase(ENUM_CMD_LOADSTRECKE){
			}
			int getId() const {
				return INVALID_ID;
			}
			Json::Value toJson() const {
				Json::Value v(Json::objectValue);			
				return v;
			}
			static tThisCommand* fromJson(const Json::Value& v) {
				return new tThisCommand();
			}
		};

#pragma endregion

#pragma region begin command related helper

		void sendEvent(const CommandBase& cmd) {
			sendMessage(cmd.type, cmd.toJson());
		}

		int storeCommand(CommandBase* cmd) {
			if(!cmd) {
				return ERROR_API_MISUSE;
			}
			eEvent type = cmd->type;
			int id = cmd->getId();
			tState::iterator it = m_state.find(type);
			if(it == m_state.end()) {
				m_state[type] = tCommandMap();
			}
			tCommandMap& cmdMap = m_state[type];
			tCommandMap::iterator cmdIt = cmdMap.find(id);
			if(cmdIt != cmdMap.end()) {
				delete cmdIt->second;
			}
			cmdMap[id] = cmd;
			return ERROR_OK;
		}

		int applyLocalCommand(CommandBase* cmd) {
			int rt = storeCommand(cmd);
			sendEvent(*cmd);
			return rt;
		}

		template<int CMD_TYPE>
		Command<CMD_TYPE>* getCommandFromState(int id = INVALID_ID) {
			tState::iterator it = m_state.find(static_cast<eEvent>(CMD_TYPE));
			if(it == m_state.end()) {
				return NULL;
			}
			tCommandMap& cmdMap = it->second;
			tCommandMap::iterator cmdIt = cmdMap.find(id);
			if(cmdIt == cmdMap.end()) {
				return NULL;
			}
			return static_cast<Command<CMD_TYPE>*>(cmdIt->second);
		}

#pragma endregion

	};

	////////////////////////////////////////////////////////////////////////////

	Middleware::Middleware(const std::string& configPath)
		: m_pImpl(new Impl(configPath)) {}

	Middleware::~Middleware() {
		delete m_pImpl;
	}

	int Middleware::onStartSimulation() {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_STARTSIMULATION>());
	}

	int Middleware::onStopSimulation() {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_STOPSIMULATION>());
	}

	int Middleware::onLoadStrecke() {
		m_pImpl->resetState();
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_LOADSTRECKE>());
	}

	int Middleware::setTrack(int gleisId, double von, double bis, double abstand, const std::string& name) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_TRACK>(gleisId, von, bis, abstand, name));
	}

	int Middleware::getTrack(int gleisId, double& von, double& bis, double& abstand, std::string& name) {
		Impl::Command<ENUM_CMD_TRACK>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_TRACK>(gleisId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		von = cmd->von;
		bis = cmd->bis;
		abstand = cmd->abstand;
		name = cmd->name;
		return ERROR_OK;
	}

	int Middleware::setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_TRACK_CONNECTION>(gleisId, gleis1, gleis2, von, bis, name, weiche1Id, weiche2Id));
	}

	int Middleware::getTrackConnection(int gleisId, int& gleis1, int& gleis2, double& von, double& bis, std::string& name, int& weiche1Id, int& weiche2Id) {
		// TODO is gleisId unique enough? track connection id!
		Impl::Command<ENUM_CMD_TRACK_CONNECTION>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_TRACK_CONNECTION>(gleisId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		gleis1 = cmd->gleis1;
		gleis2 = cmd->gleis2;
		von = cmd->von;
		bis = cmd->bis;
		name = cmd->name;
		weiche1Id = cmd->weiche1Id;
		weiche2Id = cmd->weiche2Id;
		return ERROR_OK;
	}

	int Middleware::setSignal (int signalId, int gleisId, double position, int typ, double hoehe, double distanz, const std::string& name, int stellung) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_SIGNAL>(signalId, gleisId, position, typ, hoehe, distanz, name, stellung));
	}

	int Middleware::setBalise (int baliseId, int gleisId, double position, int stellung, const std::string& protokoll) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_BALISE>(baliseId, gleisId, position, stellung, protokoll));
	}

	int Middleware::setLoop (int baliseId, int gleisId, double positionVon, double positionBis) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_LOOP>(baliseId, gleisId, positionVon, positionBis));
	}

	int Middleware::setIsolierstoss (int isolierstossId, int gleisId, double position) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_ISOLIERSTOSS>(isolierstossId, gleisId, position));
	}
	
	int Middleware::getIsolierstoss(int isolierstossId, int& gleisId, double& position) {
		Impl::Command<ENUM_CMD_ISOLIERSTOSS>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_ISOLIERSTOSS>(isolierstossId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		gleisId = cmd->gleisId;
		position = cmd->position;
		return ERROR_OK;
	}

	int Middleware::setKilometerDirection(int richtung) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_KILOMETER_DIRECTION>(richtung));
	}

	int Middleware::getKilometerDirection(int& richtung) {
		Impl::Command<ENUM_CMD_KILOMETER_DIRECTION>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_KILOMETER_DIRECTION>();
		if(!cmd) {
			return ERROR_FATAL;
		}
		richtung = cmd->richtung;
		return ERROR_OK;
	}

	int Middleware::getEvents(std::vector<int>& types, std::vector<int>& ids) {
		Impl::tChangeList cmds;
		m_pImpl->m_incomingChanges.moveTo(cmds);
		Impl::tChangeInfo change;
		while(cmds.pop(change)) {
			types.push_back(change.first);
			ids.push_back(change.second);
		}
		return ERROR_OK;
	}

	int Middleware::getSignal(int signalId, int& stellung) {
		Impl::Command<ENUM_CMD_SIGNAL>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_SIGNAL>(signalId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		stellung = cmd->stellung;
		return ERROR_OK;
	}

	int Middleware::getBalise(int baliseId, int& stellung, std::string& protokoll) {
		Impl::Command<ENUM_CMD_BALISE>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_BALISE>(baliseId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		stellung = cmd->stellung;
		protokoll = cmd->protokoll;
		return ERROR_OK;
	}

	int Middleware::getLoop(int gleisId, int& baliseId, double& positionVon, double& positionBis) {
		// TODO introduce loopId?
		Impl::Command<ENUM_CMD_LOOP>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_LOOP>(gleisId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		baliseId = cmd->baliseId;
		positionVon = cmd->positionVon;
		positionBis = cmd->positionBis;
		return ERROR_OK;
	}

	int Middleware::getWeiche(int weicheId, int& gleisId) {
		Impl::Command<ENUM_CMD_WEICHE>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_WEICHE>(weicheId);
		if(!cmd) {
			return ERROR_FATAL;
		}
		gleisId = cmd->gleisId;
		return ERROR_OK;
	}
	
	int Middleware::setTrainPosition(int trainTyp, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList) {
		return m_pImpl->applyLocalCommand(new Impl::Command<ENUM_CMD_TRAINPOSITION>(trainTyp, direction, positionList, gleisList));
	}

	int Middleware::getTrainPosition(int& trainTyp, int& direction, std::vector<double>& positionList, std::vector<int>& gleisList) {
		Impl::Command<ENUM_CMD_TRAINPOSITION>* cmd = m_pImpl->getCommandFromState<ENUM_CMD_TRAINPOSITION>();
		if(!cmd) {
			return ERROR_FATAL;
		}
		trainTyp = cmd->trainTyp;
		direction = cmd->direction;
		positionList = cmd->positionList;
		gleisList = cmd->gleisList;
		return ERROR_OK;
	}

};
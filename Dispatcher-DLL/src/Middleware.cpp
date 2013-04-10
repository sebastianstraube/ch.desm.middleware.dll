#include "Middleware.h"

////////////////////////////////////////////////////////////////////////////////

namespace desm {

	struct Middleware::Impl {
		
		////////////////////////////////////////////////////////////////////////
		// member

		CommunicationController* m_cc;

		////////////////////////////////////////////////////////////////////////
		// lifetime

		Impl(const std::string& configPath)
		{
			// TODO: load config
			m_cc = new CommunicationController(CommunicationController::MODE_CLIENT, "127.0.0.1", 27017);
		}

		~Impl() {
			delete m_cc;
		}

	};

	////////////////////////////////////////////////////////////////////////////

	Middleware::Middleware(const std::string& configPath)
		: pimpl(new Impl(configPath))
	{
	}

	Middleware::~Middleware()
	{
		delete pimpl;
	}

	int Middleware::onLoadStrecke() {
		return 0;
	}

	int Middleware::setTrack(int gleisId, double von, double bis, float abstand, const std::string& name)
	{
		return 0;
	}

	int Middleware::setTrackConnection(int gleisId, int gleis1, int gleis2, double von, double bis, const std::string& name, int weiche1Id, int weiche2Id)
	{
		return 0;
	}

	int Middleware::setSignal (int signalId, int gleisId, double position, int typ, float hoehe, float distanz, const std::string& name, int direction)
	{
		return 0;
	}

	int Middleware::setBalise (int gleisId, double position, int baliseId, int direction)
	{
		return 0;
	}

	int Middleware::setLoop (int gleisId, double positionVon, double positionBis, int baliseId)
	{
		return 0;
	}

	int Middleware::setIsolierstoss (int gleisId, double position)
	{
		return 0;
	}

	int Middleware::setKilometerDirection (int direction)
	{
		return 0;
	}

	int Middleware::onStartSimulation()
	{
		return 0;
	}

	int Middleware::getEvents(tTypeList&)
	{
		return 0;
	}

	int Middleware::getSignal(int signalId, int& stellung)
	{
		return 0;
	}

	int Middleware::getBalise(int baliseId, int& stellung, std::string& protokoll)
	{
		return 0;
	}

	int Middleware::getLoop(int baliseId, int& stellung, std::string& protokoll)
	{
		return 0;
	}

	int Middleware::getWeiche(int weicheId, int& gleisId)
	{
		return 0;
	}

	int Middleware::setTrainPosition(int train, int direction, const std::vector<double>& positionList, const std::vector<int>& gleisList)
	{
		return 0;
	}

	int Middleware::onStopSimulation()
	{
		return 0;
	}

};
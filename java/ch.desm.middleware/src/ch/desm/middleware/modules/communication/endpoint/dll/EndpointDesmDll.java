package ch.desm.middleware.modules.communication.endpoint.dll;

import java.util.ArrayList;

import ch.desm.Dll;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommonListenerInterface;

public class EndpointDesmDll extends EndpointCommon {

	private Dll dll;

	public EndpointDesmDll() {
		dll = new Dll();
		dll.onStartProgramm("C:\\svn.it-hotspot.de\\Projekte\\DESM\\Simulationskomponenten\\ch.desm.middleware\\Dispatcher-DLL\\Debug\\dispatcher.json");
	}

	public Dll getDll() {
		return dll;
	}

	@Override
	public void addEndpointListener(EndpointCommonListenerInterface listener)
			throws Exception {

		if (listener instanceof EndpointDesmDllListenerInterface) {
			this.listeners.add(listener);
		} else {
			throw new Exception("Only DLL endpoints supported.");
		}

	}

	public void loop() {
		while (true) {
			ArrayList<Dll.Event> events = null;
			try {
				events = dll.getEvents();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			for (Dll.Event event : events) {
				ArrayList<Integer> params = event.params;
				try {
					switch (event.type) {
					case Dll.ENUM_CMD_ISOLIERSTOSS:
						int isolierstossId = params.get(0);
						Dll.Isolierstoss isolierstoss = dll.getIsolierstoss(isolierstossId);
						// TODO: do something
						System.out.println("ENUM_CMD_ISOLIERSTOSS: " + isolierstoss);
						break;
					case Dll.ENUM_CMD_WEICHE:
						int weicheId = params.get(0);
						Dll.Weiche weiche = dll.getWeiche(weicheId);
						// TODO: do something
						System.out.println("ENUM_CMD_WEICHE: " + weiche);
						break;
					case Dll.ENUM_CMD_TRAINPOSITION:
						int trainTyp = params.get(0);
						Dll.TrainPosition trainPosition= dll.getTrainPosition(trainTyp);
						// TODO: do something
						System.out.println("ENUM_CMD_TRAINPOSITION: " + trainPosition);
						break;
					case Dll.ENUM_CMD_SIGNAL:
						int signalId = params.get(0);
						Dll.Signal signal = dll.getSignal(signalId);
						// TODO: do something
						System.out.println("ENUM_CMD_SIGNAL: " + signal);
						break;
					case Dll.ENUM_CMD_KILOMETER_DIRECTION:
						int kilometerDirection = dll.getKilometerDirection();
						// TODO: do something
						System.out.println("ENUM_CMD_KILOMETER_DIRECTION: " + kilometerDirection);
						break;
					case Dll.ENUM_CMD_BALISE:
						int baliseIdBalise = params.get(0);
						Dll.Balise balise = dll.getBalise(baliseIdBalise);
						// TODO: do something
						System.out.println("ENUM_CMD_BALISE: " + balise);
						break;
					case Dll.ENUM_CMD_LOOP:
						int baliseIDLoop = params.get(0);
						Dll.Loop loop = dll.getLoop(baliseIDLoop);
						// TODO: do something
						System.out.println("ENUM_CMD_LOOP: " + loop);
						break;
					case Dll.ENUM_CMD_TRACK:
						int gleisId = params.get(0);
						Dll.Track track = dll.getTrack(gleisId);
						// TODO: do something
						System.out.println("ENUM_CMD_TRACK: " + track);
						break;
					case Dll.ENUM_CMD_TRACK_CONNECTION:
						int gleis1Id = params.get(0);
						int gleis2Id = params.get(1);
						Dll.TrackConnection trackConnection = dll
								.getTrackConnection(gleis1Id, gleis2Id);
						// TODO: do something
						System.out.println("ENUM_CMD_TRACK_CONNECTION: " + trackConnection);
						break;

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

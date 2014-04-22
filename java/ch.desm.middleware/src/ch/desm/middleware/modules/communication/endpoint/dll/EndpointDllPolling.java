package ch.desm.middleware.modules.communication.endpoint.dll;

import java.util.ArrayList;

import ch.desm.Dll;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObectIsolierstoss;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectBalise;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectBase;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectLoop;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectSignal;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectTrack;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectTrackConnection;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectTrainPosition;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectWeiche;
import ch.desm.middleware.modules.core.daemon.DaemonThread;

public class EndpointDllPolling extends DaemonThread {

	private int waitTimeMs;
	private Dll dll;
	private EndpointDll endpoint;

	public EndpointDllPolling(String name, int waitTimeMs, Dll locsimDll,
			EndpointDll endpointDll) {
		super(name);
		this.dll = locsimDll;
		this.endpoint = endpointDll;
		this.waitTimeMs = waitTimeMs;
	}

	@Override
	public void run() {
		try {

			while (!isInterrupted()) {
				// System.out.println("Thread: " + this.getName());
				pollingDllEvents();
				Thread.sleep(waitTimeMs);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pollingDllEvents() throws Exception {
		ArrayList<EndpointDllEvent> events = null;

		events = dll.getEvents();

		for (EndpointDllEvent event : events) {

			ArrayList<Integer> params = event.params;

			switch (event.type) {
			case EndpointDllObjectBase.ENUM_CMD_ISOLIERSTOSS:
				int isolierstossId = params.get(0);
				EndpointDllObectIsolierstoss isolierstoss = dll
						.getIsolierstoss(isolierstossId);

				endpoint.receiveEndpointObject(isolierstoss);
				break;
			case EndpointDllObjectBase.ENUM_CMD_WEICHE:
				int weicheId = params.get(0);
				EndpointDllObjectWeiche weiche = dll.getWeiche(weicheId);

				endpoint.receiveEndpointObject(weiche);
				break;
			case EndpointDllObjectBase.ENUM_CMD_TRAINPOSITION:
				int trainTyp = params.get(0);
				EndpointDllObjectTrainPosition trainPosition = dll
						.getTrainPosition(trainTyp);

				endpoint.receiveEndpointObject(trainPosition);
				break;
			case EndpointDllObjectBase.ENUM_CMD_SIGNAL:
				int signalId = params.get(0);
				EndpointDllObjectSignal signal = dll.getSignal(signalId);

				endpoint.receiveEndpointObject(signal);
				break;
			case EndpointDllObjectBase.ENUM_CMD_KILOMETER_DIRECTION:
				int kilometerDirection = dll.getKilometerDirection();

				endpoint.receiveEndpointObject(kilometerDirection);
				break;
			case EndpointDllObjectBase.ENUM_CMD_BALISE:
				int baliseIdBalise = params.get(0);
				EndpointDllObjectBalise balise = dll.getBalise(baliseIdBalise);

				endpoint.receiveEndpointObject(balise);
				break;
			case EndpointDllObjectBase.ENUM_CMD_LOOP:
				int baliseIDLoop = params.get(0);
				EndpointDllObjectLoop loop = dll.getLoop(baliseIDLoop);

				endpoint.receiveEndpointObject(loop);
				break;
			case EndpointDllObjectBase.ENUM_CMD_TRACK:
				int gleisId = params.get(0);
				EndpointDllObjectTrack track = dll.getTrack(gleisId);

				endpoint.receiveEndpointObject(track);
				break;
			case EndpointDllObjectBase.ENUM_CMD_TRACK_CONNECTION:
				int gleis1Id = params.get(0);
				int gleis2Id = params.get(1);
				EndpointDllObjectTrackConnection trackConnection = dll
						.getTrackConnection(gleis1Id, gleis2Id);

				endpoint.receiveEndpointObject(trackConnection);
				break;
			}
		}
	}
}

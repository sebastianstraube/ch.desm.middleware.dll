package ch.desm.middleware.modules.core;

import java.util.ArrayList;

import ch.desm.Dll;
import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDll;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.test.TestBaseImpl;
import ch.desm.middleware.modules.component.test.TestEndpointUbw32;

public class Main {

	public static void main(String[] args) {

	}

	public void testCaseEndpointToEndpoint() {
		Broker broker = new Broker();

		TestEndpointUbw32 testEndpoint = new TestEndpointUbw32(
				EnumSerialPorts.COM22);
		TestBaseImpl testImpl = new TestBaseImpl(broker, testEndpoint);

		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(
				EnumSerialPorts.COM8);
		Re420BaseImpl re420Impl = new Re420BaseImpl(broker, re420EndpointUbw32);

		
		while (true) {
			testImpl.emulateBrokerMessage("1.90.02;o;0;lampe;signalf;notrot;on;#");

			// testEndpoint.sendCommandPinOutput("E", "0", "1");
			re420EndpointUbw32
					.emulateEndpointMessage("I,00192,00000,00000,24560,00111,00009,00014");

		}

	}

	public void testCaseDll() {
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("java.home"));

		EndpointDesmDll endpointDll = new EndpointDesmDll();

		while (true) {
			ArrayList<Dll.Event> events = null;
			try {
				events = endpointDll.getDll().getEvents();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			for (Dll.Event event : events) {
				ArrayList<Integer> params = event.params;
				try {
					switch (event.type) {
					case Dll.ENUM_CMD_TRACK:
						int gleisId = params.get(0);
						Dll.Track track = endpointDll.getDll()
								.getTrack(gleisId);
						// TODO: do something with track...
						break;
					case Dll.ENUM_CMD_TRACK_CONNECTION:
						int gleis1Id = params.get(0);
						int gleis2Id = params.get(1);
						Dll.TrackConnection trackConnection = endpointDll
								.getDll()
								.getTrackConnection(gleis1Id, gleis2Id);
						// TODO: do something with track connection...
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}

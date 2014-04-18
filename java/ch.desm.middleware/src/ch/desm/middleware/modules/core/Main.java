package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDll;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.test.TestBaseImpl;
import ch.desm.middleware.modules.component.test.TestEndpointUbw32;

public class Main {

	public static void main(String[] args) {
		testCaseDll();
	}

	public static void testCaseEndpointToEndpoint() {
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

	public static void testCaseDll() {
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("java.home"));

		EndpointDesmDll endpointDll = new EndpointDesmDll();
		
		endpointDll.loop();

	

	}

}

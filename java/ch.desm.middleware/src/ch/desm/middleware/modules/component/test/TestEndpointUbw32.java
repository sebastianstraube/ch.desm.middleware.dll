package ch.desm.middleware.modules.component.test;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class TestEndpointUbw32 extends EndpointUbw32 {

	private TestEndpointUbw32Data configuration;

	public TestEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(
				enumSerialPort,
				TestEndpointUbw32Data.PINBITMASK_CONFIGURATION_DIGITAL,
				TestEndpointUbw32Data.PINBITMASK_INPUT_ANALOG);

		this.configuration = new TestEndpointUbw32Data();
	}

	public TestEndpointUbw32Data getConfiguration() {
		return configuration;
	}
	
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}
}

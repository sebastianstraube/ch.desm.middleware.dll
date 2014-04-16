package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class OMLEndpointUbw32 extends EndpointUbw32 {

	private OMLEndpointUbw32Configuration configuration;

	public OMLEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(
				enumSerialPort,
				OMLEndpointUbw32Configuration.PINBITMASK_CONFIGURATION_DIGITAL,
				OMLEndpointUbw32Configuration.PINBITMASK_INPUT_ANALOG);

		this.configuration = new OMLEndpointUbw32Configuration();
	}

	public OMLEndpointUbw32Configuration getConfiguration() {
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

package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class ObermattLangnauEndpointUbw32 extends EndpointUbw32 {

	private ObermattLangnauEndpointUbw32Configuration configuration;

	public ObermattLangnauEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(
				enumSerialPort,
				ObermattLangnauEndpointUbw32Configuration.PINBITMASK_CONFIGURATION_DIGITAL,
				ObermattLangnauEndpointUbw32Configuration.PINBITMASK_INPUT_ANALOG);

		this.configuration = new ObermattLangnauEndpointUbw32Configuration();
	}

	public ObermattLangnauEndpointUbw32Configuration getConfiguration() {
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

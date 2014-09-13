package ch.desm.middleware.modules.component.interlocking.obermattlangnau;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class OMLEndpointUbw32 extends EndpointUbw32 {

	private OMLEndpointUbw32Data configuration;

	public OMLEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort,
				OMLEndpointUbw32Data.PINBITMASK_CONFIGURATION_DIGITAL,
				OMLEndpointUbw32Data.PINBITMASK_INPUT_ANALOG);

		this.configuration = new OMLEndpointUbw32Data();
	}

	public OMLEndpointUbw32Data getConfiguration() {
		return configuration;
	}
}

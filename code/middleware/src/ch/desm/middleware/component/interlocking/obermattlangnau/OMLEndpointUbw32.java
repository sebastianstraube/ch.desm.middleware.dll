package ch.desm.middleware.component.interlocking.obermattlangnau;

import ch.desm.middleware.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.communication.endpoint.serial.ubw32.EndpointUbw32Impl;
import ch.desm.middleware.component.interlocking.obermattlangnau.maps.OMLMapUbw32Analog;
import ch.desm.middleware.component.interlocking.obermattlangnau.maps.OMLMapUbw32Digital;

public class OMLEndpointUbw32 extends EndpointUbw32Impl {

	public OMLMapUbw32Digital mapDigital;
	public OMLMapUbw32Analog mapAnalog;
	
	public OMLEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort,
				OMLMapUbw32Digital.PINBITMASK_CONFIGURATION_DIGITAL,
				OMLMapUbw32Analog.PINBITMASK_INPUT_ANALOG);
	
	this.mapAnalog = new OMLMapUbw32Analog();
	this.mapDigital = new OMLMapUbw32Digital();
	}
	
}

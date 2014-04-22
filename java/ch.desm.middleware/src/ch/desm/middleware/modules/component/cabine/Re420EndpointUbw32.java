package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class Re420EndpointUbw32 extends EndpointUbw32 {
	
	protected Re420EndpointUbw32Data configuration;
	
	public Re420EndpointUbw32(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort, Re420EndpointUbw32Data.PINBITMASK_CONFIGURATION_DIGITAL, Re420EndpointUbw32Data.PINBITMASK_INPUT_ANALOG);
		
		configuration = new Re420EndpointUbw32Data();
	}
	
	public Re420EndpointUbw32Data getConfiguration(){
		return this.configuration;
	}
		
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}
	

}

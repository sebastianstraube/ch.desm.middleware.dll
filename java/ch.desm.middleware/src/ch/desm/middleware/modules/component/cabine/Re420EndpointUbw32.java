package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class Re420EndpointUbw32 extends EndpointUbw32 {
	
	protected Re420EndpointUbw32Configuration configuration;
	
	public Re420EndpointUbw32(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort, Re420EndpointUbw32Configuration.PINBITMASK_CONFIGURATION_DIGITAL, "");
		
		configuration = new Re420EndpointUbw32Configuration();
	}
	
	public Re420EndpointUbw32Configuration getConfiguration(){
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

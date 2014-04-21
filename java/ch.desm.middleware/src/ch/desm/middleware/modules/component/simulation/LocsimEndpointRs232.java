package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;

public class LocsimEndpointRs232 extends EndpointRs232 {
	
	protected LocsimEndpointRs232Configuration configuration;
	
	public LocsimEndpointRs232(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort);
		
		configuration = new LocsimEndpointRs232Configuration();
	}
	
	public LocsimEndpointRs232Configuration getConfiguration(){
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

package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;

public class LocsimEndpointRs232 extends EndpointRs232 {
	
	protected LocsimEndpointRs232Data configuration;
	
	public LocsimEndpointRs232(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort);
		
		configuration = new LocsimEndpointRs232Data();
	}
	
	public LocsimEndpointRs232Data getConfiguration(){
		return this.configuration;
	}
}

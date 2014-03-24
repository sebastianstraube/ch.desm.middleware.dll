package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;

public class LocsimEndpointRs232 extends EndpointRs232{

	public LocsimEndpointRs232(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort);
		// TODO Auto-generated constructor stub
	}

	public void setHaupthahn1(String position){
		
		System.out.println("transmit setHaupthahn1 to : " + this.getClass());	
	}
	
}

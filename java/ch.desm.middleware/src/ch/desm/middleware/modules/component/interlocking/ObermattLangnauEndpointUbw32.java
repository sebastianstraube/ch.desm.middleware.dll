package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class ObermattLangnauEndpointUbw32 extends EndpointUbw32{

	public ObermattLangnauEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort);
		// TODO Auto-generated constructor stub
	}


	
	public void setHaupthahn(int elementId, String position) {
		System.out.println("transmit setHaupthahn1 to : " + this.getClass());
		
		this.sendCommand("haupthahn.1.off");
	}

}

package ch.desm.middleware.modules.component.simulation;

import jssc.SerialPortException;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;

public class LocsimEndpointRs232 extends EndpointRs232 {
	
	public LocsimEndpointRs232(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort);
		
	}
	
	
	public void sendMessage(String command){
		try {
			super.sendStream(command);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

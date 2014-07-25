package ch.desm.middleware.modules.component.simulation;

import jssc.SerialPortException;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232Config;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232ConfigBuilder;

public class LocsimEndpointRs232 extends EndpointRs232 {
	
	public LocsimEndpointRs232(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort, new EndpointRs232ConfigBuilder()
			.setBaudRate(EndpointRs232Config.BAUDRATE_38400)
			.setDataBits(EndpointRs232Config.DATABITS_8)
			.setStopBits(EndpointRs232Config.STOPBITS_1)
			.setParity(EndpointRs232Config.PARITY_NONE)
			.setEventMask(EndpointRs232Config.MASK_RXCHAR)
			.setFlowControl(EndpointRs232Config.FLOWCONTROL_XONXOFF_IN | EndpointRs232Config.FLOWCONTROL_XONXOFF_OUT)
			.build());
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

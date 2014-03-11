package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;

import jssc.SerialPort;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointListenerRs232;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class CommunicationEndpointListenerUbw32 extends CommunicationEndpointListenerRs232 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2771647685619948122L;
	
	public CommunicationEndpointListenerUbw32(ComponentAbstract eventSource, SerialPort serialPort) {
		super(eventSource, serialPort);
	}
}

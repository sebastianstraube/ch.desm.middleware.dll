package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;

import jssc.SerialPort;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232Listener;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class CommunicationEndpointUbw32Listener extends CommunicationEndpointRs232Listener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2771647685619948122L;
	
	public CommunicationEndpointUbw32Listener(ComponentAbstract eventSource, SerialPort serialPort) {
		super(eventSource, serialPort);
	}
}

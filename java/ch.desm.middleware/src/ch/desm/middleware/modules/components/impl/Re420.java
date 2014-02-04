package ch.desm.middleware.modules.components.impl;

import ch.desm.middleware.modules.communication.AbstractCommunication;
import ch.desm.middleware.modules.communication.controller.Rs232;

public class Re420 extends AbstractCommunication{

	private Rs232 serialPort;
	private String[] connectedPorts = {"COM4"};
	
	public Re420(){
		serialPort = new Rs232();
		this.initialize();
	}
	
	public void initialize(){
		serialPort.setSerialPorts(connectedPorts);
	}
	
	public void disconnect(){
		serialPort.disconnect();
	}
	
	public void testConnection(){
		serialPort.testSeriaPorts();
	}
}

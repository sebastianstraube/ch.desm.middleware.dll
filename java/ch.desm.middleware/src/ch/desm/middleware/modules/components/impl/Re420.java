package ch.desm.middleware.modules.components.impl;

import ch.desm.middleware.modules.communication.controller.Rs232;
import ch.desm.middleware.modules.components.InterfaceComponents;

public class Re420 implements InterfaceComponents{

	private Rs232 serialPort;
	private String[] connectedPorts = {"COM6", "COM8"};
	
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

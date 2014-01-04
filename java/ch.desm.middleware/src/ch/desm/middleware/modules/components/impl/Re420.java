package ch.desm.middleware.modules.components.impl;

import ch.desm.middleware.modules.communication.controller.Rs232;

public class Re420{

	private Rs232 serialPort;
	
	public Re420(){
		serialPort = new Rs232();
	}
	
	public void initialize(){
		
	}
}

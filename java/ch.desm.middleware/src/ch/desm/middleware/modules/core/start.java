package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.controller.impl.Ubw32;
import ch.desm.middleware.modules.communication.controller.impl.Ubw32Command;


public class start {

	public static void main(String[] args) {

	Ubw32 serialports = new Ubw32();
	serialports.testSeriaPorts();
	Ubw32Command command = new Ubw32Command();
	command.setConfigure(0, 0, 0, 0, 0, 0, 0);	
	serialports.sendCommand(command);
	
	int i=65536;
	
	while(true){
		try {
			Thread.sleep(70);
			command.setOutputState(0,0,0,0,i-1,0,0);
			serialports.sendCommand(command);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i/=2;
//		if(i<=1) i++;
		if(i < 1) i=65536;
	}
	
	/*
	boolean disc = false;
	while(true){
		
		try {
			Thread.sleep(1000);
			if(!disc){
				serialports.disconnectAllPorts();
				disc = true;
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	}

}

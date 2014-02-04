package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.controller.impl.Ubw32;


public class start {

	public static void main(String[] args) {

	Ubw32 serialports = new Ubw32();
	serialports.testSeriaPorts();
	
	while(true){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	lok1.disconnect();
	}

}

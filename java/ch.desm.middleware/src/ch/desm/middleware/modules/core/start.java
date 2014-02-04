package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.components.impl.Re420;


public class start {

	public static void main(String[] args) {

	Re420 lok1 = new Re420();
	lok1.testConnection();
	
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

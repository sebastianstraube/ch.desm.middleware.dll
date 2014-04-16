package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.interlocking.OMLBaseImpl;
import ch.desm.middleware.modules.component.interlocking.OMLEndpointUbw32;
import ch.desm.middleware.modules.component.test.TestBaseImpl;
import ch.desm.middleware.modules.component.test.TestEndpointUbw32;

public class Main {

	public static void main(String[] args) {
		
		Broker broker = new Broker();		    

		TestEndpointUbw32 testEndpoint = new TestEndpointUbw32(EnumSerialPorts.COM22);
		TestBaseImpl testImpl = new TestBaseImpl(broker, testEndpoint);

		
		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM8);
		Re420BaseImpl re420Impl = new Re420BaseImpl(broker, re420EndpointUbw32);

		while(true){
			testImpl.emulateBrokerMessage("1.90.02;o;0;lampe;signalf;notrot;on;#");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			testImpl.emulateBrokerMessage("1.90.02;o;0;lampe;signalf;notrot;off;#");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
}

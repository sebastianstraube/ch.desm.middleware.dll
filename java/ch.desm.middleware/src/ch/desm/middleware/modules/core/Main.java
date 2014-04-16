package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauBaseImpl;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauEndpointUbw32;

public class Main {

	public static void main(String[] args) {
		
		Broker broker = new Broker();		    

		ObermattLangnauEndpointUbw32 omlEndpoint = new ObermattLangnauEndpointUbw32(EnumSerialPorts.COM22);//
		ObermattLangnauBaseImpl omlImpl = new ObermattLangnauBaseImpl(broker, omlEndpoint);
		
		
		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM8);
		Re420BaseImpl re420Impl = new Re420BaseImpl(broker, re420EndpointUbw32);
		
		
	}
}

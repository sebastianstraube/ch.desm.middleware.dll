package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32;
import ch.desm.middleware.modules.component.simulation.ComponentSimulationLocsim;



public class Main {

	public static void main(String[] args) {
	
		CommunicationBroker broker = new CommunicationBroker();	
		
		CommunicationEndpointUbw32 communicationEndpointUbw32Locsim = new CommunicationEndpointUbw32(EnumSerialPorts.COM6);
		
		ComponentSimulationLocsim componentSimulationLocsim = new ComponentSimulationLocsim(broker, communicationEndpointUbw32Locsim);

		broker.connect(componentSimulationLocsim);
		
		communicationEndpointUbw32Locsim.emulateIncomingMessage("test");
		
	}

}

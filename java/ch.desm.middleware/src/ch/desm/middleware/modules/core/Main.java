package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32;
import ch.desm.middleware.modules.component.interlocking.ComponentInterlockingObermattLangau;
import ch.desm.middleware.modules.component.simulation.ComponentSimulationLocsim;



public class Main {

	public static void main(String[] args) {
	
		CommunicationBroker broker = new CommunicationBroker();	
		
		CommunicationEndpointRs232 communicationEndpointRs232Locsim = new CommunicationEndpointUbw32(EnumSerialPorts.COM6);
		ComponentSimulationLocsim componentSimulationLocsim = new ComponentSimulationLocsim(broker, communicationEndpointRs232Locsim);
		
		CommunicationEndpointUbw32 communicationEndpointUbw32Cabine = new CommunicationEndpointUbw32(EnumSerialPorts.COM7);
		ComponentInterlockingObermattLangau componentInterlockingObermattLangnau = new ComponentInterlockingObermattLangau(broker, communicationEndpointUbw32Cabine);
		
		
		
	}

}

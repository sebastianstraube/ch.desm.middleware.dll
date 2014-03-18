package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.endpoint.virtual.CommunicationEndpointMessageVirtual;
import ch.desm.middleware.modules.component.interlocking.ComponentInterlockingObermattLangau;
import ch.desm.middleware.modules.component.simulation.ComponentSimulationLocsim;



public class Main {

	public static void main(String[] args) {
	
		CommunicationBroker broker = new CommunicationBroker();	
		
		CommunicationEndpointMessageVirtual communicationEndpointVirtualLocsim = new CommunicationEndpointMessageVirtual();
		ComponentSimulationLocsim componentSimulationLocsim = new ComponentSimulationLocsim(broker, communicationEndpointVirtualLocsim);
		
		CommunicationEndpointMessageVirtual communicationEndpointVirtualCabine = new CommunicationEndpointMessageVirtual();
		ComponentInterlockingObermattLangau componentInterlockingObermattLangnau = new ComponentInterlockingObermattLangau(broker, communicationEndpointVirtualCabine);
		
		
		communicationEndpointVirtualCabine.emulateIncomingMessage("test");
	}

}

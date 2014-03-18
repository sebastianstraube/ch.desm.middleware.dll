package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.endpoint.virtual.CommunicationEndpointMessageVirtual;
import ch.desm.middleware.modules.component.interlocking.ComponentInterlockingObermattLangau;
import ch.desm.middleware.modules.component.simulation.ComponentSimulationLocsim;

public class Main {

	public static void main(String[] args) {

		CommunicationBroker broker = new CommunicationBroker();

		CommunicationEndpointMessageVirtual communicationEndpointVirtualLocsim = new CommunicationEndpointMessageVirtual();
		CommunicationEndpointMessageVirtual communicationEndpointVirtualCabine = new CommunicationEndpointMessageVirtual();

		ComponentSimulationLocsim componentSimulationLocsim = new ComponentSimulationLocsim(
				broker, communicationEndpointVirtualLocsim);
		ComponentInterlockingObermattLangau componentInterlockingObermattLangnau = new ComponentInterlockingObermattLangau(
				broker, communicationEndpointVirtualCabine);

		communicationEndpointVirtualLocsim.emulateIncomingEndpointMessage("test message from virtual locsim endpoint");
		communicationEndpointVirtualCabine.emulateIncomingEndpointMessage("test message from virtual cabine endpoint");
		
		
	}

}

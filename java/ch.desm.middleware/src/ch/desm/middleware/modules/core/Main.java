package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.channel.CommunicationBrokerMessageChannel;
import ch.desm.middleware.modules.communication.broker.message.type.CommunicationBrokerMessageTypeSignalLampeAn;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32Listener;
import ch.desm.middleware.modules.component.simulation.ComponentSimulationLocsim;



public class Main {

	public static void main(String[] args) {
	
		CommunicationBrokerHandler broker = new CommunicationBrokerHandler();	
		
		ComponentSimulationLocsim componentSimulationLocsim = new ComponentSimulationLocsim(broker);
		
		CommunicationEndpointUbw32Listener communicationEndpointUbw32Listener = new CommunicationEndpointUbw32Listener();
		
		CommunicationEndpointUbw32 communicationEndpointUbw32 = new CommunicationEndpointUbw32(EnumSerialPorts.COM6);
		
		CommunicationBrokerMessageChannel messageChannel = new CommunicationBrokerMessageChannel(new CommunicationBrokerMessageTypeSignalLampeAn(1, 0,  0), componentSimulationLocsim);

	}

}

package ch.desm.middleware.modules.communication.broker.message.channel;

import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class CommunicationBrokerMessageChannel {

	public CommunicationBrokerMessageChannel(CommunicationBrokerMessage message, ComponentAbstract destination){
		
		//TODO check message type with destination
//		destination.emulateSerialMessage(message.getMessage());
	}
}

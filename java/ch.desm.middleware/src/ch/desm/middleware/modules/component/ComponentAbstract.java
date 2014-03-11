package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerClient;
import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;
	
/**
 * 
 * @author Sebastian
 *
 */
public abstract class ComponentAbstract extends CommunicationBrokerClient {

	public static enum enumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
	
	public ComponentAbstract(CommunicationBrokerHandler broker) {
		super(broker);
	}
}

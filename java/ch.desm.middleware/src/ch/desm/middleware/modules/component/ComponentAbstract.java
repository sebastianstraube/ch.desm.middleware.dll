package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerClient;
import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBaseListenerInterface;
	
/**
 * 
 * @author Sebastian
 *
 */
public abstract class ComponentAbstract extends CommunicationBrokerClient implements CommunicationEndpointBaseListenerInterface{
	
	public static enum enumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
	
	public ComponentAbstract(CommunicationBroker broker) {
		super(broker);
	}
}

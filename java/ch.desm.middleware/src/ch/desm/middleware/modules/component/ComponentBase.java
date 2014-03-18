package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.broker.CommunicationBrokerClient;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBaseListenerInterface;
	
/**
 * 
 * @author Sebastian
 *
 */
public abstract class ComponentBase extends CommunicationBrokerClient implements CommunicationEndpointBaseListenerInterface{
	
	public static enum enumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
	
	public ComponentBase(CommunicationBroker broker) {
		super(broker);
	}
	
	abstract protected void registerEndpointListener(CommunicationEndpointBase listener);
	abstract public void emulateEndpointMessage(String message);
	abstract public void emulateBrokerMessage(CommunicationBrokerMessage message);
}

package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBaseListenerInterface;
	
/**
 * 
 * @author Sebastian
 *
 */
public abstract class ComponentBase extends BrokerClient implements CommunicationEndpointBaseListenerInterface{
	
	private static double id = 0;
	
	public static enum EnumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
	
	public ComponentBase(Broker broker) {
		super(broker);
		id++;
	}
	
	public double getId(){
		return id;
	}
	
	abstract protected void registerEndpointListener(CommunicationEndpointBase listener);
	abstract public void emulateEndpointMessage(String message);
	abstract public void emulateBrokerMessage(BrokerMessageCommon message);
}

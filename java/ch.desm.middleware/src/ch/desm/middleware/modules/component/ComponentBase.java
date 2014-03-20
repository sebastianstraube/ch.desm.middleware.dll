package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.message.MessageCommon;
import ch.desm.middleware.modules.communication.broker.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
	
/**
 * 
 * @author Sebastian
 *
 */
public abstract class ComponentBase extends BrokerClient implements EndpointBaseListenerInterface{
	
	private static double id = 0;
	protected MessageTranslator messageTranslator;
	
	public static enum EnumComponentType{
		SIMULATION, INTERLOCKING, CABINE, CORBA
	}
	
	public ComponentBase(Broker broker) {
		super(broker);
		messageTranslator = new MessageTranslator();
		id++;
	}
	
	public double getId(){
		return id;
	}
	
	abstract protected void registerEndpointListener(EndpointBase listener);
	abstract public void emulateEndpointMessage(String message);
	abstract public void emulateBrokerMessage(MessageCommon message);
}

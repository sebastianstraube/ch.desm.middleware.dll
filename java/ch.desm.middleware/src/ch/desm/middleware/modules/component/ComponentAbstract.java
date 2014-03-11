package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.BrokerHandler;
	
/**
 * 
 * @author Sebastian
 *
 */
public abstract class ComponentAbstract extends BrokerClient {

	public static enum enumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
	
	public ComponentAbstract(BrokerHandler broker) {
		super(broker);
	}
}

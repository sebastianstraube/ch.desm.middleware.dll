package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.BrokerClient;

	

public abstract class ComponentAbstract extends BrokerClient implements ComponentsBridge {

	public ComponentAbstract(BrokerHandler broker) {
		super(broker);
	}

	public static enum enumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
}

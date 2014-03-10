package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;

/**
 * 
 * @author Sebastian
 *
 */
public class BrokerHandlerService {
	
	private static BrokerHandler broker;
	
	public BrokerHandlerService(){
		broker = new BrokerHandler();
	}
	
	public BrokerHandler getBroker(){
		return broker;
	}

}

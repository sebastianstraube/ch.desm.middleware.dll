package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.communication.broker.Broker;

/**
 * 
 * @author Sebastian
 *
 */
public class BrokerHandlerService {
	
	private Broker broker;
	
	public BrokerHandlerService(){
		broker = new Broker();
	}
	
	public Broker getBroker(){
		return broker;
	}

}

package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;

/**
 * 
 * @author Sebastian
 *
 */
public class BrokerHandlerService {
	
	private CommunicationBrokerHandler broker;
	
	public BrokerHandlerService(){
		broker = new CommunicationBrokerHandler();
	}
	
	public CommunicationBrokerHandler getBroker(){
		return broker;
	}

}

package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;

/**
 * 
 * @author Sebastian
 *
 */
public class BrokerHandlerService {
	
	private CommunicationBroker broker;
	
	public BrokerHandlerService(){
		broker = new CommunicationBroker();
	}
	
	public CommunicationBroker getBroker(){
		return broker;
	}

}

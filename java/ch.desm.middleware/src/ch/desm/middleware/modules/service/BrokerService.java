package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.communication.broker.Broker;

public class BrokerService {
	
	private static Broker broker;
	
	public BrokerService(){
		broker = new Broker();
	}
	
	public Broker getBroker(){
		return (broker == null? BrokerService.broker = new Broker() : BrokerService.broker);
	}

}

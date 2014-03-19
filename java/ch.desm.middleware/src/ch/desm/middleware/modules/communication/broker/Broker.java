package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;

public class Broker {
	
	/**
	 * 
	 */
    private static Set<BrokerClient> clients;

    /**
     * 
     */
    public Broker() {
    	Broker.clients = new HashSet<BrokerClient>();
    }

    /**
     * @param client
     */
    public void connect(BrokerClient client) {
    	Broker.clients.add(client);
    }

    /**
     * 
     * @param sendingClient
     * @param message
     */
    protected void publish(BrokerClient sendingClient, BrokerMessageCommon message) {
        for(BrokerClient client : Broker.clients) {
            
        	if(client != sendingClient &&
            	client.getType().equals(message.getMessageType().name())) {
                
            	client.receive(message);
            }
        }
    }

}

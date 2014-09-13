package ch.desm.middleware.communication.broker;

import java.util.HashSet;
import java.util.Set;

public class Broker {
		
	/**
	 * 
	 */
    private Set<BrokerClient> clients;

    /**
     * 
     */
    public Broker() {
    	clients = new HashSet<BrokerClient>();
    }

    /**
     * @param client
     */
    public void connect(BrokerClient client) {
    	clients.add(client);
    }

    /**
     * 
     * @param sendingClient
     * @param message
     */
    protected void publish(BrokerClient sendingClient, String message, String topic) {
        for(BrokerClient client : clients) {
            
        	if(client.hasTopicSigned(topic)) {
            	client.receive(message);
            }
        }
    }

}

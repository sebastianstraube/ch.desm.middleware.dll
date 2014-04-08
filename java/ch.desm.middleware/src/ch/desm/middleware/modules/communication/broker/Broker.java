package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class Broker {
	
	public enum EnumTopic{
		CABINE, INTERLOCKING, SIMULATION
	};
	
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
    protected void publish(BrokerClient sendingClient, MessageBase message) {
        for(BrokerClient client : Broker.clients) {
            
        	if(client != sendingClient) {
                
            	client.receive(message);
            }
        }
    }

}

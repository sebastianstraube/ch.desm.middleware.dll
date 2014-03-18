package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;

public class CommunicationBroker {
	
	/**
	 * 
	 */
    private static Set<CommunicationBrokerClient> clients;

    /**
     * 
     */
    public CommunicationBroker() {
    	CommunicationBroker.clients = new HashSet<CommunicationBrokerClient>();
    }

    /**
     * @param client
     */
    public void connect(CommunicationBrokerClient client) {
    	CommunicationBroker.clients.add(client);
    }

    /**
     * 
     * @param sendingClient
     * @param message
     */
    protected void publish(CommunicationBrokerClient sendingClient, CommunicationBrokerMessage message) {
        for(CommunicationBrokerClient client : CommunicationBroker.clients) {
            if(client != sendingClient) {
                client.receive(message);
            }
        }
    }

}

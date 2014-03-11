package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessageInterface;

public class CommunicationBrokerHandler {

    private static Set<CommunicationBrokerClient> clients;

    public CommunicationBrokerHandler() {
        clients = new HashSet<CommunicationBrokerClient>();
    }

    /**
     * @param client
     */
    public void connect(CommunicationBrokerClient client) {
        clients.add(client);
    }

    protected void publish(CommunicationBrokerClient sendingClient, CommunicationBrokerMessageInterface message) {
        for(CommunicationBrokerClient client : clients) {
            if(client != sendingClient) {
                client.receive(message);
            }
        }
    }

}

package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.broker.message.BrokerMessageInterface;

public class BrokerHandler {

    private static Set<BrokerClient> clients;

    public BrokerHandler() {
        clients = new HashSet<BrokerClient>();
    }

    /**
     * @param client
     */
    public void connect(BrokerClient client) {
        clients.add(client);
    }

    protected void publish(BrokerClient sendingClient, BrokerMessageInterface message) {
        for(BrokerClient client : clients) {
            if(client != sendingClient) {
                client.receive(message);
            }
        }
    }

}

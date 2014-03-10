package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

public class BrokerHandler {

    private Set<BrokerClient> clients;

    public BrokerHandler() {
        this.clients = new HashSet<BrokerClient>();
    }

    /**
     * @param client
     */
    public void connect(BrokerClient client) {
        clients.add(client);
    }

    protected void publish(BrokerClient sendingClient, BrokerMessageBridge message) {
        for(BrokerClient client : this.clients) {
            if(client != sendingClient) {
                client.receive(message);
            }
        }
    }

}

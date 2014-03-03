package ch.desm.middleware.modules.communication.broker;

import java.util.HashSet;
import java.util.Set;

public class Broker {

    private Set<BrokerClient> clients;

    public Broker() {
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

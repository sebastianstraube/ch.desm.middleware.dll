package ch.desm.middleware.modules.communication.broker;

import ch.desm.middleware.modules.communication.message.MessageBroker;


public abstract class BrokerClient implements BrokerClientInterface {

    protected static Broker broker;

    public BrokerClient(Broker broker) {
        BrokerClient.broker = broker;
        
        initialize();
    }
    
    private void initialize(){
    	broker.connect(this);
    }

    /**
     * util to forward message to broker
     * @param message
     */
    protected void publish(MessageBroker message) {
        broker.publish(this, message);
    }

    /**
     * called by Broker with incoming message
     * @param message
     */
    protected void receive(MessageBroker message) {
        this.onIncomingBrokerMessage(message);
    }

    /**
     * must be implemented with message handling functionality
     * @param message
     */
    protected abstract void onIncomingBrokerMessage(MessageBroker message);

}

package ch.desm.middleware.modules.communication.broker;

import ch.desm.middleware.modules.communication.broker.message.BrokerMessageInterface;

public abstract class BrokerClient {

    protected BrokerHandler broker;

    public BrokerClient(BrokerHandler broker) {
        this.broker = broker;
        
        initialize();
    }
    
    private void initialize(){
    	this.broker.connect(this);
    }

    /**
     * util to forward message to broker
     * @param message
     */
    protected void publish(BrokerMessageInterface message) {
        this.broker.publish(this, message);
    }

    /**
     * called by Broker with incoming message
     * @param message
     */
    protected void receive(BrokerMessageInterface message) {
        this.onBrokerMessage(message);
    }

    /**
     * must be implemented with message handling functionality
     * @param message
     */
    protected abstract void onBrokerMessage(BrokerMessageInterface message);

}

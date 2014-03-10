package ch.desm.middleware.modules.communication.broker;

public abstract class BrokerClient {

    protected Broker broker;

    public BrokerClient(Broker broker) {
        this.broker = broker;
        this.broker.connect(this);
    }

    /**
     * util to forward message to broker
     * @param message
     */
    protected void publish(BrokerMessageBridge message) {
        this.broker.publish(this, message);
    }

    /**
     * called by Broker with incoming message
     * @param message
     */
    protected void receive(BrokerMessageBridge message) {
        this.onBrokerMessage(message);
    }

    /**
     * must be implemented with message handling functionality
     * @param message
     */
    protected abstract void onBrokerMessage(BrokerMessageBridge message);

}

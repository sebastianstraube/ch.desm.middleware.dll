package ch.desm.middleware.modules.communication.broker;

import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;

public abstract class CommunicationBrokerClient implements CommunicationBrokerClientInterface {

    protected static CommunicationBroker broker;

    public CommunicationBrokerClient(CommunicationBroker broker) {
        CommunicationBrokerClient.broker = broker;
        
        initialize();
    }
    
    private void initialize(){
    	broker.connect(this);
    }

    /**
     * util to forward message to broker
     * @param message
     */
    protected void publish(CommunicationBrokerMessage message) {
        broker.publish(this, message);
    }

    /**
     * called by Broker with incoming message
     * @param message
     */
    protected void receive(CommunicationBrokerMessage message) {
        this.onBrokerMessage(message);
    }

    /**
     * must be implemented with message handling functionality
     * @param message
     */
    protected abstract void onBrokerMessage(CommunicationBrokerMessage message);

}

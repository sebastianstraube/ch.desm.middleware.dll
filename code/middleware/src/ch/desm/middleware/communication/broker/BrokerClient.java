package ch.desm.middleware.communication.broker;

import java.util.HashSet;
import java.util.Set;


public abstract class BrokerClient implements BrokerClientInterface {

	protected Broker broker;
	private Set<String> signedTopics;
	
	/**
	 * must be implemented with message handling functionality
	 * 
	 * @param message
	 */
	abstract protected void onIncomingBrokerMessage(String message);
	abstract protected void intializeSignedTopic();

    protected void signForTopic(String topic) {
        signedTopics.add(topic);
    }

    public boolean hasTopicSigned(String topic) {
        return signedTopics.contains(topic);
    }

	public BrokerClient(Broker broker) {		
		this.broker = broker;
		signedTopics = new HashSet<String>();
		initialize();
	}

	private void initialize() {
		this.intializeSignedTopic();
		broker.connect(this);
	}

	/**
	 * util to forward message to broker
	 * 
	 * @param message
	 */
	public synchronized void publish(String message, String topic) {
		if (message != null && !message.isEmpty()) {
			broker.publish(this, message, topic);
		}
	}

	/**
	 * called by Broker with incoming message
	 * 
	 * @param message
	 */
	protected void receive(String message) {
		if (message != null && !message.isEmpty()) {
			this.onIncomingBrokerMessage(message);
		}
	}

}

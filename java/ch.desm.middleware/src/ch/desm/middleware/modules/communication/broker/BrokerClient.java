package ch.desm.middleware.modules.communication.broker;

import java.util.ArrayList;


public abstract class BrokerClient implements
		BrokerClientInterface {

	protected static Broker broker;
	protected ArrayList<String> signedTopic;
	
	/**
	 * must be implemented with message handling functionality
	 * 
	 * @param message
	 */
	abstract protected void onIncomingBrokerMessage(String message);
	abstract public boolean hasTopicSigned(String topic);
	abstract protected void intializeSignedTopic();
	
	public BrokerClient(Broker broker) {		
		BrokerClient.broker = broker;
		signedTopic = new ArrayList<String>();
		
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

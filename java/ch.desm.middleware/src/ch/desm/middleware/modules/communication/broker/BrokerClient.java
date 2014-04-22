package ch.desm.middleware.modules.communication.broker;


public abstract class BrokerClient implements
		BrokerClientInterface {

	protected static Broker broker;

	public BrokerClient(Broker broker) {		
		BrokerClient.broker = broker;

		initialize();
	}

	private void initialize() {
		broker.connect(this);
	}

	/**
	 * util to forward message to broker
	 * 
	 * @param message
	 */
	public synchronized void publish(String message) {
		if (message != null && !message.isEmpty()) {
			broker.publish(this, message);
		}
	}

	/**
	 * called by Broker with incoming message
	 * 
	 * @param message
	 */
	protected synchronized void receive(String message) {
		if (message != null && !message.isEmpty()) {
			this.onIncomingBrokerMessage(message);
		}
	}

	/**
	 * must be implemented with message handling functionality
	 * 
	 * @param message
	 */
	protected abstract void onIncomingBrokerMessage(String message);

}

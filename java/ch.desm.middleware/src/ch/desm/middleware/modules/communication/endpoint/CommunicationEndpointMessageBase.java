package ch.desm.middleware.modules.communication.endpoint;

public abstract class CommunicationEndpointMessageBase extends
		CommunicationEndpointBase {

	public void sendMessage(String message) {
		System.out.println(this.getClass().getCanonicalName()
				+ "sending message: " + message);
	}

	protected void onIncomingEndpointMessage(String message) {
		for (CommunicationEndpointBaseListenerInterface listener : this.listeners) {

			((CommunicationEndpointMessageBaseListenerInterface) listener)
					.onIncomingEndpointMessage(message);
		}
	}

	/**
	 * test incoming message
	 * @param message
	 */
	public void emulateIncomingEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}

	@Override
	public void addEndpointListener(
			CommunicationEndpointBaseListenerInterface listener)
			throws Exception {

		if (listener instanceof CommunicationEndpointMessageBaseListenerInterface) {
			this.listeners.add(listener);
		} else {
			throw new Exception(
					"Only endpoints with message handling supported.");
		}

	}
}

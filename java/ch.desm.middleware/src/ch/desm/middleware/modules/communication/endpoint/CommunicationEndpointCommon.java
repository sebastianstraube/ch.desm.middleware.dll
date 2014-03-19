package ch.desm.middleware.modules.communication.endpoint;

public abstract class CommunicationEndpointCommon extends
		CommunicationEndpointBase {

	/**
	 * send a message thru an endpoint
	 * 
	 * @param message
	 */
	abstract public void sendMessage(String message);

	protected void onIncomingEndpointMessage(String message) {
		for (CommunicationEndpointBaseListenerInterface listener : this.listeners) {

			((CommunicationEndpointCommonListenerInterface) listener)
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

		if (listener instanceof CommunicationEndpointCommonListenerInterface) {
			this.listeners.add(listener);
		} else {
			throw new Exception(
					"Only endpoints with message handling supported.");
		}

	}
}

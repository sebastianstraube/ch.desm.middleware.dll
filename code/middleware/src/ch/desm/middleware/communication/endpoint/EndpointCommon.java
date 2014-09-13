package ch.desm.middleware.communication.endpoint;

public abstract class EndpointCommon extends EndpointBase {

	public void onIncomingEndpointMessage(String message) {
		for (EndpointCommonListenerInterface listener : this.listeners) {
			listener.onIncomingEndpointMessage(message);
		}
	}

	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}

	@Override
	public void addEndpointListener(EndpointCommonListenerInterface listener) throws Exception {
		if (!(listener instanceof EndpointCommonListenerInterface)) {
			throw new Exception("Only endpoints with message handling supported.");
		}
        this.listeners.add(listener);
	}
}

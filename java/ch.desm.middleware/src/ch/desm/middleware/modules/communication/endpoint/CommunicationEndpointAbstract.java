package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

public abstract class CommunicationEndpointAbstract implements CommunicationEndpointInterface {

	protected Set<CommunicationEndpointListenerInterface> listeners;

	public CommunicationEndpointAbstract() {
		this.listeners = new HashSet<CommunicationEndpointListenerInterface>();
	}

	@Override
	public void addEndpointListener(CommunicationEndpointListenerInterface listener) {
		this.listeners.add(listener);
	}

	public void sendSerialMessage(String message) {
		System.out.println(this.getClass().getCanonicalName()
				+ " sending serial message: " + message);
	}

	private void onIncomingSerialMessage(String message) {
		for (CommunicationEndpointListenerInterface listener : this.listeners) {
			listener.onSerialMessage(message);
		}
	}

	/**
	 * TEST TEST TEST TEST
	 * 
	 * @param message
	 */
	public void emulateSerialMessage(String message) {
		onIncomingSerialMessage(message);
	}

}
package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.core.event.Event;

public abstract class CommunicationEndpointListenerAbstract extends Event implements CommunicationEndpointInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3890306603301505337L;
	
	protected Set<CommunicationEndpointListenerInterface> listeners;

	public CommunicationEndpointListenerAbstract(Object eventSource) {
		super(eventSource);
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
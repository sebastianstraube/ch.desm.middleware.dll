package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

public abstract class CommunicationEndpointBase {

	protected Set<CommunicationEndpointBaseListenerInterface> listeners;

	public CommunicationEndpointBase() {
		this.listeners = new HashSet<CommunicationEndpointBaseListenerInterface>();
	}

	abstract public void addEndpointListener(CommunicationEndpointBaseListenerInterface listener) throws Exception;
}
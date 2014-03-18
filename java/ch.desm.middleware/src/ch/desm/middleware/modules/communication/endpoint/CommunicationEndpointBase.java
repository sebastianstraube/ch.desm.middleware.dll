package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Sebastian
 *
 */
public abstract class CommunicationEndpointBase {

	protected Set<CommunicationEndpointBaseListenerInterface> listeners;

	/**
	 * 
	 */
	public CommunicationEndpointBase() {
		this.listeners = new HashSet<CommunicationEndpointBaseListenerInterface>();
	}

	/**
	 * 
	 * @param listener
	 * @throws Exception
	 */
	abstract public void addEndpointListener(CommunicationEndpointBaseListenerInterface listener) throws Exception;

}
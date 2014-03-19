package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Sebastian
 *
 */
public abstract class EndpointBase {

	protected Set<EndpointBaseListenerInterface> listeners;

	/**
	 * 
	 */
	public EndpointBase() {
		this.listeners = new HashSet<EndpointBaseListenerInterface>();
	}

	/**
	 * 
	 * @param listener
	 * @throws Exception
	 */
	abstract public void addEndpointListener(EndpointBaseListenerInterface listener) throws Exception;

}
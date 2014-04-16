package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;

/**
 * 
 * @author Sebastian
 *
 */
public abstract class EndpointBase {

	protected Set<ComponentBaseListenerInterface> listeners;
	protected MessageTranslator messageTranslator;
	
	/**
	 * 
	 */
	public EndpointBase() {
		this.listeners = new HashSet<ComponentBaseListenerInterface>();
		messageTranslator = new MessageTranslator();
	}

	/**
	 * 
	 * @param listener
	 * @throws Exception
	 */
	abstract public void addEndpointListener(ComponentBaseListenerInterface listener) throws Exception;

}
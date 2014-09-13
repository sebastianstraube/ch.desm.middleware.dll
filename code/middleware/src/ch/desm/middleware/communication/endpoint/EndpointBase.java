package ch.desm.middleware.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.communication.message.translator.MessageTranslatorMiddleware;

/**
 * 
 * @author Sebastian
 * 
 */
public abstract class EndpointBase {

	protected Set<EndpointCommonListenerInterface> listeners;
	protected MessageTranslatorMiddleware messageTranslator;

	/**
	 * 
	 */
	public EndpointBase() {
		this.listeners = new HashSet<EndpointCommonListenerInterface>();
		messageTranslator = new MessageTranslatorMiddleware();
	}

	/**
	 * 
	 * @param listener
	 * @throws Exception
	 */
	abstract public void addEndpointListener(EndpointCommonListenerInterface listener) throws Exception;

}
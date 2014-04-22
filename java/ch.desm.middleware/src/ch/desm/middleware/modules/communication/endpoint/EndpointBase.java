package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorCommon;

/**
 * 
 * @author Sebastian
 *
 */
public abstract class EndpointBase {

	protected Set<EndpointCommonListenerInterface> listeners;
	protected MessageTranslatorCommon messageTranslator;
	
	/**
	 * 
	 */
	public EndpointBase() {
		this.listeners = new HashSet<EndpointCommonListenerInterface>();
		messageTranslator = new MessageTranslatorCommon();
	}

	/**
	 * 
	 * @param listener
	 * @throws Exception
	 */
	abstract public void addEndpointListener(EndpointCommonListenerInterface listener) throws Exception;

}
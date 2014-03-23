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

	protected Set<EndpointBaseListenerInterface> listeners;
	protected MessageTranslator messageTranslator;
	
	public enum EnumEndpointType{
		SERIAL, UBW32, TCPIP, CORBA, VIRTUAL, DLL
	}
	
	/**
	 * 
	 */
	public EndpointBase() {
		this.listeners = new HashSet<EndpointBaseListenerInterface>();
		messageTranslator = new MessageTranslator();
	}

	/**
	 * 
	 * @param listener
	 * @throws Exception
	 */
	abstract public void addEndpointListener(EndpointBaseListenerInterface listener) throws Exception;

}
package ch.desm.middleware.component;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.communication.broker.BrokerClient;
import ch.desm.middleware.communication.endpoint.EndpointBase;
import ch.desm.middleware.communication.endpoint.EndpointCommonListenerInterface;

/**
 * 
 * @author Sebastian
 * 
 */
public abstract class ComponentBase extends BrokerClient
	implements EndpointCommonListenerInterface {
	
	private static Logger log = Logger.getLogger(ComponentBase.class);
	
	protected void registerEndpointListener(EndpointBase listener) {
        try {
            listener.addEndpointListener(this);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void emulateBrokerMessage(String message) {
        onIncomingBrokerMessage(message);
    }
	
	public ComponentBase(Broker broker) {
		super(broker);
	}
	
}

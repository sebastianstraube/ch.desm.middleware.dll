package ch.desm.middleware.component.interlocking.obermattlangnau;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.component.ComponentBase;

abstract class OMLBase extends ComponentBase {

	private static Logger log = Logger.getLogger(OMLBase.class);

	protected OMLEndpointUbw32 endpoint;

	public OMLBase(Broker broker, OMLEndpointUbw32 endpoint) {
		super(broker);
		this.endpoint = endpoint;

		this.registerEndpointListener(endpoint);
	}

	public OMLEndpointUbw32 getEndpoint() {
		return this.endpoint;
	}

}

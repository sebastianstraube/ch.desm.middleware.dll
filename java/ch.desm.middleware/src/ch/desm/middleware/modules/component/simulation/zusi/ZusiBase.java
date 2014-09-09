package ch.desm.middleware.modules.component.simulation.zusi;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.component.ComponentBase;

abstract class ZusiBase extends ComponentBase {

	protected ZusiEndpointTcp endpoint;

	public ZusiBase(Broker broker, ZusiEndpointTcp endpoint) {
		super(broker);
		this.endpoint = endpoint;
		this.registerEndpointListener(endpoint);
	}

	public ZusiEndpointTcp getEndpoint() {
		return this.endpoint;
	}
}

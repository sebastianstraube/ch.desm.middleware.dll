package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.component.ComponentBase;

abstract class LocsimBase extends ComponentBase {

	protected LocsimEndpointRs232 endpoint;
	
	public LocsimBase(Broker broker,
			LocsimEndpointRs232 endpoint) {
		super(broker);
		
		this.endpoint = endpoint;
		this.registerEndpointListener(endpoint);
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			endpoint.addEndpointListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LocsimEndpointRs232 getEndpoint(){
		return this.endpoint;
	}
	
	@Override
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateBrokerMessage(String message) {
		onIncomingBrokerMessage(message);
	}
}

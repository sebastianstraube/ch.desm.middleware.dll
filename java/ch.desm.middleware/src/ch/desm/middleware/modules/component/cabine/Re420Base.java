package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.component.ComponentBase;

abstract class Re420Base extends ComponentBase {

	protected Re420EndpointUbw32 endpoint;
	
	public Re420Base(Broker broker,
			Re420EndpointUbw32 endpoint) {
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
	
	public Re420EndpointUbw32 getEndpoint(){
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

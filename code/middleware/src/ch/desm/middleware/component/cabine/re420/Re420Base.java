package ch.desm.middleware.component.cabine.re420;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.component.ComponentBase;

abstract class Re420Base extends ComponentBase {

	protected Re420EndpointUbw32 endpoint;
	protected Re420EndpointFabisch endpointFabisch;
	
	public Re420Base(Broker broker,
			Re420EndpointUbw32 endpoint, Re420EndpointFabisch endpointFabisch) {
		super(broker);
		
		this.endpoint = endpoint;
		this.endpointFabisch = endpointFabisch;
		
		this.registerEndpointListener(endpoint);
		this.registerEndpointListener(endpointFabisch);
	}
	
	public Re420EndpointUbw32 getEndpointUbw32(){
		return this.endpoint;
	}
	
	public Re420EndpointFabisch getEndpointFabisch(){
		return this.endpointFabisch;
	}
	
}

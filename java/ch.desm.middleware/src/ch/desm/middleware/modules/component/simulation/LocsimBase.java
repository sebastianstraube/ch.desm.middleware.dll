package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.component.ComponentBase;

abstract class LocsimBase extends ComponentBase {

	protected LocsimEndpointRs232 endpointRs232;
	protected LocsimEndpointDll endpointDll;
	
	public LocsimBase(Broker broker,
			LocsimEndpointRs232 endpointRs232,
			LocsimEndpointDll endpointDll) {
		super(broker);
		
		this.endpointRs232 = endpointRs232;
		this.endpointDll = endpointDll;
		
		this.registerEndpointListener(endpointRs232);
		this.registerEndpointListener(endpointDll);
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			endpointRs232.addEndpointListener(this);
			endpointDll.addEndpointListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LocsimEndpointRs232 getEndpointRs232(){
		return this.endpointRs232;
	}
	
	public LocsimEndpointDll getEndpointDll(){
		return this.endpointDll;
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

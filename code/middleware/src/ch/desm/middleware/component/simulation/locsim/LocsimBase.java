package ch.desm.middleware.component.simulation.locsim;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.component.ComponentBase;

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
	
	public LocsimEndpointRs232 getEndpointRs232(){
		return this.endpointRs232;
	}
	
	public LocsimEndpointDll getEndpointDll(){
		return this.endpointDll;
	}
	
}

package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDll;

public class LocsimEndpointDll extends EndpointDll {

	LocsimEndpointDllData data;
	
	public LocsimEndpointDll(String configPath) {
		super(configPath);
		
		data = new LocsimEndpointDllData();
	}
	
	
	public LocsimEndpointDllData getConfiguration(){
		return this.data;
	}

}

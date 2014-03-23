package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;

public abstract class EndpointDesmDll extends EndpointCommon {

	@Override
	public void addEndpointListener(EndpointBaseListenerInterface listener) throws Exception {
		
		if(listener instanceof EndpointDesmDllListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only DLL endpoints supported.");
		}
		
	}
}

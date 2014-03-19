package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointCommon;

public class EndpointDesmDll extends CommunicationEndpointCommon {

	
	public void stw_setWeiche(int weicheId, boolean on) {
		// call desm dll stuff here...
		// adding these explicit calls for test purpose only:
		for (CommunicationEndpointBaseListenerInterface listener : super.listeners) {

			if (listener instanceof EndpointDesmDllListenerInterface) {
				EndpointDesmDllListenerInterface dllListener = 
						(EndpointDesmDllListenerInterface) listener;

				if (on) {
					dllListener.onSignalAn(6);
					dllListener.onZugPositionChanged(1., 2., 3.);
				} else {
					dllListener.onSignalAus(6);
					dllListener.onZugPositionChanged(3, 2, 1);
				}
			}
		}
	}

	@Override
	public void addEndpointListener(CommunicationEndpointBaseListenerInterface listener) throws Exception {
		
		if(listener instanceof EndpointDesmDllListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only DLL endpoints supported.");
		}
		
	}

	@Override
	public void sendMessage(String message) {
		System.out.println("sending message:\"" + message+"\" from " + this.getClass().getCanonicalName());
	}
}

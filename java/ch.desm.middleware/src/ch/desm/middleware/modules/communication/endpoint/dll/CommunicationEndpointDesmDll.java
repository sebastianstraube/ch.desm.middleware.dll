package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBaseListenerInterface;

public class CommunicationEndpointDesmDll extends CommunicationEndpointBase {

	public void stw_setWeiche(int weicheId, boolean on) {
		// call desm dll stuff here...
		// adding these explicit calls for test purpose only:
		for (CommunicationEndpointBaseListenerInterface listener : super.listeners) {

			if (listener instanceof CommunicationEndpointDesmDllListenerInterface) {
				CommunicationEndpointDesmDllListenerInterface dllListener = 
						(CommunicationEndpointDesmDllListenerInterface) listener;

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
		
		if(listener instanceof CommunicationEndpointDesmDllListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only DLL endpoints supported.");
		}
		
	}
}

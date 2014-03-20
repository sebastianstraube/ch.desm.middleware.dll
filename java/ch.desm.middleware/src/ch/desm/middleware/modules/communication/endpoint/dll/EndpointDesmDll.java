package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;

public class EndpointDesmDll extends EndpointCommon {

	/**
	 * TODO implementation 
	 * 
	 * @param weicheId
	 * @param on
	 */
	public void stw_setWeiche(int weicheId, boolean on) {
		// call desm dll stuff here...
		// adding these explicit calls for test purpose only:
		for (EndpointBaseListenerInterface listener : super.listeners) {
			
			try{
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
				}else {
					throw new Exception("only dll endpoints supported");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addEndpointListener(EndpointBaseListenerInterface listener) throws Exception {
		
		if(listener instanceof EndpointDesmDllListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only DLL endpoints supported.");
		}
		
	}
}

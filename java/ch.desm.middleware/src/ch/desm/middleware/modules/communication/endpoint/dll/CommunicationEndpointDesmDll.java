package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointAbstract;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointListenerInterface;

public class CommunicationEndpointDesmDll extends CommunicationEndpointAbstract {

	public void stw_setWeiche(int weicheId, boolean on) {
		// call desm dll stuff here...
		// adding these explicit calls for test purpose only:
		for (CommunicationEndpointListenerInterface listener : super.listeners) {

			if (listener instanceof CommunicationEndpointListenerInterfaceDesmDll) {
				CommunicationEndpointListenerInterfaceDesmDll dllListener = 
						(CommunicationEndpointListenerInterfaceDesmDll) listener;

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

	// TODO: put all this dll and stw event handling stuff here

}

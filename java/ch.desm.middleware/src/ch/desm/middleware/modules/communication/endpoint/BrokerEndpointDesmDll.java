package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

public class BrokerEndpointDesmDll implements BrokerEndpointInterface {

    private Set<BrokerEndpointListenerInterfaceDesmDll> listeners;

    public BrokerEndpointDesmDll() {
        this.listeners = new HashSet<BrokerEndpointListenerInterfaceDesmDll>();
    }

    public void addEndpointListener(BrokerEndpointListenerInterface listener) throws Exception {
        if(!(listener instanceof BrokerEndpointListenerInterfaceDesmDll)) {
            throw new Exception("only desm dll endpoints supported");
        }
        this.listeners.add((BrokerEndpointListenerInterfaceDesmDll)listener);
    }

    public void stw_setWeiche(int weicheId, boolean on) {
        // call desm dll stuff here...
        // adding these explicit calls for test purpose only:
        for(BrokerEndpointListenerInterfaceDesmDll listener : listeners) {
            if(on) {
                listener.onSignalAn(6);
                listener.onZugPositionChanged(1, 2, 3);
            } else {
                listener.onSignalAus(6);
                listener.onZugPositionChanged(3, 2, 1);
            }
        }
    }

    // TODO: put all this dll and stw event handling stuff here

}

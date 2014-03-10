package ch.desm.middleware.modules.communication.dll;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.broker.endpoint.BrokerEndpointBridge;
import ch.desm.middleware.modules.communication.broker.endpoint.BrokerEndpointListenerBridge;

public class BrokerEndpointDesmDll implements BrokerEndpointBridge {

    private Set<BrokerEndpointListenerDesmDllBridge> listeners;

    public BrokerEndpointDesmDll() {
        this.listeners = new HashSet<BrokerEndpointListenerDesmDllBridge>();
    }

    public void addEndpointListener(BrokerEndpointListenerBridge listener) throws Exception {
        if(!(listener instanceof BrokerEndpointListenerDesmDllBridge)) {
            throw new Exception("only desm dll endpoints supported");
        }
        this.listeners.add((BrokerEndpointListenerDesmDllBridge)listener);
    }

    public void stw_setWeiche(int weicheId, boolean on) {
        // call desm dll stuff here...
        // adding these explicit calls for test purpose only:
        for(BrokerEndpointListenerDesmDllBridge listener : listeners) {
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

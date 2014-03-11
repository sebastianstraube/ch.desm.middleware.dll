package ch.desm.middleware.modules.communication.endpoint;

import java.util.HashSet;
import java.util.Set;

public class BrokerEndpointUbw32 implements BrokerEndpointInterface {

    private Set<BrokerEndpointListenerInterfaceUbw32> listeners;

    public BrokerEndpointUbw32() {
        this.listeners = new HashSet<BrokerEndpointListenerInterfaceUbw32>();
    }

    @Override
    public void addEndpointListener(BrokerEndpointListenerInterface listener) throws Exception {
        if(!(listener instanceof BrokerEndpointListenerInterfaceUbw32)) {
            throw new Exception("only serial endpoints supported");
        }
        this.listeners.add((BrokerEndpointListenerInterfaceUbw32)listener);
    }

    public void sendSerialMessage(String message) {
        System.out.println(this.getClass().getCanonicalName() + " sending serial message: " + message);
    }

    private void onIncomingSerialMessage(String message) {
        for(BrokerEndpointListenerInterfaceUbw32 listener : this.listeners) {
            listener.onSerialMessage(message);
        }
    }

    /**
     * TEST TEST TEST TEST
     * @param message
     */
    public void emulateSerialMessage(String message) {
        onIncomingSerialMessage(message);
    }

}

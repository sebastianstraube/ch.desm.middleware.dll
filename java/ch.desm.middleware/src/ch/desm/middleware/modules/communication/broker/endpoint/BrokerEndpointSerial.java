package ch.desm.middleware.modules.communication.broker.endpoint;

import java.util.HashSet;
import java.util.Set;

public class BrokerEndpointSerial implements BrokerEndpointBridge {

    private Set<BrokerEndpointListenerSerialBridge> listeners;

    public BrokerEndpointSerial() {
        this.listeners = new HashSet<BrokerEndpointListenerSerialBridge>();
    }

    @Override
    public void addEndpointListener(BrokerEndpointListenerBridge listener) throws Exception {
        if(!(listener instanceof BrokerEndpointListenerSerialBridge)) {
            throw new Exception("only serial endpoints supported");
        }
        this.listeners.add((BrokerEndpointListenerSerialBridge)listener);
    }

    public void sendSerialMessage(String message) {
        System.out.println(this.getClass().getCanonicalName() + " sending serial message: " + message);
    }

    private void onIncomingSerialMessage(String message) {
        for(BrokerEndpointListenerSerialBridge listener : this.listeners) {
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

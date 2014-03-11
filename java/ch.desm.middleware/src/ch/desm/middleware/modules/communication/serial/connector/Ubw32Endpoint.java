package ch.desm.middleware.modules.communication.serial.connector;

import java.util.HashSet;
import java.util.Set;

import ch.desm.middleware.modules.communication.broker.endpoint.BrokerEndpointBridge;
import ch.desm.middleware.modules.communication.broker.endpoint.BrokerEndpointListenerBridge;

public class Ubw32Endpoint implements BrokerEndpointBridge {

    private Set<Ubw32EndpointListenerBridge> listeners;

    public Ubw32Endpoint() {
        this.listeners = new HashSet<Ubw32EndpointListenerBridge>();
    }

    @Override
    public void addEndpointListener(BrokerEndpointListenerBridge listener) throws Exception {
        if(!(listener instanceof Ubw32EndpointListenerBridge)) {
            throw new Exception("only serial endpoints supported");
        }
        this.listeners.add((Ubw32EndpointListenerBridge)listener);
    }

    public void sendSerialMessage(String message) {
        System.out.println(this.getClass().getCanonicalName() + " sending serial message: " + message);
    }

    private void onIncomingSerialMessage(String message) {
        for(Ubw32EndpointListenerBridge listener : this.listeners) {
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

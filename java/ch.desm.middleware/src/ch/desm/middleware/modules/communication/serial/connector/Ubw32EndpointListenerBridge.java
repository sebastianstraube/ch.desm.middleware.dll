package ch.desm.middleware.modules.communication.serial.connector;

import ch.desm.middleware.modules.communication.broker.endpoint.BrokerEndpointListenerBridge;

public interface Ubw32EndpointListenerBridge extends BrokerEndpointListenerBridge {
    
	public void onSerialMessage(String message);
}

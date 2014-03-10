package ch.desm.middleware.modules.communication.serial.connector;

import ch.desm.middleware.modules.communication.broker.endpoint.BrokerEndpointListenerBridge;

public interface BrokerEndpointListenerBridgeUbw32 extends BrokerEndpointListenerBridge {
    
	public void onSerialMessage(String message);
}

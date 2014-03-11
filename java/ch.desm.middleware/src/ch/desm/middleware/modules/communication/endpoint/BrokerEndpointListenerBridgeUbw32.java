package ch.desm.middleware.modules.communication.endpoint;


public interface BrokerEndpointListenerBridgeUbw32 extends BrokerEndpointListenerBridge {
    
	public void onSerialMessage(String message);
}

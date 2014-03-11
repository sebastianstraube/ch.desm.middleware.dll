package ch.desm.middleware.modules.communication.broker.endpoint;


public interface BrokerEndpointListenerBridgeUbw32 extends BrokerEndpointListenerBridge {
    
	public void onSerialMessage(String message);
}

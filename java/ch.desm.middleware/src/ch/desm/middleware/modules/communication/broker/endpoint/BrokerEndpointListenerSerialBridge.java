package ch.desm.middleware.modules.communication.broker.endpoint;

public interface BrokerEndpointListenerSerialBridge extends BrokerEndpointListenerBridge {
    
	public void onSerialMessage(String message);
}

package ch.desm.middleware.modules.communication.broker.endpoint;

public interface BrokerEndpointBridge {
	
    public void addEndpointListener(BrokerEndpointListenerBridge listener) throws Exception;
}

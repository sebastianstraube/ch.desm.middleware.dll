package ch.desm.middleware.modules.communication.endpoint;

public interface BrokerEndpointInterface {
	
    public void addEndpointListener(BrokerEndpointListenerBridge listener) throws Exception;
}

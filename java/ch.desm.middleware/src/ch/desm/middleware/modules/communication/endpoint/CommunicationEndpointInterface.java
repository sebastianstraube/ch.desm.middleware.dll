package ch.desm.middleware.modules.communication.endpoint;

public interface CommunicationEndpointInterface {
	
    public void addEndpointListener(CommunicationEndpointListenerInterface listener) throws Exception;
}

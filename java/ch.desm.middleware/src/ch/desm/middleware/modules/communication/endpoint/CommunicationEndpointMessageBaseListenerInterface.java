package ch.desm.middleware.modules.communication.endpoint;



public interface CommunicationEndpointMessageBaseListenerInterface extends CommunicationEndpointBaseListenerInterface {
	
	public void onEndpointMessage(String message);
}

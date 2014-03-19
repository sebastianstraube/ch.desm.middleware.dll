package ch.desm.middleware.modules.communication.endpoint;



public interface CommunicationEndpointCommonListenerInterface extends CommunicationEndpointBaseListenerInterface {
	
	public void onIncomingEndpointMessage(String message);
}

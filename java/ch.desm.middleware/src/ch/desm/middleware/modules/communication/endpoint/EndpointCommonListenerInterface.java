package ch.desm.middleware.modules.communication.endpoint;



public interface EndpointCommonListenerInterface extends EndpointBaseListenerInterface {
	
	public void onIncomingEndpointMessage(String message);
}

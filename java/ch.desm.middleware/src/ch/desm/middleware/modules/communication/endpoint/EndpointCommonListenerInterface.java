package ch.desm.middleware.modules.communication.endpoint;



public interface EndpointCommonListenerInterface extends ComponentBaseListenerInterface {

	public void onIncomingEndpointMessage(String message);
}

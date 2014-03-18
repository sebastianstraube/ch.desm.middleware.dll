package ch.desm.middleware.modules.communication.endpoint;


public abstract class CommunicationEndpointMessageBase extends CommunicationEndpointBase {

	public void sendMessage(String message) {
		System.out.println(this.getClass().getCanonicalName()
				+ "sending message: " + message);
	}

	protected void onIncomingMessage(String message) {
		for (CommunicationEndpointBaseListenerInterface listener : this.listeners) {
			
			((CommunicationEndpointMessageBaseListenerInterface)listener).onEndpointMessage(message);
		}
	}
	
	/**
	 * Test incoming message
	 */
	public void emulateIncomingMessage(String message){
		onIncomingMessage(message);
	}
	
	@Override
	public void addEndpointListener(CommunicationEndpointBaseListenerInterface listener) throws Exception {
		
		if(listener instanceof CommunicationEndpointMessageBaseListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only endpoints with message handling supported.");
		}
		
	}
}

package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.type.component.MessageComponentBase;
import ch.desm.middleware.modules.communication.message.type.component.cabine.MessageTypeHaupthahn;

public class LocsimImplRs232 extends LocsimBase implements LocsimListenerRs232{

	public LocsimImplRs232(Broker broker, EndpointCommon communicationEndpointDll) {
		super(broker, communicationEndpointDll);
	}

	@Override
	/**
	 * TODO Implementation
	 * 
	 */
	protected void onIncomingBrokerMessage(MessageComponentBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		
	}

	@Override
	public void onHaupthahn(int id, String value) {
		
		publish(new MessageTypeHaupthahn(id, value));
	}


	@Override
	/**
	 * TODO implementation
	 * 
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		MessageComponentBase comvertedMessage = messageTranslator.translateToBroker(message);
		//TODO CHECK MESSAGES, if they are relevant then publish to other broker clients
		if(message.equals("haupthahn.1.off")){
			onHaupthahn(1, "off");
		}		
	}
	
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}

}

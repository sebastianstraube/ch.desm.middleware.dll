package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
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
	protected void onIncomingBrokerMessage(MessageBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		
	}

	@Override
	/**
	 * TODO implementation
	 * 
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());

		MessageTranslator translator = new MessageTranslator();
		MessageBase brokerMessage = translator.translateToBrokerMessage(message);
		
		publish(brokerMessage);
		//TODO CHECK MESSAGES, if they are relevant then publish to other broker clients
//		if(message.equals("6.90.01;o;schalter;haupthahn;1;on;;#")){
//			onHaupthahn("1", "off");
//		}		
	}
	
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}
	
	
	
	@Override
	public void onHaupthahn(String id, String value) {
		
		publish(new MessageTypeHaupthahn(id, value));
	}




}

package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.MessageBroker;
import ch.desm.middleware.modules.communication.message.type.component.cabine.MessageTypeHaupthahn1;

public class LocsimImplRs232 extends LocsimBase implements LocsimListenerRs232{

	public LocsimImplRs232(Broker broker, EndpointCommon communicationEndpointDll) {
		super(broker, communicationEndpointDll);
	}

	@Override
	/**
	 * TODO Implementation
	 * 
	 */
	protected void onIncomingBrokerMessage(MessageBroker message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		
	}

	@Override
	public void onHaupthahn1(String payload) {
		
		publish(new MessageTypeHaupthahn1(payload));
	}


	@Override
	/**
	 * TODO implementation
	 * 
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		//TODO CHECK MESSAGES, if they are relevant then publish to other broker clients
		if(message.equals("haupthahn.1.off")){
			onHaupthahn1("off");
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

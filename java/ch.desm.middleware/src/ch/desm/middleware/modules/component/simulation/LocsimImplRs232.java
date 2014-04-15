package ch.desm.middleware.modules.component.simulation;

import java.util.Map;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.component.cabine.MessageTypeHaupthahn;

public class LocsimImplRs232 extends LocsimBase implements LocsimListenerRs232 {

	public LocsimImplRs232(Broker broker,
			EndpointCommon communicationEndpointDll) {
		super(broker, communicationEndpointDll);
	}

	@Override
	/**
	 * TODO Implementation
	 * 
	 */
	protected void onIncomingBrokerMessage(MessageCommon message) {
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

	}

	/**
	 * test endpoint message handling
	 * 
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}

	/**
	 * TODO implementation
	 */
	@Override
	public void onHaupthahn(String payload) {

//		publish(new MessageTypeHaupthahn(payload));
	}

	@Override
	public Map<String, String> getInpuAnalogOn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getInpuDigitalOn() {
		// TODO Auto-generated method stub
		return null;
	}

}

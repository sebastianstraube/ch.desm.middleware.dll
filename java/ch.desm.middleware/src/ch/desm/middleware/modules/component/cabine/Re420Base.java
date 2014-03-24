package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.message.type.component.MessageComponentBase;
import ch.desm.middleware.modules.component.ComponentBase;

public abstract class Re420Base extends ComponentBase implements
		EndpointUbw32ListenerInterface {

	Re420EndpointUbw32 communicationEndpoint;

	public Re420Base(Broker broker,
			Re420EndpointUbw32 communicationEndpoint) {
		super(broker);
		
		this.communicationEndpoint = communicationEndpoint;
		this.registerEndpointListener(communicationEndpoint);
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			communicationEndpoint.addEndpointListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onIncomingBrokerMessage(MessageComponentBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		messageTranslator.translateTo(EnumComponentType.CabineRe420, message);
		
		communicationEndpoint.setHaupthahn(message.getElementId(), message.getValue());
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		 MessageComponentBase messageCommon = messageTranslator.translateToBroker(message);
		 publish(messageCommon);
	}
	
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}

	@Override
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateBrokerMessage(MessageComponentBase message) {
		onIncomingBrokerMessage(message);
	}
	
	public EnumComponentCategorie getType() {
		return EnumComponentCategorie.CABINE;
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentCategorie.INTERLOCKING.name(),
				EnumComponentCategorie.SIMULATION.name());
	}
}

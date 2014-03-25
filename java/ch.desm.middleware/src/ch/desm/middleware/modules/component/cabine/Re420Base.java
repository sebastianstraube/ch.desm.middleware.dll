package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
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
	protected void onIncomingBrokerMessage(MessageBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());

		//TODO route and transmit to endpoint
		MessageRouter router = new MessageRouter();
		MessageCommon common = new MessageCommon(message.getInstance(), message.getPayload());
		router.transmitMessage(communicationEndpoint, common);
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		//TODO 
		MessageTranslator messageTranslator = new MessageTranslator();
		MessageBase messageCommon = messageTranslator.translateToBrokerMessage(message);
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
	public void emulateBrokerMessage(MessageBase message) {
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

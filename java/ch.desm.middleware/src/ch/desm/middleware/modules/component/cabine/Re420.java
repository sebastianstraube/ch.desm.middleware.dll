package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.MessageCommon;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase.EnumEndpointType;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public class Re420 extends ComponentBase implements
		EndpointUbw32ListenerInterface {

	EndpointUbw32 communicationEndpointUbw32;

	public Re420(Broker broker,
			EndpointUbw32 communicationEndpointUbw32) {
		super(broker);
		
		this.communicationEndpointUbw32 = communicationEndpointUbw32;
		this.registerEndpointListener((EndpointBase)communicationEndpointUbw32);
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			communicationEndpointUbw32.addEndpointListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onIncomingBrokerMessage(MessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		messageTranslator.translate(communicationEndpointUbw32, message);
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		 MessageCommon messageCommon = messageTranslator.translate(EnumEndpointType.UBW32, message);
		 publish(messageCommon);
	}
	
	@Override
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
	public void emulateBrokerMessage(MessageCommon message) {
		onIncomingBrokerMessage(message);
	}
	
	public EnumComponentType getType() {
		return EnumComponentType.CABINE;
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentType.INTERLOCKING.name(),
				EnumComponentType.SIMULATION.name());
	}
}

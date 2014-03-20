package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.MessageCommon;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public class ObermattLangau extends ComponentBase
		implements EndpointUbw32ListenerInterface {

	EndpointCommon communicationEndpointUbw32;
	
	public ObermattLangau(Broker broker, EndpointCommon communicationEndpointUbw32) {
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
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	protected void onIncomingBrokerMessage(MessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		
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
	
	@Override
	public EnumComponentType getType() {
		return EnumComponentType.INTERLOCKING;
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentType.SIMULATION.name(),
				EnumComponentType.CABINE.name());
	}
}

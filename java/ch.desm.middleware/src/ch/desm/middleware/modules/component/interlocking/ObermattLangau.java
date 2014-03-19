package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public class ObermattLangau extends ComponentBase
		implements EndpointUbw32ListenerInterface {

	CommunicationEndpointCommon communicationEndpointUbw32;
	
	public ObermattLangau(Broker broker, CommunicationEndpointCommon communicationEndpointUbw32) {
		super(broker);
		this.communicationEndpointUbw32 = communicationEndpointUbw32;
		
		this.registerEndpointListener((CommunicationEndpointBase)communicationEndpointUbw32);
	}
	
	@Override
	protected void registerEndpointListener(
			CommunicationEndpointBase listener) {
		try {
			communicationEndpointUbw32.addEndpointListener(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	protected void onIncomingBrokerMessage(BrokerMessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		communicationEndpointUbw32.sendMessage(message);
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
	public void emulateBrokerMessage(BrokerMessageCommon message) {
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

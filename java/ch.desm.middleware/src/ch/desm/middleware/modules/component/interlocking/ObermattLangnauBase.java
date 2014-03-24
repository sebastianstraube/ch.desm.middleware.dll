package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.message.type.component.MessageComponentBase;
import ch.desm.middleware.modules.component.ComponentBase;

public abstract class ObermattLangnauBase extends ComponentBase
		implements EndpointUbw32ListenerInterface {

	ObermattLangnauEndpointUbw32 communicationEndpoint;
	
	public ObermattLangnauBase(Broker broker, EndpointCommon communicationEndpointUbw32) {
		super(broker);
		this.communicationEndpoint = (ObermattLangnauEndpointUbw32)communicationEndpointUbw32;
		
		this.registerEndpointListener(communicationEndpointUbw32);
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			communicationEndpoint.addEndpointListener(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
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
	
	@Override
	public EnumComponentCategorie getType() {
		return EnumComponentCategorie.INTERLOCKING;
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentCategorie.SIMULATION.name(),
				EnumComponentCategorie.CABINE.name());
	}
}

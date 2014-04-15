package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase;

public abstract class Re420Base extends ComponentBase implements
		EndpointUbw32ListenerInterface {

	Re420Ubw32 communicationEndpoint;
	
	public Re420Base(Broker broker,
			Re420Ubw32 communicationEndpoint) {
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
	
	public EnumComponentCategorie getType() {
		return EnumComponentCategorie.CABINE;
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentCategorie.INTERLOCKING.name(),
				EnumComponentCategorie.SIMULATION.name());
	}
	
	@Override
	public Map<String, String> getInpuDigitalOn() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> getInpuAnalogOn() {
		// TODO Auto-generated method stub
		return null;
	}
}

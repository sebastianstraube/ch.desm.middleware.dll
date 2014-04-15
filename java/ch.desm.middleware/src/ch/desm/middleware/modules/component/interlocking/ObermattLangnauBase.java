package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public abstract class ObermattLangnauBase extends ComponentBase
		implements EndpointUbw32ListenerInterface {

	private ObermattLangnauEndpointUbw32 communicationEndpoint;
	
	public ObermattLangnauBase(Broker broker, EndpointCommon communicationEndpointUbw32) {
		super(broker);
		this.communicationEndpoint = (ObermattLangnauEndpointUbw32)communicationEndpointUbw32;
		
		this.registerEndpointListener(communicationEndpointUbw32);
	}
	
	public ObermattLangnauEndpointUbw32 getEndpoint(){
		return this.communicationEndpoint;
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
	public void emulateBrokerMessage(String message) {
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
	
	@Override
	public Map<String, String> getInpuAnalogOn() {
		
		return null;
	}
	
	@Override
	public Map<String, String> getInpuDigitalOn() {
		// TODO Auto-generated method stub
		return null;
	}
}

package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.component.ComponentBase;

abstract class ObermattLangnauBase extends ComponentBase {

	protected ObermattLangnauEndpointUbw32 endpoint;
	
	public ObermattLangnauBase(Broker broker, EndpointCommon endpoint) {
		super(broker);
		this.endpoint = (ObermattLangnauEndpointUbw32)endpoint;
		
		this.registerEndpointListener(endpoint);
	}
	
	public ObermattLangnauEndpointUbw32 getEndpoint(){
		return this.endpoint;
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			endpoint.addEndpointListener(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
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
}

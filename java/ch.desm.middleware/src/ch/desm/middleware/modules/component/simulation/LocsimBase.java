package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.component.ComponentBase;

public abstract class LocsimBase extends ComponentBase{

	protected EndpointCommon communicationEndpoint;
	
	public enum EnumFunctionTypeLocsim{
		STUFENSCHALTER
	}
	
	public LocsimBase(Broker broker,
			EndpointCommon communicationEndpointDll) {
		
		super(broker);
		this.communicationEndpoint = communicationEndpointDll;
		
		this.registerEndpointListener((EndpointBase)communicationEndpointDll);
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
	
	@Override
	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	protected abstract void onIncomingBrokerMessage(MessageBase message);



	@Override
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateBrokerMessage(MessageBase message) {
		onIncomingBrokerMessage(message);
	}
	
	public EnumComponentCategorie getType() {
		return EnumComponentCategorie.SIMULATION;
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentCategorie.INTERLOCKING.name());
	}

}

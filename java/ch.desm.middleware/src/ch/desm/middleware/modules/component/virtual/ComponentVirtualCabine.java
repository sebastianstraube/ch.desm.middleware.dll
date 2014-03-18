package ch.desm.middleware.modules.component.virtual;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.communication.broker.message.type.component.cabine.CommunicationBrokerMessageTypeComponentCabineStufenschalter;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.virtual.CommunicationEndpointMessageVirtual;
import ch.desm.middleware.modules.component.ComponentBase;

public class ComponentVirtualCabine extends ComponentBase implements
		CommunicationEndpointUbw32ListenerInterface {

	CommunicationEndpointMessageVirtual communicationEndpointUbw32;

	public ComponentVirtualCabine(CommunicationBroker broker,
			CommunicationEndpointMessageVirtual communicationEndpointUbw32) {
		super(broker);
		
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
	/**
	 * TODO: refactor implementiation communicationEndpointUbw32.sendMessage(message.toString());
	 * 
	 * @param message
	 */
	protected void onIncomingBrokerMessage(CommunicationBrokerMessage message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		if(message instanceof CommunicationBrokerMessageTypeComponentCabineStufenschalter){
			communicationEndpointUbw32.sendMessage(message.toString());
		}else{
			try {
				throw new Exception("unknown message type");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
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
	public void emulateBrokerMessage(CommunicationBrokerMessage message) {
		onIncomingBrokerMessage(message);
	}
	
	public String getType() {
		return enumComponentType.CABINE.name();
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.INTERLOCKING.name(),
				enumComponentType.SIMULATION.name());
	}
}

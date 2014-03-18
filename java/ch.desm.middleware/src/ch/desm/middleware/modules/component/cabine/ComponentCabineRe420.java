package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.virtual.CommunicationEndpointMessageVirtual;
import ch.desm.middleware.modules.component.ComponentBase;

public class ComponentCabineRe420 extends ComponentBase implements
		CommunicationEndpointUbw32ListenerInterface {

	CommunicationEndpointMessageVirtual communicationEndpointUbw32;

	public ComponentCabineRe420(CommunicationBroker broker,
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
	
	public String getType() {
		return enumComponentType.CABINE.name();
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.INTERLOCKING.name(),
				enumComponentType.SIMULATION.name());
	}

	@Override
	protected void onBrokerMessage(CommunicationBrokerMessage message) {
		System.out.println("received a message from Broker:" + message
				+ " in class" + this.getClass());
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received a message:\"" + message
				+ "\" from endpoint " + this.getClass());
	}
}

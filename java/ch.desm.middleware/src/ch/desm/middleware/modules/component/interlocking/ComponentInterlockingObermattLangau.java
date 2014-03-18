package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class ComponentInterlockingObermattLangau extends ComponentAbstract
		implements CommunicationEndpointUbw32ListenerInterface {

	CommunicationEndpointUbw32 communicationEndpointUbw32;
	
	public ComponentInterlockingObermattLangau(CommunicationBroker broker, CommunicationEndpointUbw32 communicationEndpointUbw32) {
		super(broker);
		this.communicationEndpointUbw32 = communicationEndpointUbw32;
	}

	@Override
	public String getType() {
		return enumComponentType.INTERLOCKING.name();
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.SIMULATION.name(),
				enumComponentType.CABINE.name());
	}

	@Override
	protected void onBrokerMessage(CommunicationBrokerMessage message) {
		System.out.println("received a broker message:" + message + " in class"
				+ this.getClass());
	}

	@Override
	public void onEndpointMessage(String message) {
		System.out.println("received a message from Endpoint:" + message
				+ " in class" + this.getClass());
	}

}

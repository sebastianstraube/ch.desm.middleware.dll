package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.CommunicationEndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class ComponentCabineRe420 extends ComponentAbstract implements
		CommunicationEndpointUbw32ListenerInterface {

	CommunicationEndpointUbw32 communicationEndpointUbw32;

	public ComponentCabineRe420(CommunicationBroker broker,
			CommunicationEndpointUbw32 communicationEndpointUbw32) {
		super(broker);
		this.communicationEndpointUbw32 = communicationEndpointUbw32;
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
	public void onEndpointMessage(String message) {
		System.out.println("received a message from Endpoint:" + message
				+ " in class" + this.getClass());
	}

}

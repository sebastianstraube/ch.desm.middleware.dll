package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBroker;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232;
import ch.desm.middleware.modules.communication.endpoint.serial.CommunicationEndpointRs232ListenerInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class ComponentSimulationLocsim extends ComponentAbstract implements CommunicationEndpointRs232ListenerInterface{
	
	CommunicationEndpointRs232 communicationEndpointRs232;
	
	public ComponentSimulationLocsim(CommunicationBroker broker, CommunicationEndpointRs232 communicationEndpointRs232 ){
		super(broker);
		this.communicationEndpointRs232 = communicationEndpointRs232;
	}

	public String getType() {
		return enumComponentType.SIMULATION.name();
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.INTERLOCKING.name());
	}

	@Override
	protected void onBrokerMessage(CommunicationBrokerMessage message) {
		System.out.println("received a broker message:" + message + " in class" + this.getClass());
		communicationEndpointRs232.sendMessage(message.toString());
	}
	


	@Override
	public void onEndpointMessage(String message) {
		System.out.println("received a message from Endpoint:" + message + " in class" + this.getClass());
	}

	
	/**
     * TEST TEST TEST TEST
     * @param message
     */
    public void emulateEndpointMessage(String message) {
    	onEndpointMessage(message);
    }
}

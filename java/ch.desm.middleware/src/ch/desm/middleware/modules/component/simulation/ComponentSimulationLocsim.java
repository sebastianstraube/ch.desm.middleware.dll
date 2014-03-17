package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class ComponentSimulationLocsim extends ComponentAbstract{
	
	ComponentSimulationLocsimListener listener;
	
	public ComponentSimulationLocsim(CommunicationBrokerHandler broker){
		super(broker);
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
	}

}

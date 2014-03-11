package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessageInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class ComponentSimulationLocsim extends ComponentAbstract{
	
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
	protected void onBrokerMessage(CommunicationBrokerMessageInterface message) {
		// TODO Auto-generated method stub
		
	}

}

package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class Locsim extends ComponentAbstract{
	
	public Locsim(BrokerHandler broker){
		super(broker);
	}

	public String getType() {
		return enumComponentType.SIMULATION.name();
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.INTERLOCKING.name());
	}

	@Override
	protected void onBrokerMessage(BrokerMessageInterface message) {
		// TODO Auto-generated method stub
		
	}

}

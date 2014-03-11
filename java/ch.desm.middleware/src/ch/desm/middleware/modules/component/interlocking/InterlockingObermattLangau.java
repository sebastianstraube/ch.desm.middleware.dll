package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class InterlockingObermattLangau extends ComponentAbstract{
	
	public InterlockingObermattLangau(BrokerHandler broker){
		super(broker);
	}

	@Override
	public String getType() {
		return enumComponentType.INTERLOCKING.name();
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.SIMULATION.name());
	}

	@Override
	protected void onBrokerMessage(BrokerMessageInterface message) {
		// TODO Auto-generated method stub
		
	}

}

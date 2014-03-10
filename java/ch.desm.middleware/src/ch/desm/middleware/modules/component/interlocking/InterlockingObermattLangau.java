package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerMessageBridge;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class InterlockingObermattLangau extends ComponentAbstract{
	
	public InterlockingObermattLangau(Broker broker){
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
	protected void onBrokerMessage(BrokerMessageBridge message) {
		// TODO Auto-generated method stub
		
	}

}

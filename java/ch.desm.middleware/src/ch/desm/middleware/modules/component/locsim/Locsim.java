package ch.desm.middleware.modules.component.locsim;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.BrokerMessageBridge;
import ch.desm.middleware.modules.component.ComponentAbstract.enumComponentType;


public class Locsim extends BrokerClient{
	
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
	protected void onBrokerMessage(BrokerMessageBridge message) {
		// TODO Auto-generated method stub
		
	}

}

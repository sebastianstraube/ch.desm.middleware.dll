package ch.desm.middleware.modules.component.cabine;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class Re460 extends ComponentAbstract{

	public Re460(BrokerHandler broker){
		super(broker);
	}
	
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getRequiredTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onBrokerMessage(BrokerMessageInterface message) {
		// TODO Auto-generated method stub
		
	}
}

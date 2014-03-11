package ch.desm.middleware.modules.component.re460;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageInterface;

public class Re460 extends BrokerClient{

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

package ch.desm.middleware.modules.component.re460;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.BrokerMessageBridge;

public class Re460 extends BrokerClient{

	public Re460(Broker broker){
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
	protected void onBrokerMessage(BrokerMessageBridge message) {
		// TODO Auto-generated method stub
		
	}
}

package ch.desm.middleware.modules.component.zusi;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerHandler;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.BrokerMessageBridge;

public class Zusi extends BrokerClient{

	public Zusi(BrokerHandler broker){
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

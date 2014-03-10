package ch.desm.middleware.modules.component.re420;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.broker.BrokerMessageBridge;

public class Re420 extends BrokerClient{
	
	public Re420(Broker broker){
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

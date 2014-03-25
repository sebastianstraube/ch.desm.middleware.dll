package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;

public class ObermattLangnauImplUbw32 extends ObermattLangnauBase implements ObermattLangnauListenerUbw32  {

	public ObermattLangnauImplUbw32(Broker broker,
			EndpointCommon communicationEndpointUbw32) {
		super(broker, communicationEndpointUbw32);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void onIncomingBrokerMessage(MessageBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
	
		
		//TODO route and transmit to endpoint
		MessageRouter router = new MessageRouter();
		MessageCommon common = new MessageCommon(message.getInstance(), message.getPayload());
		router.transmitMessage(communicationEndpoint, common);
	}
	
	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		
	}
	
	@Override
	public void onHaupthahn(String position) {
		// TODO Auto-generated method stub
		System.out.println("haupthahn.position = " + position);
		
		
	}



	
}

package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;

public class ObermattLangnauImplUbw32 extends ObermattLangnauBase {

	public ObermattLangnauImplUbw32(Broker broker,
			EndpointCommon communicationEndpointUbw32) {
		super(broker, communicationEndpointUbw32);
		// TODO Auto-generated constructor stub
	}
	
	protected void onIncomingBrokerMessage(MessageBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());

		//TODO route and transmit to endpoint
		MessageRouter router = new MessageRouter();
		router.processBrokerMessage(communicationEndpoint, (MessageCommon)message);
	}	
}

package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;

public class Re420EndpointImplUbw32 extends Re420Base {

	public Re420EndpointImplUbw32(Broker broker,
			Re420EndpointUbw32 communicationEndpoint) {
		super(broker, communicationEndpoint);
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

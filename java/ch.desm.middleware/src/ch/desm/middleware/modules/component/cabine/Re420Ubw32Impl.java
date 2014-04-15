package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.message.MessageBase.EnumMessageTopic;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;

public class Re420Ubw32Impl extends Re420Base {

	public Re420Ubw32Impl(Broker broker,
			Re420Ubw32 communicationEndpoint) {
		super(broker, communicationEndpoint);
		// TODO Auto-generated constructor stub
	}

	protected void onIncomingBrokerMessage(MessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());

		//TODO route and transmit to endpoint
		MessageRouter router = new MessageRouter();
		router.processBrokerMessage(communicationEndpoint, (MessageCommon)message);
	}
	
	/**
	 * TODO implementation
	 * @param message
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());

		MessageCommon messageCommon = null;
		
		MessageTranslator translator = new MessageTranslator();
		MessageUbw32 ubw32Message = translator.decodeUbw32EndpointMessage(message, EnumMessageTopic.INTERLOCKING);
		
		
		
		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, messageCommon);
	}
	


}

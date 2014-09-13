package ch.desm.middleware.component.interlocking.obermattlangnau;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.communication.message.MessageBase;
import ch.desm.middleware.communication.message.MessageCommon;
import ch.desm.middleware.communication.message.MessageMiddleware;
import ch.desm.middleware.communication.message.MessageUbw32Base;
import ch.desm.middleware.communication.message.processor.MessageProcessor;
import ch.desm.middleware.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.component.interlocking.obermattlangnau.maps.OMLMapMiddleware;

public class OML extends OMLBase implements
		EndpointUbw32ListenerInterface {

	private static Logger log = Logger.getLogger(OML.class);

	private MessageTranslatorMiddleware translator;
	private MessageProcessor router;
	private OMLMessageProcessor processor;
	private OMLMapMiddleware map;
	
	public OML(Broker broker, OMLEndpointUbw32 endpoint) {
		super(broker, endpoint);
		
		this.translator = new MessageTranslatorMiddleware();
		this.router = new MessageProcessor();
		this.processor = new OMLMessageProcessor();
		this.map = new OMLMapMiddleware();
		
		endpoint.startPolling();
		endpoint.setCacheEnabled(true);
	}

	protected void onIncomingBrokerMessage(String message) {
		
		log.info("broker (" + this.getClass() + ") received message: " + message);
		
		ArrayList<MessageMiddleware> messageCommon = translator
				.translateToCommonMiddlewareMessageList(message);

		router.processBrokerMessage(this, messageCommon);
	}

	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	public void onIncomingEndpointMessage(String message) {
		log.info("endpoint (" + getEndpoint().getSerialPortName() + ") received message: " + message);
		
		//TODO implementation
		MessageUbw32Base ubw32Message = translator
				.decodeUbw32EndpointMessage(message,
						MessageCommon.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);

		//processable message
		if(ubw32Message != null){
					
			String messages = processor.convertToMiddlewareMessage(this, ubw32Message, map);
			
			router.processEndpointMessage(this, messages,
					MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
		}
	}

	/**
	 * 
	 */
	@Override
	protected void intializeSignedTopic() {
		signForTopic(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
		signForTopic(MessageBase.MESSAGE_TOPIC_PETRINET_OBERMATT_LANGNAU);
	}

}

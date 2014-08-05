package ch.desm.middleware.modules.component.simulation.locsim;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDllListenerInterface;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.MessageCommon;
import ch.desm.middleware.modules.communication.message.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.processor.MessageProcessorBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.modules.component.simulation.locsim.messages.LocsimMessageDll;

public class LocsimBaseImpl extends LocsimBase implements
		EndpointDllListenerInterface {

	private static Logger log = Logger.getLogger(LocsimBaseImpl.class);

	public LocsimMessageProcessorRs232 translatorRs232;
	public MessageRouter router;
	public MessageProcessorBase converter;
	MessageTranslatorMiddleware translator;
	
	boolean initialisiert;

	public LocsimBaseImpl(Broker broker, LocsimEndpointRs232 endpointRs232,
			LocsimEndpointDll endpointDll) {
		super(broker, endpointRs232, endpointDll);

		translatorRs232 = new LocsimMessageProcessorRs232();
		router = new MessageRouter();
		translator = new MessageTranslatorMiddleware();
	}

	@Override
	public void onIncomingEndpointMessage(String message) {

		if(!message.isEmpty()){
			if (isLocsimDllMessage(message)) {
				log.trace("endpoint (" + this.getClass() + ") received message DLL: " + message);
				processIncomingDataDll(message);

			} else {
				log.trace("endpoint (" + this.getClass() + ") received message RS232: " + message);
				processIncomingDataRs232(message);
			}
		}
	}

	/**
	 * TODO implementation
	 */
	@Override
	protected void onIncomingBrokerMessage(String message) {
		
		log.trace("broker (" + this.getClass() + ") received message: " + message);

		ArrayList<MessageMiddleware> messageList = translator
				.translateToCommonMiddlewareMessageList(message);

		router.processBrokerMessage(this, messageList);
	}

	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	private void processIncomingDataDll(String message) {

		LocsimMessageDll messageDll = new LocsimMessageDll(message,
				MessageCommon.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);

		String stream = "";

		if (messageDll.isGleislistMessage()) {
			System.out
					.println("processEndpointDataDll: not yet supported message: "
							+ message);

		} else if (messageDll.isSignalMessage()) {
			// TODO implementation
			log.fatal("processEndpointDataDll: not yet supported message: "
					+ message);
		} else if (messageDll.isTrackMessage()) {
			// TODO implementation
			log.fatal("processEndpointDataDll: not yet supported message: "
					+ message);

		} else if (messageDll.isTrainpositionMessage()) {
			// TODO implementation
			log.fatal("processEndpointDataDll: not yet supported message: "
					+ message);

		} else if (messageDll.isWeicheMessage()) {
			// TODO implementation
			log.fatal("processEndpointDataDll: not yet supported message: "
					+ message);

		} else {
			log.fatal("processEndpointDataDll: not yet supported message: "
					+ message);
		}

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, stream,
				MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
	}

	/**
	 * processing all incoming rs232 data from locsim
	 * 
	 * @param data
	 */
	private void processIncomingDataRs232(String data) {
		endpointRs232.parser.addData(data);

		String message;
		while ((message = endpointRs232.parser.pop()) != null) {

			log.trace("receive endpoint("+ endpointRs232.getSerialPortName()+") locsim message: " + message);
			if (message.startsWith("INI")) {

				//TODO implementation different states of locsim (INI8)
				if (!initialisiert && message.equalsIgnoreCase("INI1")) {
					message = "locsim.initialization.ready.ini1;os;0;message;initialisiation;ini1;on;locsim-rs232;#";
					router.processEndpointMessage(this, message,
							MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
					initialisiert = true;
					
					this.endpointRs232.sendMessage("INI2");
				}
				
				else if (message.equalsIgnoreCase("INI7")) {
					message = "locsim.initialization.ready.ini7;os;0;message;initialisiation;ini7;on;locsim-rs232;#";
					router.processEndpointMessage(this, message,
							MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
				}
				
				else{
					log.trace("not implemented or skipped initialisation message: " + message);
				}				
			} else {

				message = translatorRs232
						.translateToCommonMiddlewareMessage(message, MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);

				router.processEndpointMessage(this, message,
						MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
			}
		}
	}

	public boolean isLocsimDllMessage(String message) {

		if (message.startsWith(LocsimMessageDll.DLL_MESSAGE_GLEISLIST)
				|| message.startsWith(LocsimMessageDll.DLL_MESSAGE_SIGNAL)
				|| message.startsWith(LocsimMessageDll.DLL_MESSAGE_TRACK)
				|| message
						.startsWith(LocsimMessageDll.DLL_MESSAGE_TRAINPOSITION)
				|| message.startsWith(LocsimMessageDll.DLL_MESSAGE_WEICHE)) {
			return true;
		}

		return false;
	}
	
	public boolean isLocsimSoftwareMessage(String outputInput) {

		if (outputInput.equalsIgnoreCase("os")) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean hasTopicSigned(String topic) {
		return signedTopic.contains(topic);
	}

	/**
	 * 
	 */
	@Override
	protected void intializeSignedTopic() {
		signedTopic.add(MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_CABINE_RE420);
	}

}
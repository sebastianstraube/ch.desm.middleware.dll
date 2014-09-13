package ch.desm.middleware.component.simulation.locsim;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.communication.endpoint.dll.EndpointDllListenerInterface;
import ch.desm.middleware.communication.endpoint.serial.EndpointRs232ListenerInterface;
import ch.desm.middleware.communication.message.MessageBase;
import ch.desm.middleware.communication.message.MessageCommon;
import ch.desm.middleware.communication.message.MessageMiddleware;
import ch.desm.middleware.communication.message.processor.MessageProcessor;
import ch.desm.middleware.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.component.simulation.locsim.messages.LocsimMessageDll;

public class Locsim extends LocsimBase implements
		EndpointDllListenerInterface, EndpointRs232ListenerInterface {

	private static Logger log = Logger.getLogger(Locsim.class);

	public LocsimMessageProcessorRs232 translatorRs232;
	public MessageProcessor processor;
	public MessageTranslatorMiddleware translator;
	
	boolean initialisiert;

	public Locsim(Broker broker, LocsimEndpointRs232 endpointRs232,
			LocsimEndpointDll endpointDll) {
		super(broker, endpointRs232, endpointDll);

		translatorRs232 = new LocsimMessageProcessorRs232();
		processor = new MessageProcessor();
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

		processor.processBrokerMessage(this, messageList);
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

		processor.processEndpointMessage(this, stream,
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
					processor.processEndpointMessage(this, message,
							MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
					initialisiert = true;
					
					this.endpointRs232.sendMessage("INI2");
				}
				
				else if (message.equalsIgnoreCase("INI7")) {
					message = "locsim.initialization.ready.ini7;os;0;message;initialisiation;ini7;on;locsim-rs232;#";
					processor.processEndpointMessage(this, message,
							MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
				}
				
				else{
					log.warn("not implemented or skipped initialisation message: " + message);
				}				
			} else {

				message = translatorRs232
						.translateToCommonMiddlewareMessage(message, MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);

				processor.processEndpointMessage(this, message,
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

	@Override
	protected void intializeSignedTopic() {
        signForTopic(MessageBase.MESSAGE_TOPIC_CABINE_RE420);
        signForTopic(MessageBase.MESSAGE_TOPIC_CABINE_RE420_FABISCH);
        signForTopic(MessageBase.MESSAGE_TOPIC_PETRINET_OBERMATT_LANGNAU);
	}

}

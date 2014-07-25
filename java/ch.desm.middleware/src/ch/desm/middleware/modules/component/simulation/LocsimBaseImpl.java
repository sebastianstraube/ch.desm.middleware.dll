package ch.desm.middleware.modules.component.simulation;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDllListenerInterface;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageLocsimDll;
import ch.desm.middleware.modules.communication.message.type.MessageLocsimRs232;
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;

public class LocsimBaseImpl extends LocsimBase implements
		EndpointDllListenerInterface {

	private LocsimEndpointDllData dataDll;
	private LocsimEndpointRs232Data dataRs232;
	private LocsimEndpointRs232Parser rs232Parser;
	private LocsimMiddlewareMessagesRs232 midllewareMessagesRs232;
	private LocsimMiddlewareMessagesDll middlewareMessagesDll;

	private final String initializationMessage = "locsim.rs232.config.ini1;os;0;configuration;initialisation;start;ini1;locsim-rs232-configuration;#";
	
	public LocsimBaseImpl(Broker broker, LocsimEndpointRs232 endpointRs232,
			LocsimEndpointDll endpointDll) {
		super(broker, endpointRs232, endpointDll);

		this.dataDll = new LocsimEndpointDllData();
		this.dataRs232 = new LocsimEndpointRs232Data();
		this.rs232Parser = new LocsimEndpointRs232Parser();
		this.middlewareMessagesDll = new LocsimMiddlewareMessagesDll();
		this.midllewareMessagesRs232 = new LocsimMiddlewareMessagesRs232();

		this.initializeLocsim();
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.print("endpoint (" + this.getClass() + ") received message: ");
		
		if(!message.isEmpty()){
			System.out.println(Byte.valueOf(message));
		}

		if (dataDll.isLocsimDllMessage(message)) {
			processEndpointDataDll(message);

		} else if (dataRs232.isLocsimRs232DataMessage(message)) {
			processEndpointDataRs232(message);

			// TODO implementation middleware messages
		} else if (dataRs232.isLocsimRs232ConfigurationMessage(message)) {
			processConfiguration(message);

			// TODO implementation middleware messages
		} else {
			System.err
					.println("error no valid endpoint data to process message: "
							+ message);
		}
	}

	/**
	 * TODO implementation
	 */
	@Override
	protected void onIncomingBrokerMessage(String message) {
		System.out.println("endpoint (" + this.getClass()
				+ ") received broker message: " + message);

		MessageTranslatorMiddleware translator = new MessageTranslatorMiddleware();

		ArrayList<MessageMiddleware> messageList = translator
				.translateToCommonMessageObjectList(message);

		for (MessageMiddleware element : messageList) {
			
			if (dataDll.isLocsimDllMessage(element.getGlobalId())) {
				System.out.println("dll messsage");
				//TODO implementation
				
			} else if (dataRs232.isLocsimRs232ConfigurationMessage(element.getGlobalId())) {

				endpointRs232.sendMessage(element.getParameter().toUpperCase());

			} else if (dataRs232.isLocsimRs232DataMessage(element.getGlobalId())) {
				String locsimRs232Message = initializationMessage;

				locsimRs232Message = dataRs232.getValueFromMapGlobalIdToLocsimRs232(element.getGlobalId());
				
				endpointRs232.sendMessage(locsimRs232Message);

			} else {

			}
		}
	}

	/**
	 * Locsim initialization commands
	 * 
	 * 'INI1' PC -> SPS Reset
	 * 'INI2' SPS -> PC Reset done 
	 * 'INI7' PC -> SPS Refresh 
	 * 'INI8' PC -> SPS Don't send
	 */
	private void processConfiguration(String message) {

	}

	/**
	 * TODO implementation
	 * 
	 * send "INI1" command to Locsim
	 */
	private void initializeLocsim() {
			publish(initializationMessage,
					MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232_CONFIG);		
	}

	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	private void processEndpointDataDll(String message) {

		MessageLocsimDll messageDll = new MessageLocsimDll(message, MessageCommon.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);

		String stream = "";
 
		if (messageDll.isGleislistMessage()) {
			System.out.println("processEndpointDataDll: not yet supported message: " + message);

		} else if (messageDll.isSignalMessage()) {

			String id = messageDll.getGlobalId() + "." + messageDll.getDataId();
			stream = middlewareMessagesDll.getMiddlewareMessageFromGlobalId(id);

			if (stream == null) {
				try {
					throw new Exception("missing mapping with dll message: "
							+ messageDll + " in endpoint message: " + message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				stream = stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER,
						messageDll.getValue());
			}

		} else if (messageDll.isTrackMessage()) {
//			TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);

		} else if (messageDll.isTrainpositionMessage()) {
//			TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);

		} else if (messageDll.isWeicheMessage()) {
//			TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);

		} else {
			System.err.println("processEndpointDataDll: not supported message: " + message);
		}

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, stream, MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
	}

	/**
	 * @param data
	 */
	private void processEndpointDataRs232(String data) {
		rs232Parser.addData(data);
		
		String message;
		while((message = rs232Parser.pop()) != null) {
			
		}
		
		MessageLocsimRs232 messageRs232 = new MessageLocsimRs232(message, MessageCommon.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
		String globalId = dataRs232.getGlobalIdFromMapGlobalIdToLocsimRs232(messageRs232.getSignalType(), messageRs232.getChannel());
		String stream = middlewareMessagesDll.getMessages().get(globalId);
		
		//TODO implementation of different data value (global message)
		String parameter = (messageRs232.isCommandEnable() ? "on" : "off");
		
		stream = stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, parameter);

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, stream, MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
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
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232_CONFIG);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_CABINE_RE420);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_TEST);
	}
}

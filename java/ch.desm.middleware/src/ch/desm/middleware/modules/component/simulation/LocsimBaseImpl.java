package ch.desm.middleware.modules.component.simulation;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDllListenerInterface;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageBase.EnumMessageTopic;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageLocsimRs232;
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;

public class LocsimBaseImpl extends LocsimBase implements
		EndpointDllListenerInterface {

	private LocsimEndpointDllData dataDll;
	private LocsimEndpointRs232Data dataRs232;
	private LocsimMiddlewareMessagesRs232 midllewareMessagesRs232;
	private LocsimMiddlewareMessagesDll middlewareMessagesDll;
	

	public LocsimBaseImpl(Broker broker, LocsimEndpointRs232 endpointRs232,
			LocsimEndpointDll endpointDll) {
		super(broker, endpointRs232, endpointDll);

		this.dataDll = new LocsimEndpointDllData();
		this.dataRs232 = new LocsimEndpointRs232Data();
		this.middlewareMessagesDll = new LocsimMiddlewareMessagesDll();
		this.midllewareMessagesRs232 = new LocsimMiddlewareMessagesRs232();

		this.initializeLocsim();
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("locsim endpoint received message: " + message);

		// is data message
		if (isConfigurationMessage(message)) {
			processConfiguration(message);
		} else {
			processEndpointData(message);
		}
	}
	
	/**
	 * TODO implementation
	 */
	@Override
	protected void onIncomingBrokerMessage(String message) {
		System.out.println("endpoint received broker message: " + message);

		MessageTranslatorMiddleware translator = new MessageTranslatorMiddleware();
		
		ArrayList<MessageMiddleware> messageList = translator.translateToCommonMessageObjectList(message, EnumMessageTopic.SUMLATION);
		
		for(MessageMiddleware element : messageList){
			
			//is dll message
			if(dataDll.isLocsimDllMessage(element.getGlobalId())){
				System.out.println("receive locsim dll broker message: " + element.getPayload());
			
				
			//is rs232 message
			}else if(dataDll.isLocsimDllMessage(element.getGlobalId())){
				System.out.println("receive locsim rs232 broker message: " + element.getPayload());
				
				
			}
		}
	}

	/**
	 * is the message a locsim configuration then return true, else false
	 * 
	 * @param message
	 * @return
	 */
	private boolean isConfigurationMessage(String message) {
		if (message.contains(LocsimEndpointRs232Data.LOCSIM_INIT_MESSAGE)) {
			return true;
		}

		return false;
	}

	/**
	 * Locsim initialization commands
	 * 
	 * 'INI1' PC -> SPS Reset
	 * 'INI2' SPS -> PC Reset done 
	 * 'INI7' PC -> SPS Refresh
	 * 'INI8' PC -> SPS Don't send
	 * 
	 * DO NOTHING plz
	 * 
	 */
	private void processConfiguration(String message){}

	/**
	 * TODO implementation
	 * 
	 * send "INI1" command to Locsim
	 */
	private void initializeLocsim() {
		publish(LocsimEndpointRs232Data.LOCSIM_INIT_MESSAGE + "1");
	}

	private void processEndpointData(String message) {

		if (!dataDll.isLocsimDllMessage(message)) {
			processDataDll(message);

		} else if (!dataRs232.isLocsimRs232Message(message)) {
			processDataRs232(message);

		} else {
			System.err
					.println("no valid endpoint data found to process message: "
							+ message);

		}
	}

	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	private void processDataDll(String message) {

	}

	/**
	 * 
	 * Message Definition:
	 * 
	 * 0		1			2-3		4-7			8
	 * start 	Signalart 	Kanal 	Data 		ende
	 * [X] 		[D,L,S,U,V] [00-99] [0000-FFFF]	[Y]
	 * [X]		[U,V] 		[00-99] [0000-FFFF] [Y]
	 * 
	 * @param message
	 */
	private void processDataRs232(String message) {
		MessageLocsimRs232 messageRs232 = new MessageLocsimRs232(message, EnumMessageTopic.SUMLATION);

		String globalId = dataRs232.getGlobalIdFromMapGlobalIdToLocsimRs232(message);
		String stream = middlewareMessagesDll.getMessages().get(globalId);
		
		if(messageRs232.isCommandEnable()){
			stream = stream
					.replaceAll(
							MessageCommon.PARAMETER_PLACEHOLDER,
							"on");
		}else{
			stream = stream
					.replaceAll(
							MessageCommon.PARAMETER_PLACEHOLDER,
							"off");
		}
		
		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, stream);
	}
}

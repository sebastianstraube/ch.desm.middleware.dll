package ch.desm.middleware.modules.component.simulation.locsim;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDllListenerInterface;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.component.simulation.locsim.messages.LocsimMessageDll;
import ch.desm.middleware.modules.component.simulation.locsim.messages.LocsimMessageTranslatorRs232;

public class LocsimBaseImpl extends LocsimBase implements
		EndpointDllListenerInterface {
	
	LocsimMessageTranslatorRs232 translatorRs232;
	MessageRouter router;
	
	public LocsimBaseImpl(Broker broker, LocsimEndpointRs232 endpointRs232,
			LocsimEndpointDll endpointDll) {
		super(broker, endpointRs232, endpointDll);
		
		translatorRs232 = new LocsimMessageTranslatorRs232();
		router = new MessageRouter();
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.print("endpoint (" + this.getClass()
				+ ") received message: ");

		if (isLocsimDllMessage(message)) {
			processIncomingDataDll(message);

		} else {
			processIncomingDataRs232(message);
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
			//TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);
		} else if (messageDll.isTrackMessage()) {
			// TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);

		} else if (messageDll.isTrainpositionMessage()) {
			// TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);

		} else if (messageDll.isWeicheMessage()) {
			// TODO implementation
			// System.out.println("processEndpointDataDll: not yet supported message: "
			// + message);

		} else {
			// System.err
			// .println("processEndpointDataDll: not supported message: "
			// + message);
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

			//process the initaition of locsim
			if (endpointRs232.isLocsimInitializationMessage(message)) {
				if (!endpointRs232.isLocsimInitialized) {
					
//					TODO refactoring
//					need to send all cabine states to locsim
//					cabine send the state every 250ms
//					implementation not yet needed
//					to improve perfomance introduce middleware message
					
					//send status message to locsim
					endpointRs232.sendMessage("INI2");
					
					endpointRs232.isLocsimReadyToInitialize = true;
				}

			} else {

				// TODO process locsim data
				message = translatorRs232.translateToCommonMiddlewareMessage(data);
				
				MessageRouter router = new MessageRouter();
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
		signedTopic
				.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232_CONFIG);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
		signedTopic
				.add(MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_CABINE_RE420);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_TEST);
	}
	
	
}

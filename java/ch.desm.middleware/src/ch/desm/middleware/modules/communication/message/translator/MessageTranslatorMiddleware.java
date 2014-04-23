package ch.desm.middleware.modules.communication.message.translator;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;

/**
 * TODO implementation
 * 
 * @author Sebastian
 *
 */
public class MessageTranslatorMiddleware extends MessageTranslatorMiddlewareBase {

	
	public MessageTranslatorMiddleware(){
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param message
	 * 
	 */
	public ArrayList<MessageMiddleware> translateToCommonMessageObjectList(String message){
		return decodeMiddlewareMessages(message);
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param commonMessage
	 * 
	 */
	public String translateToCommonMiddlewareMessageStream(MessageMiddleware commonMessage){
		return encodeMiddlewareMessage(commonMessage);
	}
	
	/**
	 * 
	 * @param payload
	 * @return
	 */
	public MessageUbw32 decodeUbw32EndpointMessage(String payload, String topic){
		MessageUbw32 messageUbw32 = new MessageUbw32(payload, topic);
		
		return messageUbw32;
	}
	
	
}

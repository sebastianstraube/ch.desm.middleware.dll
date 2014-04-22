package ch.desm.middleware.modules.communication.message.translator;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;
import ch.desm.middleware.modules.communication.message.type.MessageBase.EnumMessageTopic;

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
	public ArrayList<MessageMiddleware> translateToCommonMessageObjectList(String message, EnumMessageTopic topic){
		return decodeMiddlewareMessages(message, topic);
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
	public MessageUbw32 decodeUbw32EndpointMessage(String payload, EnumMessageTopic topic){
		MessageUbw32 messageUbw32 = new MessageUbw32(payload, topic);
		
		return messageUbw32;
	}
	
	
}

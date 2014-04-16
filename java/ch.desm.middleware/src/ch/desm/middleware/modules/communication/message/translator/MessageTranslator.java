package ch.desm.middleware.modules.communication.message.translator;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.message.MessageBase.EnumMessageTopic;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;

/**
 * TODO implementation
 * 
 * @author Sebastian
 *
 */
public class MessageTranslator extends MessageTranslatorBase {

	
	public MessageTranslator(){
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param message
	 * 
	 */
	public ArrayList<MessageCommon> translateToCommonMessageObjectList(String message, EnumMessageTopic topic){
		return decodeMiddlewareMessages(message, topic);
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param commonMessage
	 * 
	 */
	public String translateToCommonMiddlewareMessageStream(MessageCommon commonMessage){
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

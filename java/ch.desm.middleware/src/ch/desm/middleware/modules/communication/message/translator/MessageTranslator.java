package ch.desm.middleware.modules.communication.message.translator;

import java.util.ArrayList;
import java.util.HashMap;

import ch.desm.middleware.modules.communication.message.MessageBase.EnumMessageTopic;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauEndpointUbw32Configuration;

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
	public ArrayList<MessageCommon> translateMiddlewareMessageStreamToCommonMessageObject(String message, EnumMessageTopic topic){
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
	
	/**
	 * TODO Implementation
	 * 
	 * @param commonMessage
	 * 
	 */
	public MessageCommon[] translateToCommonMiddlewareMessageObject(MessageUbw32 messageUbw32){
		
		if(messageUbw32.topic.equals(EnumMessageTopic.INTERLOCKING)){
			
			
		}else if(messageUbw32.topic.equals(EnumMessageTopic.CABINE)){
			//TODO implementation
			System.out.println("message topic not yet implemented");
			
		}else if(messageUbw32.topic.equals(EnumMessageTopic.SUMLATION)){
			//TODO implementation
			System.out.println("message topic not yet implemented");
			
		}else{
			System.err.println("message topic not implemented");
		}
		
		
		
//		MessageCommon messageCommon = new MessageCommon();
		
		return null;
	}
}

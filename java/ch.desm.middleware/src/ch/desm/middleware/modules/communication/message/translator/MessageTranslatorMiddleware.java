package ch.desm.middleware.modules.communication.message.translator;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32Analog;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32Digital;

/**
 * TODO implementation
 * 
 * @author Sebastian
 *
 */
public class MessageTranslatorMiddleware extends MessageTranslatorMiddlewareBase {
	
	/**
	 * 
	 * @param message
	 * 
	 */
	public MessageMiddleware translateToCommonMiddlewareMessage(String message){
		return decodeMiddlewareMessage(message);
	}
	
	/**
	 * 
	 * @param stream
	 * 
	 */
	public ArrayList<MessageMiddleware> translateToCommonMiddlewareMessageList(String stream){
		return decodeMiddlewareMessages(stream);
	}
	
	/**
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
	 * @param topic
	 * @return
	 */
	public MessageUbw32Digital decodeUbw32EndpointMessage(String payload, String topic){
		MessageUbw32Digital messageUbw32 = new MessageUbw32Digital(payload, topic);
		
		return messageUbw32;
	}
	
	/**
	 * 
	 * @param payload
	 * @param topic
	 * @return
	 */
//	public MessageUbw32Analog decodeUbw32EndpointMessage(String payload, String topic){
//		MessageUbw32Analog messageUbw32 = new MessageUbw32Analog(payload, topic);
//		
//		return messageUbw32;
//	}
	
	

	
}

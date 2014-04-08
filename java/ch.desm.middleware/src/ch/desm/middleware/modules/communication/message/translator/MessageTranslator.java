package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.message.type.MessageCommon;

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
	public MessageCommon translateCommonMessageToMessageObject(String message){
		return decodeMessage(message);
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param commonMessage
	 * 
	 */
	public String translateToCommonMiddlewareMessage(MessageCommon commonMessage){
		return encodeMessage(commonMessage);
	}
}

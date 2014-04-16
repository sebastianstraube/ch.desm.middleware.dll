package ch.desm.middleware.modules.communication.message.translator;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.MessageBase.EnumMessageTopic;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;

public abstract class MessageTranslatorBase {

	private static final String ELEMENT_CUT = ";";
	private static final String MESSAGE_CUT = "#";
	
	/**
	 * Array Positions
	 */
	private static final int ID = 0;
	private static final int EXTERN_INTERN = 1;
	private static final int ELEMENT = 2;
	private static final int FUNCTION = 3;
	private static final int INSTANCE= 4;
	private static final int PARAMETER = 5;
	
	protected ArrayList<MessageCommon> decodeMiddlewareMessages(String message, EnumMessageTopic topic){
		String[] messageArray = message.split(MESSAGE_CUT);
		ArrayList<MessageCommon> messageList = new ArrayList<MessageCommon>();
		
		for(int i=0; i < messageArray.length; i++){
			messageList.add(decodeMiddlewareMessage(message, topic));
		}		
		return messageList;
	}
	
	/**
	 * decodes a message to fit the message object
	 * 
	 * TODO refactor EnumMessageTopic topic, move it to stanard message stream (Excel)
	 * 
	 * @param message
	 * @return {@link MessageBase}
	 */
	private MessageCommon decodeMiddlewareMessage(String message, EnumMessageTopic topic){
		MessageCommon messageCommon = null;
		try {
			if(message == null || message.isEmpty()){
				throw new Exception("there is no message to translate");
			}else{
				String[] parts = message.split(MESSAGE_CUT);
				messageCommon = new MessageCommon(topic, parts[ID], parts[ELEMENT], parts[FUNCTION], parts[INSTANCE], parts[PARAMETER], parts[EXTERN_INTERN], message);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageCommon;
	}
	
	
	/**
	 * TODO implementation
	 * decode to the common middleware message
	 * 
	 * @param
	 * @param
	 */
	protected String encodeMiddlewareMessage(MessageCommon message) {

		String endpointMessage = "";
		endpointMessage += message.getGlobalId();
		endpointMessage += ELEMENT_CUT;
		endpointMessage += message.getProcess();
		endpointMessage += ELEMENT_CUT;
		endpointMessage += message.getElement();
		endpointMessage += ELEMENT_CUT;
		endpointMessage += message.getFunction();
		endpointMessage += ELEMENT_CUT;
		endpointMessage += message.getInstance();
		endpointMessage += ELEMENT_CUT;
		endpointMessage += message.getParameter();
		endpointMessage += MESSAGE_CUT;
		
		return endpointMessage;
	}
}

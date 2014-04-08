package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;

public abstract class MessageTranslatorBase {

	private static final String ELEMENT_CUT = ";";
	private static final String MESSAGE_CUT = "#";
	private static final int MESSAGE_ELEMENTS = 6;
	
	/**
	 * Array Positions
	 */
	private static final int ID = 0;
	private static final int EXTERN_INTERN = 1;
	private static final int ELEMENT = 2;
	private static final int FUNCTION = 3;
	private static final int INSTANCE= 4;
	private static final int PARAMETER = 5;
	
	
	/**
	 * encodes a message to fit the system message,
	 * for broker message implementation
	 * 
	 * @param message
	 * @return {@link MessageBase}
	 */
	protected MessageCommon decodeMessage(String message){
		
		MessageCommon messageCommon = null;
		
		try {
			
			if(message == null){
				throw new Exception("there are no messages to translate");
			}
		
			String[] messages = message.split(MESSAGE_CUT + "+");
		
			if(messages.length > 1){
				throw new Exception("transmit more then one message is not yet supported.");
			}
		
			String[][] commandList = new String[messages.length][MESSAGE_ELEMENTS];
			
			for(int i=0; i<messages.length; i++){
				String[] command = message.split(ELEMENT_CUT + "+");
				
				for(int j=0; j<command.length; j++){
					commandList[i][j] = command[j];
					}
			}
			
			messageCommon = new MessageCommon(commandList[0][ID], commandList[0][ELEMENT], commandList[0][FUNCTION], commandList[0][INSTANCE], commandList[0][PARAMETER], commandList[0][EXTERN_INTERN], message);
		
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
	protected String encodeMessage(MessageCommon message) {

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

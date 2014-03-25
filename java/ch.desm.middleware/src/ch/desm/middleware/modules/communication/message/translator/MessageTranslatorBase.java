package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;

public abstract class MessageTranslatorBase {

	private static final String ELEMENT_CUT = ";";
	private static final String MESSAGE_CUT = "#";
	private static final int MESSAGE_ELEMENTS = 7;
	
	/**
	 * encodes a message to fit the system message,
	 * for broker message implementation
	 * 
	 * @param message
	 * @return {@link MessageBase}
	 */
	protected MessageCommon encodeMessage(String message){
		
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
				
				for(int j=0; j<command.length;j++){
					commandList[i][j] = command[j];
					}
			}
			
			messageCommon = new MessageCommon(commandList[0][0], commandList[0][1], commandList[0][2], commandList[0][3], commandList[0][4], commandList[0][5], message);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messageCommon;
	}
}

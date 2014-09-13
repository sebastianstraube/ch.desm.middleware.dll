package ch.desm.middleware.communication.utility;

import ch.desm.middleware.communication.message.MessageCommon;

public class UtilityMessage {

	public String replaceMessageParameter(String stream, String replaceChar){
	
		return stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, replaceChar);
	}
		
}

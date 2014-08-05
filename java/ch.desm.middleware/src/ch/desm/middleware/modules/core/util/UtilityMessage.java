package ch.desm.middleware.modules.core.util;

import ch.desm.middleware.modules.communication.message.MessageCommon;

public class UtilityMessage {

	public String replaceMessageParameter(String stream, String replaceChar){
	
		return stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, replaceChar);
	}
		
}

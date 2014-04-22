package ch.desm.middleware.modules.communication.message.type;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageDll extends MessageBase {

	public static final String DLL_MESSAGE_TRAINPOSITION = "locsim.dll.trainposition;";
	public static final String DLL_MESSAGE_SIGNAL = "locsim.dll.signal;";
	public static final String DLL_MESSAGE_WEICHE = "locsim.dll.weiche;";
	
	public MessageDll(String payload, EnumMessageTopic topic) {
		super(payload, topic);
		// TODO Auto-generated constructor stub

	}



	
	
}

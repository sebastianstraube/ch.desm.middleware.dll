package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.message.type.MessageUbw32;
import ch.desm.middleware.modules.component.ComponentMessageStore;

public class ObermattLangnauMessageStore extends ComponentMessageStore {

	private MessageUbw32 lastMessage;
	
	public ObermattLangnauMessageStore(){
		
	}
	
	public void setLastMessage(MessageUbw32 lastMessage){
		this.lastMessage = lastMessage;
	}
	
	public MessageUbw32 getLastMessage(){
		return this.lastMessage;
	}
}

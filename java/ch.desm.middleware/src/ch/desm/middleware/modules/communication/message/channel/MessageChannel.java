package ch.desm.middleware.modules.communication.message.channel;

import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.component.ComponentBase;

/**
 * TODO refactoring and implementation
 * 
 * @author Sebastian
 *
 */
public abstract class MessageChannel {

    private ComponentBase sourceComponent;
	private MessageBase message;
	
	public MessageChannel(ComponentBase sourceComponent, MessageBase message){
		this.sourceComponent = sourceComponent;
		this.message = message;
	}
	
	
}

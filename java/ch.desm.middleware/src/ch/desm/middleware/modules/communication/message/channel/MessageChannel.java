package ch.desm.middleware.modules.communication.message.channel;

import ch.desm.middleware.modules.communication.message.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase;

/**
 * TODO refactoring and implementation
 * 
 * @author Sebastian
 *
 */
public class MessageChannel {

    private ComponentBase sourceComponent;
	private MessageCommon message;
	
	public MessageChannel(ComponentBase sourceComponent, MessageCommon message){
		this.sourceComponent = sourceComponent;
		this.message = message;
	}
	
	
}

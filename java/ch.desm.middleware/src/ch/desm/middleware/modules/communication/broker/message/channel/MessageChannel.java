package ch.desm.middleware.modules.communication.broker.message.channel;

import ch.desm.middleware.modules.communication.broker.message.MessageCommon;
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

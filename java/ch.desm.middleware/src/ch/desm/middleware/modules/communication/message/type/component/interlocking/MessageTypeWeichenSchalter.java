package ch.desm.middleware.modules.communication.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageTypeWeichenSchalter extends MessageBase{

    public MessageTypeWeichenSchalter(String payload) {
    	super(payload, EnumMessageTopic.INTERLOCKING);
    }
}
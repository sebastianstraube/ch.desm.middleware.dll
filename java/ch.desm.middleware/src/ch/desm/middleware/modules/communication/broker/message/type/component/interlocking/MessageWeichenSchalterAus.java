package ch.desm.middleware.modules.communication.broker.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.broker.message.MessageCommon;

public class MessageWeichenSchalterAus extends MessageCommon{

    public MessageWeichenSchalterAus(EnumMessageType messageType, int parameterTypeId) {
    	super(messageType, parameterTypeId);
    }
}
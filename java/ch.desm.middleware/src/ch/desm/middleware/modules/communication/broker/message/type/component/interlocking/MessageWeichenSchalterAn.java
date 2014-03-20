package ch.desm.middleware.modules.communication.broker.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.broker.message.MessageCommon;

public class MessageWeichenSchalterAn extends MessageCommon{

    public MessageWeichenSchalterAn(EnumMessageType messageType, int parameterTypeId) {
    	super(messageType, parameterTypeId);
    }
}
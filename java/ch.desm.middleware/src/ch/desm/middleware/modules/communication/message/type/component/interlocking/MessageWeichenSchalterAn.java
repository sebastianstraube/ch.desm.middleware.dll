package ch.desm.middleware.modules.communication.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.message.MessageCommon;

public class MessageWeichenSchalterAn extends MessageCommon{

    public MessageWeichenSchalterAn(EnumMessageType messageType, int parameterTypeId, String value) {
    	super(messageType, parameterTypeId, value);
    }
}
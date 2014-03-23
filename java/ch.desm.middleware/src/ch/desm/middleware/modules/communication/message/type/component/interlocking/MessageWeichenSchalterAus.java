package ch.desm.middleware.modules.communication.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.message.MessageCommon;

public class MessageWeichenSchalterAus extends MessageCommon{

    public MessageWeichenSchalterAus(EnumMessageType messageType, int parameterTypeId, String value) {
    	super(messageType, parameterTypeId, value);
    }
}
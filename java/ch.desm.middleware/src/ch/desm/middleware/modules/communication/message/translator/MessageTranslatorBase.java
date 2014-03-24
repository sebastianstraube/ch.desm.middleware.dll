package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.message.MessageBroker;
import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;

public abstract class MessageTranslatorBase {

protected MessageBroker encodeMessage(String message){
		
		String[] list = message.split("*.");
		
		System.out.println(list);
		
		return null;
	}
	
	protected abstract String decodeMessage(EnumComponentType targetEndpoint, MessageBroker message);
}

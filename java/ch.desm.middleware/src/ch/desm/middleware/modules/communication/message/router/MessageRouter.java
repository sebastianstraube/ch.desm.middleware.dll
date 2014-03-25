package ch.desm.middleware.modules.communication.message.router;

import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauEndpointUbw32;

/**
 * 
 * @author Sebastian
 *
 */
public class MessageRouter {

	private MessageTranslator translator;
	
	public MessageRouter(){
		this.translator = new MessageTranslator();
	}
	
	public void transmitMessage(ObermattLangnauEndpointUbw32 endpoint, MessageCommon messageBase){

		//TODO check relevanz to Endpoint then transmit thru endpoint
		String message = translator.translateToEndpointMessage(EnumComponentType.INTERLOCKING_OBERMATTLANGNAU, messageBase);
		
		
		//transmit to relevant function
		endpoint.setHaupthahn(message);
	}
	
	public void transmitMessage(Re420EndpointUbw32 endpoint, MessageCommon messageBase){

		//TODO check relevance to Endpoint then transmit thru endpoint
		String message = translator.translateToEndpointMessage(EnumComponentType.INTERLOCKING_OBERMATTLANGNAU, messageBase);
		
		
		//transmit to relevant function
		endpoint.setHaupthahn(message);
	}
	
	
	
}

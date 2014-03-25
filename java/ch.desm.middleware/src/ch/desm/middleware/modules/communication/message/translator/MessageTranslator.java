package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;

/**
 * TODO implementation
 * 
 * @author Sebastian
 *
 */
public class MessageTranslator extends MessageTranslatorBase {

	
	public MessageTranslator(){
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param fromEndpoint
	 * @param message
	 * 
	 */
	public MessageBase translateToBrokerMessage(String message){
		return encodeMessage(message);
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param fromEndpoint
	 * @param message
	 * 
	 */
	public String translateToEndpointMessage(EnumComponentType targetEndpointType, MessageCommon message){
		
		String endpointMessage = decodeMessage(targetEndpointType, message);
		
		return endpointMessage;

	}

	/**
	 * TODO implementation
	 * decode the message to the endpoint code
	 * 
	 * @param
	 * @param
	 */
	protected String decodeMessage(EnumComponentType targetEndpointType,
			MessageCommon message) {

		String endpointMessage = "";
		
		if(targetEndpointType.equals(EnumComponentType.CABINE_RE420)){
			
			endpointMessage = "cabine.re420.";
			endpointMessage += message.getElement();
			endpointMessage += message.getInstance();
			endpointMessage += message.getParameter();
			
			
		}else if(targetEndpointType.equals(EnumComponentType.INTERLOCKING_OBERMATTLANGNAU)){
			
		}else if(targetEndpointType.equals(EnumComponentType.SIMULATION_LOCSIM)){
			
		}else{
			System.err.println("decode for endpoint not implemented" + this.getClass());
		}
		
		return endpointMessage;
	}
}

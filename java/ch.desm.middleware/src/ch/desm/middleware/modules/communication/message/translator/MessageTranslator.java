package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.message.MessageBroker;
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
	public MessageBroker translateToBroker(String message){
		
		MessageBroker temp = new MessageBroker(message);
		
		return null;
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param fromEndpoint
	 * @param message
	 * 
	 */
	public String translateTo(EnumComponentType targetEndpointType, MessageBroker message){
		
		String endpointMessage = decodeMessage(targetEndpointType, message);

		if(targetEndpointType.equals(EnumComponentType.CabineRe420)){
			
			
			
		}else if(targetEndpointType.equals(EnumComponentType.InterlockingObermattLangnau)){
			
		}else if(targetEndpointType.equals(EnumComponentType.SimulationLocsim)){
			
		}
		
		return null;

	}

	@Override
	protected String decodeMessage(EnumComponentType targetEndpoint,
			MessageBroker message) {

		if(targetEndpoint.equals(EnumComponentType.CabineRe420)){
			
		}else{
			System.err.println("decode not implemented" + this.getClass());
		}
		return null;
	}
}

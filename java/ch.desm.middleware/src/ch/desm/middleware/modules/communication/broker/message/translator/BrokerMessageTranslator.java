package ch.desm.middleware.modules.communication.broker.message.translator;

import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.component.ComponentBase;
import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;

/**
 * TODO implementation
 * 
 * @author Sebastian
 *
 */
public class BrokerMessageTranslator {

	BrokerMessageCommon message;
	
	public BrokerMessageTranslator(BrokerMessageCommon message){
		this.message = message;
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param messageSource
	 * @param messageDestination
	 */
	public void translateMessage(EnumComponentType messageSource, EnumComponentType messageDestination, BrokerMessageCommon message){
		
		if(messageSource.equals(EnumComponentType.CABINE)){
			
		}else if(messageSource.equals(EnumComponentType.INTERLOCKING)){
		
		}else if(messageSource.equals(EnumComponentType.SIMULATION)){
			
		}else{
			System.err.println("message source unknown in " + this.getClass().getCanonicalName());
		}
	}
	
	/**
	 * TODO implementation
	 * 
	 * @param component
	 * @return
	 */
	private BrokerMessageCommon translateToComponentMessage(ComponentBase component){
		
		
		return null;
	}
	
	/**
	 * TODO implementation
	 * 
	 * @param component
	 * @return
	 */
	private String translateToEndpointMessage(EndpointBase component){
		
		
		return null;
	}
	
}

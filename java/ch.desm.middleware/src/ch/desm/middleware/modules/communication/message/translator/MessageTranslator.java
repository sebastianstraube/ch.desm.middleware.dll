package ch.desm.middleware.modules.communication.message.translator;

import ch.desm.middleware.modules.communication.endpoint.EndpointBase.EnumEndpointType;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.communication.message.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.component.cabine.Stufenschalter1;
import ch.desm.middleware.modules.component.simulation.LocsimBase.EnumFunctionTypeLocsim;
import ch.desm.middleware.modules.component.simulation.LocsimEndpointRs232;

/**
 * TODO implementation
 * 
 * @author Sebastian
 *
 */
public class MessageTranslator {

	
	public MessageTranslator(){
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param fromEndpoint
	 * @param message
	 * 
	 */
	public MessageCommon translate(EnumEndpointType fromEndpoint, String message){
		
		if(fromEndpoint.equals(EnumEndpointType.SERIAL)){
			
			
			
		}else if(fromEndpoint.equals(EnumEndpointType.UBW32)){
		
		}else if(fromEndpoint.equals(EnumEndpointType.TCPIP)){
			
		}else if(fromEndpoint.equals(EnumEndpointType.CORBA)){
			
		}else if(fromEndpoint.equals(EnumEndpointType.VIRTUAL)){
			
		}else{
			try {
				throw new Exception("message endpoint unknown");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param fromEndpoint
	 * @param message
	 * 
	 */
	public void translate(LocsimEndpointRs232 endpoint, EnumFunctionTypeLocsim functionType, String message){
		
//		if(functionType.equals(EnumFunctionTypeLocsim.STUFENSCHALTER)){
//			endpoint.onStufenschalter(1, "translated to locsim rs232");
//		}
	}
	
	/**
	 * TODO Implementation
	 * 
	 * @param fromEndpoint
	 * @param message
	 * 
	 */
	public MessageCommon translate(EnumEndpointType fromEndpoint, MessageCommon message){
		
		if(fromEndpoint.equals(EnumEndpointType.SERIAL)){
						
		}else if(fromEndpoint.equals(EnumEndpointType.UBW32)){
		
			if(message instanceof Stufenschalter1) {
				Stufenschalter1 stufenSchalter = (Stufenschalter1)message;
				System.out.println(this.getClass().getCanonicalName() + ": stufenschalter an " + stufenSchalter.getIdParameterType());
				
				return stufenSchalter;
	            
	        }
			
		}else if(fromEndpoint.equals(EnumEndpointType.TCPIP)){
			
		}else if(fromEndpoint.equals(EnumEndpointType.CORBA)){
			
		}else if(fromEndpoint.equals(EnumEndpointType.VIRTUAL)){
			
		}else{
			try {
				throw new Exception("message endpoint unknown");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}	
	
	/**
	 * TODO Implementation, CORBA, VIRTUAL, TCPIP
	 * 
	 */
	public MessageCommon translate(EndpointCommon fromEndpoint, MessageCommon message){
		
		if(fromEndpoint instanceof EndpointUbw32){
			
		}else{
			System.err.println("The message endpoint is not configured, the message will not be processed." + message);
		}
		
		return message;
	}	
}

package ch.desm.middleware.modules.communication.message.router;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase;
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

	
	public void processBrokerMessage(Re420EndpointUbw32 endpoint, MessageCommon messageBase){

		System.out.println("processing message:" + messageBase);
		
		if(endpoint.getConfiguration().isKeyAvailable(messageBase.getGlobalId())){
			String register = endpoint.getConfiguration().map.get(messageBase.getGlobalId());
			String port = register.substring(0, 1);
			String pin = register.substring(1, register.length());
			String value = messageBase.getParameter();
			boolean isReturnPacket = messageBase.isReturnMessage();
			
			switch(messageBase.getGlobalId()){
				case("1.90.01"):{};
				case("1.90.02"):{};
				case("1.90.04"):{};
				case("1.90.05"):{};
				case("1.90.06"):{};
				case("1.90.07"):{};
				case("1.90.08"):{};
				case("1.90.09"):{};
				case("1.90.10"):{};
				case("1.90.11"):{};
				case("1.90.12"):{};
				case("1.90.13"):{};
				case("1.90.14"):{};
				case("1.90.15"):{};
				case("1.90.16"):{};
				case("1.91.21"):{};
				case("1.91.22"):{};
				case("1.90.31"):{//BLOCK VON LANGNAU
					if(isReturnPacket){
						endpoint.setHaupthahn(port, pin, value);
					}else{
						endpoint.getHaupthahn(port, pin);
					}
					
					
				}
				case("1.90.32"):{};
				case("1.90.33"):{};
				case("1.90.34"):{};
				case("1.90.35"):{};
				case("1.90.36"):{};
				case("1.90.37"):{};
				case("1.90.38"):{};
				case("1.90.39"):{};
				case("1.90.40"):{};
				case("1.90.41"):{};
				case("1.90.42"):{};
				case("1.90.43"):{};
				case("1.90.44"):{};
				case("1.91.01"):{};
				case("1.91.02"):{};
				case("1.91.03"):{};
				case("1.01.01"):{};
				case("1.01.02"):{};
				case("1.04.01"):{};
				case("1.04.02"):{};
				case("2.92.01"):{};
				case("2.92.02"):{};
				case("2.90.01"):{};
				case("9.99.04"):{};
				case("9.99.05"):{};
				case("9.99.06"):{};
				case("9.99.07"):{};
				case("9.99.08"):{};
				case("9.99.09"):{};
				case("9.99.10"):{};
				case("9.99.11"):{};
				case("9.99.12"):{};
				case("3.01.01"):{};
				case("3.04.01"):{};
				case("3.04.02"):{};
				case("4.01.01"):{};
				case("5.01.01"):{};
				case("5.04.01"):{};
				case("9.93.01"):{};
				case("9.93.02"):{};
				case("6.90.01"):{};
				case("6.91.02"):{};
				case("6.91.01"):{};
				case("6.91.03"):{};
				case("6.91.04"):{};
				case("6.91.05"):{};
				case("6.91.06"):{};
				case("6.91.07"):{};
				case("6.91.08"):{};
				case("6.91.09"):{};
				case("6.91.10"):{};
				case("6.91.11"):{};
				case("6.91.12"):{};
				case("6.91.13"):{};
				case("6.91.14"):{};
				case("6.91.15"):{};
				case("7.91.01"):{};
				case("7.91.02"):{};
				case("7.91.03"):{};
				case("7.91.04"):{};
				case("7.91.06"):{};
				case("7.91.07"):{};
				case("8.91.01"):{};
				case("8.91.02"):{};
				case("8.91.03"):{};
				case("8.91.04"):{};
				case("8.91.05"):{};
				case("8.91.06"):{};
				case("8.91.07"):{};
				case("8.91.08"):{};
				case("8.91.09"):{};
				case("8.91.10"):{};
				case("8.91.11"):{};
				case("8.91.12"):{};
				case("8.91.13"):{};
				case("8.91.14"):{};
				case("8.91.15"):{};
				case("8.91.16"):{};
				case("8.91.17"):{};
				case("8.91.18"):{};
				case("8.91.19"):{};
				case("8.91.20"):{};
				case("8.91.21"):{};
				case("8.91.22"):{};
				case("8.91.23"):{};
				case("8.91.24"):{};
				case("8.91.25"):{};
				case("8.91.26"):{};
				case("8.91.27"):{};
				case("8.91.28"):{};
				case("8.91.29"):{};
				case("8.91.30"):{};
				case("8.91.31"):{};
				case("8.91.32"):{};
				case("8.91.33"):{};
				case("8.91.34"):{};
				case("10.99.01"):{};
				case("10.99.02"):{};
				case("10.99.03"):{};
				case("10.99.04"):{};
				case("10.99.05"):{};
				case("10.99.06"):{};
				case("10.99.07"):{};
				case("10.99.08"):{};
				case("10.99.09"):{};
				case("10.99.10"):{};
				case("11.99.01"):{};
				case("11.99.02"):{};
				case("11.99.03"):{};
				case("11.99.04"):{};
				case("11.99.05"):{};
				case("11.99.06"):{};
				case("11.99.07"):{};
				case("11.99.08"):{};
				case("9.99.13"):{};
				case("9.99.14"):{};
				case("9.99.15"):{};
				case("11.99.09"):{};
				case("11.99.10"):{};
				case("9.99.01"):{};
				case("9.99.02"):{};
				case("9.99.03"):{};
				case("6.99.01"):{};
				case("6.99.02"):{};
				case("6.99.03"):{};
				case("12.99.01"):{};
				case("12.99.02"):{};
				case("12.99.03"):{};
				case("12.99.04"):{};
				case("90.99.01"):{};
				case("90.99.02"):{};
				case("90.99.03"):{};
				case("90.99.04"):{};
				case("90.99.05"):{};
				case("90.99.06"):{};
				case("90.99.07"):{};
				case("90.99.08"):{};
				case("90.99.09"):{};
				case("90.99.10"):{};
				case("90.99.11"):{};
				case("90.99.12"):{};
				case("90.99.13"):{};
				case("90.99.14"):{};
				case("90.99.15"):{};
				case("90.99.16"):{};
				case("90.99.17"):{};
				case("90.99.18"):{};
				case("90.99.19"):{};
				default: System.err.println("global message ID not found in");
			}
			
		}
	}
	
	public void processBrokerMessage(ObermattLangnauEndpointUbw32 endpoint, MessageCommon messageBase){
		
		System.out.println("processing message:" + messageBase);
		
		if(endpoint.getConfiguration().isKeyAvailable(messageBase.getGlobalId())){
			String register = endpoint.getConfiguration().map.get(messageBase.getGlobalId());
			String port = register.substring(0, 1);
			String pin = register.substring(1, register.length());
			String value = messageBase.getParameter();
			boolean isReturnPacket = messageBase.isReturnMessage();
			
			switch(messageBase.getGlobalId()){
			case("1.90.01"):{};
			case("1.90.02"):{};
			case("1.90.04"):{};
			case("1.90.05"):{};
			case("1.90.06"):{};
			case("1.90.07"):{};
			case("1.90.08"):{};
			case("1.90.09"):{};
			case("1.90.10"):{};
			case("1.90.11"):{};
			case("1.90.12"):{};
			case("1.90.13"):{};
			case("1.90.14"):{};
			case("1.90.15"):{};
			case("1.90.16"):{};
			case("1.91.21"):{};
			case("1.91.22"):{};
			case("1.90.31"):{//BLOCK VON LANGNAU
				if(isReturnPacket){
					endpoint.setBlockVonLangnau(port, pin, value);
				}else{
					endpoint.getBlockVonLangnau(port, pin);
				}
			}
			case("1.90.32"):{};
			case("1.90.33"):{};
			case("1.90.34"):{};
			case("1.90.35"):{};
			case("1.90.36"):{};
			case("1.90.37"):{};
			case("1.90.38"):{};
			case("1.90.39"):{};
			case("1.90.40"):{};
			case("1.90.41"):{};
			case("1.90.42"):{};
			case("1.90.43"):{};
			case("1.90.44"):{};
			case("1.91.01"):{};
			case("1.91.02"):{};
			case("1.91.03"):{};
			case("1.01.01"):{};
			case("1.01.02"):{};
			case("1.04.01"):{};
			case("1.04.02"):{};
			case("2.92.01"):{};
			case("2.92.02"):{};
			case("2.90.01"):{};
			case("9.99.04"):{};
			case("9.99.05"):{};
			case("9.99.06"):{};
			case("9.99.07"):{};
			case("9.99.08"):{};
			case("9.99.09"):{};
			case("9.99.10"):{};
			case("9.99.11"):{};
			case("9.99.12"):{};
			case("3.01.01"):{};
			case("3.04.01"):{};
			case("3.04.02"):{};
			case("4.01.01"):{};
			case("5.01.01"):{};
			case("5.04.01"):{};
			case("9.93.01"):{};
			case("9.93.02"):{};
			case("6.90.01"):{};
			case("6.91.02"):{};
			case("6.91.01"):{};
			case("6.91.03"):{};
			case("6.91.04"):{};
			case("6.91.05"):{};
			case("6.91.06"):{};
			case("6.91.07"):{};
			case("6.91.08"):{};
			case("6.91.09"):{};
			case("6.91.10"):{};
			case("6.91.11"):{};
			case("6.91.12"):{};
			case("6.91.13"):{};
			case("6.91.14"):{};
			case("6.91.15"):{};
			case("7.91.01"):{};
			case("7.91.02"):{};
			case("7.91.03"):{};
			case("7.91.04"):{};
			case("7.91.06"):{};
			case("7.91.07"):{};
			case("8.91.01"):{};
			case("8.91.02"):{};
			case("8.91.03"):{};
			case("8.91.04"):{};
			case("8.91.05"):{};
			case("8.91.06"):{};
			case("8.91.07"):{};
			case("8.91.08"):{};
			case("8.91.09"):{};
			case("8.91.10"):{};
			case("8.91.11"):{};
			case("8.91.12"):{};
			case("8.91.13"):{};
			case("8.91.14"):{};
			case("8.91.15"):{};
			case("8.91.16"):{};
			case("8.91.17"):{};
			case("8.91.18"):{};
			case("8.91.19"):{};
			case("8.91.20"):{};
			case("8.91.21"):{};
			case("8.91.22"):{};
			case("8.91.23"):{};
			case("8.91.24"):{};
			case("8.91.25"):{};
			case("8.91.26"):{};
			case("8.91.27"):{};
			case("8.91.28"):{};
			case("8.91.29"):{};
			case("8.91.30"):{};
			case("8.91.31"):{};
			case("8.91.32"):{};
			case("8.91.33"):{};
			case("8.91.34"):{};
			case("10.99.01"):{};
			case("10.99.02"):{};
			case("10.99.03"):{};
			case("10.99.04"):{};
			case("10.99.05"):{};
			case("10.99.06"):{};
			case("10.99.07"):{};
			case("10.99.08"):{};
			case("10.99.09"):{};
			case("10.99.10"):{};
			case("11.99.01"):{};
			case("11.99.02"):{};
			case("11.99.03"):{};
			case("11.99.04"):{};
			case("11.99.05"):{};
			case("11.99.06"):{};
			case("11.99.07"):{};
			case("11.99.08"):{};
			case("9.99.13"):{};
			case("9.99.14"):{};
			case("9.99.15"):{};
			case("11.99.09"):{};
			case("11.99.10"):{};
			case("9.99.01"):{};
			case("9.99.02"):{};
			case("9.99.03"):{};
			case("6.99.01"):{};
			case("6.99.02"):{};
			case("6.99.03"):{};
			case("12.99.01"):{};
			case("12.99.02"):{};
			case("12.99.03"):{};
			case("12.99.04"):{};
			case("90.99.01"):{};
			case("90.99.02"):{};
			case("90.99.03"):{};
			case("90.99.04"):{};
			case("90.99.05"):{};
			case("90.99.06"):{};
			case("90.99.07"):{};
			case("90.99.08"):{};
			case("90.99.09"):{};
			case("90.99.10"):{};
			case("90.99.11"):{};
			case("90.99.12"):{};
			case("90.99.13"):{};
			case("90.99.14"):{};
			case("90.99.15"):{};
			case("90.99.16"):{};
			case("90.99.17"):{};
			case("90.99.18"):{};
			case("90.99.19"):{};
			default: System.err.println("global message ID not found in");
			}
		}
	}
	
	/**
	 * 
	 * TODO Implementation
	 * 
	 * @param component
	 * @param message
	 */
	public void processEndpointMessage(ComponentBase component, String message){
		
		//message = PI
		MessageCommon commonMessage = translator.translateCommonMessageToMessageObject(message);
		
		//TODO
		if(isReturnMessage(message)){
			System.out.println("received a return packet:" + message);
			commonMessage.setReturnMessage(true);
		}
		
		//publish to broker clients
		component.publish(commonMessage);
	}
	
	/**
	 * 
	 * @param message
	 * @return true if the ubw32 returns a state package
	 */
	private boolean isReturnMessage(String message){
		boolean isReturnPacket = false;
		
		if(message.startsWith(EndpointUbw32.RETURN_INPUT_ANALOG)){
			isReturnPacket = true;
		} else if(message.startsWith(EndpointUbw32.RETURN_INPUT_STATE)){
			isReturnPacket = true;
		} else if(message.startsWith(EndpointUbw32.RETURN_OK)){
			isReturnPacket = true;
		} else if(message.startsWith(EndpointUbw32.RETURN_PIN_INPUT)){
			isReturnPacket = true;
		}
		
		return isReturnPacket;
	}	
}

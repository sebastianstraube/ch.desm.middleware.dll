package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.component.cabine.maps.Re420MapAnalog;
import ch.desm.middleware.modules.component.cabine.maps.Re420MapDigital;

public class Re420EndpointUbw32 extends EndpointUbw32 {

	public static final String PINBITMASK_CONFIGURATION_DIGITAL = "17943,65339,16,49152,768,12596,960";

	protected Re420MapDigital re420MapDigital;
	protected Re420MapAnalog re420MapAnalog;
	
	public Re420EndpointUbw32(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort, PINBITMASK_CONFIGURATION_DIGITAL, Re420MapAnalog.PINBITMASK_INPUT_ANALOG);
		
		re420MapDigital = new Re420MapDigital();
		re420MapAnalog = new Re420MapAnalog();
	}
	
	public Re420MapAnalog getMapAnalog(){
		return re420MapAnalog;
	}
	
	public Re420MapDigital getMapDigital(){
		return re420MapDigital;
	}
	
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}
	

}

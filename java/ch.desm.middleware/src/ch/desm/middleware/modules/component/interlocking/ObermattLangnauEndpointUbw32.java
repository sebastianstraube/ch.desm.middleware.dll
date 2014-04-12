package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class ObermattLangnauEndpointUbw32 extends EndpointUbw32 implements ObermattLangnauListenerUbw32  {
	
	protected ObermattLangnauConfiguration configuration;	
	
	public ObermattLangnauEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort, ObermattLangnauConfiguration.PINBITMASK_CONFIGURATION_DIGITAL, ObermattLangnauConfiguration.PINBITMASK_INPUT_ANALOG);
		
		this.configuration = new ObermattLangnauConfiguration();
	}
	
	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void setBlockVonLangnau(String port, String pin, String value){
		System.out.println("transmit setBlockVonLangnau: " + this.getClass());	
		this.sendCommandPinOutput(port, pin, value);
	};
	

	/**
	 * read the BlockVonLangnau pin
	 * @param message
	 */
	public void getBlockVonLangnau(String port, String pin) {
		System.out.println("transmit getBlockVonLangnau to : " + this.getClass());
		
		this.sendCommandPinInput(port, pin);
	}
	
	public ObermattLangnauConfiguration getConfiguration(){
		return this.configuration;
	}

	@Override
	/**
	 * TODO implementation / refactoring
	 */
	public void onBlockVonLangnau(String payload) {
		// TODO Auto-generated method stub
		System.out.println("entering onBlockVonLangnau...");
		
	}
}

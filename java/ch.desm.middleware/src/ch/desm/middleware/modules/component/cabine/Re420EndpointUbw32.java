package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class Re420EndpointUbw32 extends EndpointUbw32 implements Re420ListenerUbw32{
	
	protected Re420EndpointConfiguration configuration;
	
	public Re420EndpointUbw32(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort, Re420EndpointConfiguration.CONFIGURATION);
		
		configuration = new Re420EndpointConfiguration();
	}
	
	public Re420EndpointConfiguration getConfiguration(){
		return this.configuration;
	}
		
	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void setHaupthahn(String port, String pin, String value){
		
		System.out.println("transmit setHaupthahn: " + this.getClass());	
		this.sendCommandPinOutput(port, pin, value);
	}
	
	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void getHaupthahn(String port, String pin){
		
		System.out.println("transmit getHaupthahn: " + this.getClass());	
		this.sendCommandPinInput(port, pin);
	}
	


	@Override
	/**
	 * TODO implementation
	 */
	public void onHaupthahn(String payload) {
		System.out.println("processing onHaupthahn: " + this.getClass());	
		
	}
}

package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class Re420Ubw32 extends EndpointUbw32 implements Re420ListenerUbw32{
	
	protected Re420Ubw32Configuration configuration;
	
	public Re420Ubw32(EnumSerialPorts enumSerialPort) {		
		super(enumSerialPort, Re420Ubw32Configuration.CONFIGURATION, "");
		
		configuration = new Re420Ubw32Configuration();
	}
	
	public Re420Ubw32Configuration getConfiguration(){
		return this.configuration;
	}
		
	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void setHauptschalter(String port, String pin, String value){
		
		System.out.println("transmit setHauptschalter: " + this.getClass());	
		this.sendCommandPinOutput(port, pin, value);
	}

	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void getHauptschalter(String port, String pin){
		
		System.out.println("transmit getHauptschalter: " + this.getClass());	
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

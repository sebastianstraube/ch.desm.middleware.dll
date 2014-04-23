package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;


public class EndpointUbw32PortAnalog {

	private static final int MAX_DISCRET_VALUE = 1023;
	
	private String value;
	private EnumEndpointUbw32PortAnalog port;
	
	public enum EnumEndpointUbw32PortAnalog{
		AN0, AN1, AN2, AN3, AN4, AN5, AN6, AN7, AN8, AN9, AN10, AN11, AN12, AN13, AN14, AN15
	}
		
	public enum EnumEndpointUbw32RegisterAnalog{
		AN0, AN1, AN2, AN3, AN4, AN5, AN6, AN7, AN8, AN9, AN10, AN11, AN12, AN13, AN14, AN15
	}

	public EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog port, String value){		
		this.port = port;
		this.value = value;
	}
	
	public void setPort(EnumEndpointUbw32PortAnalog name){
		this.port = name;
	}
	
	public EnumEndpointUbw32PortAnalog getPort(){
		return port;
	}
	
	public void setValue(String value){
		if(!isValueOk(Integer.parseInt(value))){
			try {
				throw new Exception("The analog input has a wrong value.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

	private boolean isValueOk(int value){
		if(value < 0 || value > MAX_DISCRET_VALUE) return false;
		
		return true;
	}
}

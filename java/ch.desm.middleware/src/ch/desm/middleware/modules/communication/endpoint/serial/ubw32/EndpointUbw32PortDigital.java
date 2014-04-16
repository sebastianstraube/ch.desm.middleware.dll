package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;


public class EndpointUbw32PortDigital {

	private static final int ALL_PINS_ON = 65535;
	
	/**
	 * 0 = output
	 * 1 = input
	 * 
	 * Pin-Mask
	 * default = 65535 = FF FF = 1111 1111  1111 1111 > all pins are configured for output
	 */
	private int pinBitMask;
	private EnumEndpointUbw32PortDigital port;

	public enum EnumEndpointUbw32PortDigital{
		A, B, C, D, E, F, G
	}
		
	public enum EnumEndpointUbw32RegisterDigital{
		A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15,
		B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15,
		C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15,
		D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15,
		E0, E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15,
		F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15,
		G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15
	}
	
	public EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital port, String pinMask){
		this.port = port;
		this.pinBitMask = Integer.parseInt(pinMask);
	}
	
	public void setPort(EnumEndpointUbw32PortDigital name){
		this.port = name;
	}
	
	public EnumEndpointUbw32PortDigital getPort(){
		return port;
	}
	
	public void setPins(int mask){
		if(!isPinMaskOk(mask)){
			try {
				throw new Exception("The pin mask has a wrong value.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pinBitMask = mask;
	}
	
	public int getPinBitMask(){
		return pinBitMask;
	}

	private boolean isPinMaskOk(int mask){
		if(mask < 0 || mask > ALL_PINS_ON) return false;
		
		return true;
	}
}

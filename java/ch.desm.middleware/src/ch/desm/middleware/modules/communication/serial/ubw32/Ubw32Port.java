package ch.desm.middleware.modules.communication.serial.ubw32;

class Ubw32Port {

	private static final int ALL_PINS_INPUT = 65535;
	private static final int ALL_PINS_OUTPUT = 0;
	
	public Ubw32Port(String name){
		pinBitMask = ALL_PINS_INPUT;
		this.name = name;
	}
	
	public Ubw32Port(String name, int pinMask){
		pinBitMask = pinMask;
		this.name = name;
	}
	
	/**
	 * 0 = output
	 * 1 = input
	 * 
	 * Pin-Mask
	 * default = 65535 = FF FF = 1111 1111  1111 1111 > all pins are configured for output
	 */
	private int pinBitMask;
	private String name;
	
	private boolean isPinMaskOk(int mask){
		if(mask < 0 || mask > 65535) return false;
		
		return true;
	}
	
	public void setAllPinsAsOutput(){
		pinBitMask = ALL_PINS_OUTPUT;
	}
	
	public void setAllPinsAsInput(){
		pinBitMask = ALL_PINS_INPUT;
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
	
	public int getPins(){
		return pinBitMask;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}

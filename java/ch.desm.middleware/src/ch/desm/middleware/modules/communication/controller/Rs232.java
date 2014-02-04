package ch.desm.middleware.modules.communication.controller;

import jssc.*;

public class Rs232 {

	private SerialPort[] serialPorts;
	
	public Rs232(){
		this.showPortNames();
	}
	
	/**
	 * Method getPortNames() returns an array of strings.
	 * Elements of the array is already sorted.
	 */
	public void showPortNames(){
    String[] portNames = SerialPortList.getPortNames();
    for(int i = 0; i < portNames.length; i++){
        System.out.println(portNames[i]);
    	}
	}
	
	public void setSerialPorts(String[] serialportNames){
		System.out.print("intialize ports...");
		
		serialPorts = new SerialPort[serialportNames.length];
		
		for(int i=0; i< serialportNames.length; i++){
			serialPorts[i] = new SerialPort(serialportNames[i]); 
	        try {
	        	System.out.print(serialPorts[i].getPortName() + " ");
	        	
	        	serialPorts[i].openPort();
	        	serialPorts[i].setParams(9600, 8, 1, 0);
	            //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
	            //Well, for example, we need to know what came some data, thus in the mask must have the
	            //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states 
	            //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
	            
	            //Set the prepared mask
	            serialPorts[i].setEventsMask(SerialPort.MASK_RXCHAR);
	            //Add an interface through which we will receive information about events
	            serialPorts[i].addEventListener(new EventListenerRS232(serialPorts[i]));
	        }
	        catch (SerialPortException e) {
	            System.err.println(e);
	        }
		}
		
		System.out.println("");
	}
	
	public SerialPort[] getSerialPorts(){
		return serialPorts;
	}
	
	public void disconnect(){
		System.out.print("disconnecting all open ports...");
		
		for(int i=0; i<serialPorts.length; i++){
			try {
				if(serialPorts[i].isOpened()){
					System.out.println(serialPorts[i].getPortName()+ " ");
					serialPorts[i].purgePort(1);
					serialPorts[i].purgePort(2);
					serialPorts[i].closePort();
				}
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
	}
	
	public void testSeriaPorts(){
		for(int i=0; i<serialPorts.length; i++){
			try {
				serialPorts[i].writeString("Write Test to Serialport ..."+serialPorts[i].getPortName());
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				 System.err.println(e);
			}
		}
	}
}

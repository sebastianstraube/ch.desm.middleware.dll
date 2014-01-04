package ch.desm.middleware.modules.communication.controller;

import jssc.*;

public class Rs232 {

	private SerialPort serialPort;
	private String serialportName;
	
	public Rs232(){
		this.serialportName = "COM7";
		this.showPortNames();
		this.initialize();
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
	
	private void initialize(){
		serialPort = new SerialPort(serialportName); 
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
            //Well, for example, we need to know what came some data, thus in the mask must have the
            //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states 
            //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
            int mask = SerialPort.MASK_RXCHAR;
            //Set the prepared mask
            serialPort.setEventsMask(mask);
            //Add an interface through which we will receive information about events
            serialPort.addEventListener(new SerialportReader());
        }
        catch (SerialPortException ex) {
            System.err.println(ex);
        }
	}
}

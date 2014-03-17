package ch.desm.middleware.modules.communication.endpoint.serial;

import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointListenerInterface;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public abstract class CommunicationEndpointRs232Listener implements CommunicationEndpointListenerInterface, SerialPortEventListener {

	private SerialPort serialPort;
	
	public CommunicationEndpointRs232Listener(){
		
	}
	
	//TODO - need refactoring
	public void setSerialPort(SerialPort serialPort){
		this.serialPort = serialPort;
	}
	
	public void serialEvent(SerialPortEvent event) {
        //Object type SerialPortEvent carries information about which event occurred and a value.
        //For example, if the data came a method event.getEventValue() returns us the number of bytes in the input buffer.
		
    	if(event.isRXCHAR()){
    		
    		System.out.println("Serial event listener receive data on port "+ serialPort.getPortName()+ ": ");
    		
            if(event.getEventValue() > 1){
                try {
                	byte buffer[]  = serialPort.readBytes();
                	String receivedCommand = "";
                	
                	for(int i=0; i<buffer.length; i++){
                		if( ((char)buffer[i]) != '\n' &&
                			((char)buffer[i]) != '\r'){
                			receivedCommand += (char)buffer[i];
                		}
                	}

                	//TODO
//                	System.out.println(receivedCommand + "\n");
//                	onSerialMessage(receivedCommand);
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
        //If the CTS line status has changed, then the method event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
        else if(event.isCTS()){
            if(event.getEventValue() == 1){
                System.out.println("CTS - ON");
            }
            else {
                System.out.println("CTS - OFF");
            }
        }
        else if(event.isDSR()){
            if(event.getEventValue() == 1){
                System.out.println("DSR - ON");
            }
            else {
                System.out.println("DSR - OFF");
                }
            }
        }
	
	public abstract void onSerialMessage(String message);
}

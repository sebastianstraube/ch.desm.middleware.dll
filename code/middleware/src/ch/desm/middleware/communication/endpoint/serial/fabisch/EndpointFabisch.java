package ch.desm.middleware.communication.endpoint.serial.fabisch;

import jssc.SerialPortEvent;
import jssc.SerialPortException;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.endpoint.serial.EndpointRs232;
import ch.desm.middleware.communication.endpoint.serial.EndpointRs232Config;
import ch.desm.middleware.communication.endpoint.serial.EndpointRs232ConfigBuilder;

/**
 * send buffer = 256
 * read buffer = 128 
 * @author Jürg
 *
 */
public class EndpointFabisch extends EndpointRs232 {

	private static Logger log = Logger.getLogger(EndpointFabisch.class);
	
	private EndpointFabischThread polling;
	
	/**
	 * 
	 * @param enumSerialPort
	 */
	public EndpointFabisch(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort, new EndpointRs232ConfigBuilder()
				.setBaudRate(EndpointRs232Config.BAUDRATE_9600)
				.setDataBits(EndpointRs232Config.DATABITS_8)
				.setStopBits(EndpointRs232Config.STOPBITS_1)
				.setParity(EndpointRs232Config.PARITY_NONE)
				.setEventMask(EndpointRs232Config.MASK_RXCHAR)
				.setFlowControl(EndpointRs232Config.FLOWCONTROL_NONE).build());

		this.polling = new EndpointFabischThread(this);
		this.initialize();
	}


	/**
	 * do work to initialze the controller on constructor call
	 */
	private void initialize() {
		polling.start();
		
		//ping
		this.sendStream("000101");
		
		//reset
		this.sendStream("F76500");
		
		//Systemuhr 
		this.sendStream("F61900"); // Stunden
		this.sendStream("F61A00"); // minuten
		this.sendStream("F61B00"); // sekunden
				
		//Countdown start
//	this.sendStream("F62205");
				
//		//modus Simulation
//		this.sendStream("F62D01");
		
//		//türen links on
//		this.sendStream("479102");
//		
//		//türen links off
//		this.sendStream("479101");
//
//		//magnet of m-taste anziehen
//		this.sendStream("47E102");
//		
//		//magnet of m-taste lösen
//		this.sendStream("47E101");
//
//		//lampe abfertigungsbefehl ein
//		this.sendStream("46B402");
//		
//		//lampe abfertigungsbefehl aus
//		this.sendStream("46B401");
//		
//		//summer hochton
//		this.sendStream("5D5C02");
//		
		//summer tiefton
		this.sendStream("5D5C03");
//	
		//summer aus
		this.sendStream("5D5C01");

	}

	/**
	 * 
	 */
	public void pollingCommand(){
		
	}

	@Override
	/**
	 * this listener receives a command from UBW32
	 * 
	 * @param SerialPortEvent event
	 */
	public synchronized void serialEvent(SerialPortEvent event) {
		String message = super.getSerialPortMessage(event);
		
		byte[] b = message.getBytes();
		
		String s = "";
		for(int i=0; i< b.length; i++){
			s += (char)b[i];
			
			if(i!=b.length-1){
				s += ",";
			}
		}
		
		log.trace("endpoint (" +this.getClass()+ ") received message : [" + message + "], bytes: ["+s+"]");
		
		message += "#fabisch#";
	}

	
	/**
	 * TODO refactoring sleep
	 */
	public void sendStream(byte val0, byte val1, byte val2) {
		try {
			
			Thread.sleep(12);
			byte[] byteArray = {val0, val1, val2, 0, 0};
			
			super.sendStream(byteArray);

		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}
	
	public void sendStream(int val0, int val1, int val2) {
		try {
			
			//convert to byte value when size exceed 127
			if(val2 > 127){
				val2 -= 128;
			}
			
			int[] array = {val0, val1, val2, 0, 0};
			
			super.sendStream(array);

		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}
	
	public void sendStream(String hex0, String hex1, String hex2) {
		
		int int0 = Integer.parseInt(hex0,16);
		int int1 = Integer.parseInt(hex1,16);
		int int2 = Integer.parseInt(hex2,16);
		
		this.sendStream(int0, int1, int2);
	}
	
	/**
	 * 
	 */
	public void sendStream(String hexString) {
		
		sendStream(hexString.substring(0, 2), hexString.substring(2, 4), hexString.substring(4, 6));
	}
}

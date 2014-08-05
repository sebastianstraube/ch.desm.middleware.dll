package ch.desm.middleware.modules.communication.endpoint.serial.fabisch;

import jssc.SerialPortEvent;
import jssc.SerialPortException;

import org.apache.log4j.Logger;

import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232Config;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232ConfigBuilder;


public class EndpointFabisch extends EndpointRs232 {

	private static Logger log = Logger.getLogger(EndpointFabisch.class);
	
	EndpointFabischPolling polling;
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

		this.polling = new EndpointFabischPolling(this, 256);
		this.initialize();
	}


	/**
	 * do work to initialze the controller on constructor call
	 */
	private void initialize() {
		polling.start();
		
		//send command start controller
		this.sendStream("");
	}
	
	/**
	 * 
	 */
	public void pollingCommand(){
		
		//read incoming message
		
		
	}

	@Override
	/**
	 * this listener receives a command from UBW32
	 * 
	 * @param SerialPortEvent event
	 */
	public synchronized void serialEvent(SerialPortEvent event) {
		String message = super.getSerialPortMessage(event);
		
		message += "fabi#"+message;
		System.out.println("fabi message: " + message);
			
	}

	
	/**
	 * TODO refactoring sleep
	 */
	@Override
	protected void sendStream(String command) {
		try {

			Thread.sleep(128);
			command += "\n";
			super.sendStream(command);

		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

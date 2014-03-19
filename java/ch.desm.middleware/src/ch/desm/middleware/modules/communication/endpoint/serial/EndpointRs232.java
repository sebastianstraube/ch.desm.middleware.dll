package ch.desm.middleware.modules.communication.endpoint.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointCommon;

public class EndpointRs232 extends
		CommunicationEndpointCommon implements SerialPortEventListener {

	protected SerialPort serialPort;
	protected boolean ignoreUbw32ControlMessages;
	
	public static enum EnumSerialPorts {
		COM1, COM2, COM3, COM4, COM5, COM6, COM7, COM8, COM9, COM10;
	}

	public EndpointRs232(EnumSerialPorts enumSerialPort) {
		this.serialPort = new SerialPort(enumSerialPort.name());
		this.ignoreUbw32ControlMessages = false;
		
		this.initialize();
	}

	private void initialize() {
		this.initializeSerialPorts();
		this.showSerialPortName();
	}
	
	/**
	 * 
	 */
	private void initializeSerialPorts() {
		System.out.print("intialize serial port:" + serialPort.getPortName());

		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			
			// Set the prepared mask
			serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
			
			//add event listener
			serialPort.addEventListener(this);
			
			System.out.println("... ready.");
		}
		catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method getPortNames() returns an array of strings. Elements of the array
	 * is already sorted.
	 */
	public void showSerialPortName() {
		serialPort.getPortName();
	}

	public void disconnectSerialPorts() {
		System.out.print("disconnecting all opened serial ports...");

		try {
			if (serialPort.isOpened()) {
				System.out.print(serialPort.getPortName() + " ");
				serialPort.purgePort(SerialPort.PURGE_RXABORT);
				serialPort.purgePort(SerialPort.PURGE_TXABORT);
				serialPort.closePort();
			}
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	public void testSeriaPort() {
		try {
			serialPort.writeString("Write Test to Serialport ..."
					+ serialPort.getPortName() + "\r\n");
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 *  sending common message to rs232
	 * 	@param message 
	 * 
	 */
	public void sendMessage(String message){
		System.out.println("sending message:\"" + message+"\" from " + this.getClass().getCanonicalName());
		this.sendCommand(message);
	}
	
	/**
	 * sending a message to serial port
	 * 
	 * TODO refactoring
	 * the messages will be concatenated with the terminator CR
	 * 
	 * @param commandString
	 */
	protected void sendCommand(String commandString) {
		boolean isSendOk = false;
		
		//TODO refactoring
		String terminator = "\n";
		commandString+=terminator;
		
		try {
			isSendOk = serialPort.writeString(commandString);

			if (isSendOk) {
				System.out.println("...command successfull sended.");
			}

		} catch (SerialPortException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * this listener receives a command from UBW32
	 * 
	 * TODO refactoring:
	 * the command will be sended when there is
	 * no "!" and no "OK" character sequence
	 * 
	 * @param SerialPortEvent event
	 */
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR()) {
			
			if (event.getEventValue() > 1) {
				try {
					byte buffer[] = serialPort.readBytes();
					String message = "";

					for (int i = 0; i < buffer.length; i++) {
						if (((char) buffer[i]) != '\n'
								&& ((char) buffer[i]) != '\r') {
							message += (char) buffer[i];
						}
					}

					//TODO refactoring
					if(!ignoreUbw32ControlMessages || ( 
						!message.contains("!") &&
						!message.endsWith("OK"))){
						
						System.out.println("serial event listener receive data on port:"
								+ serialPort.getPortName() + " with message:" + message);
						
						super.onIncomingEndpointMessage(message);
					}else{
						System.out.println("the message contains control character from UBW32 and will be ignored, message:\"" + message + "\"");
					}
					
				} catch (SerialPortException ex) {
					System.out.println(ex);
				}
			}
		}
		
		// If the CTS line status has changed, then the method
		// event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
		else if (event.isCTS()) {
			if (event.getEventValue() == 1) {
				System.out.println("CTS - ON");
			} else {
				System.out.println("CTS - OFF");
			}
		} else if (event.isDSR()) {
			if (event.getEventValue() == 1) {
				System.out.println("DSR - ON");
			} else {
				System.out.println("DSR - OFF");
			}
		}

	}
}

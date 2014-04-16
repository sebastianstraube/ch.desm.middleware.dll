package ch.desm.middleware.modules.communication.endpoint.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;

public abstract class EndpointRs232 extends EndpointCommon implements
		SerialPortEventListener {

	protected SerialPort serialPort;

	public static enum EnumSerialPorts {
		COM1, COM2, COM3, COM4, COM5, COM6, COM7, COM8, COM9,
		COM10, COM11, COM12, COM13, COM14, COM15, COM16, COM17,
		COM18, COM19, COM20, COM21, COM22
	}

	public EndpointRs232(EnumSerialPorts enumSerialPort) {
		this.serialPort = new SerialPort(enumSerialPort.name());

		this.initialize();
	}

	private void initialize() {
		this.initializeSerialPorts();
		this.getSerialPortName();
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

			// add event listener
			serialPort.addEventListener(this);

			System.out.println("... ready.");
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public String getSerialPortName() {
		return serialPort.getPortName();
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	public void testSeriaPort() {
		try {
			serialPort.writeString("Write Test to Serialport ..."
					+ serialPort.getPortName() + "\r\n");
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	/**
	 * sending a message to serial port
	 * 
	 * TODO refactoring the messages will be concatenated with the terminator CR
	 * 
	 * @param command
	 * @throws SerialPortException 
	 */
	protected synchronized void sendCommand(String command) throws SerialPortException {
		boolean isSendOk = false;

		isSendOk = serialPort.writeString(command);

		if (isSendOk) {
			System.out.println(serialPort.getPortName() + " send stream: " + command);
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
	public synchronized void serialEvent(SerialPortEvent event) {
		String message = this.getSerialPortMessage(event);
		System.out.println("received serial port message on port: " + this.serialPort.getPortName() + " with message: "+ message);

	}
	
	protected String getSerialPortMessage(SerialPortEvent event){
		String message ="";
		
		if (event.isRXCHAR()) {

			if (event.getEventValue() > 1) {
				try {
					byte buffer[] = serialPort.readBytes();

					for (int i = 0; i < buffer.length; i++) {
						if (((char) buffer[i]) != '\n'
								&& ((char) buffer[i]) != '\r') {
							message += (char) buffer[i];
						}
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
	
	return message;
	}
}

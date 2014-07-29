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
		COM1, COM2, COM3, COM4, COM5, COM6, COM7, COM8, COM9, COM10, COM11, COM12, COM13, COM14, COM15, COM16, COM17, COM18, COM19, COM20, COM21, COM22
	}

	public EndpointRs232(EnumSerialPorts enumSerialPort, EndpointRs232Config config) {
		this.serialPort = new SerialPort(enumSerialPort.name());
		this.initializeSerialPort(serialPort, config);
		// TODO: mxh: why call this getter here? does it have side effects?
		this.getSerialPortName();
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
	 * 
	 */
	public String getSerialPortName() {
		return serialPort.getPortName();
	}

	/**
	 * 
	 */
	private void initializeSerialPort(SerialPort port, EndpointRs232Config config) {
		System.out.print("intialize serial port:" + port.getPortName());

		try {
			port.openPort();
			port.setParams(config.getBaudRate(), config.getDataBits(), config.getStopBits(),
					config.getParity(), config.isRTS(), config.isDTR());
			port.setFlowControlMode(config.getFlowControl());
			port.setEventsMask(config.getEventMask());
			port.addEventListener(this);
			System.out.println("... ready.");
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
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
	 * sending a stream to serial port
	 * 
	 * @param stream
	 * @throws SerialPortException
	 */
	protected synchronized void sendStream(String stream) throws SerialPortException {
		if (stream != null && !stream.isEmpty() && serialPort.writeString(stream)) {
//			System.out.println(serialPort.getPortName() + " send stream: " + stream);
		}
	}

	@Override
	/**
	 * this listener receives a command from UBW32
	 * 
	 * @param SerialPortEvent event
	 */
	public synchronized void serialEvent(SerialPortEvent event) {
		String message = this.getSerialPortMessage(event);
		System.out.println("received serial port message on port: "
				+ this.serialPort.getPortName() + " with message: " + message);
		
		super.onIncomingEndpointMessage(message);
	}

	protected String getSerialPortMessage(SerialPortEvent event) {
		String message = "";

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
					ex.printStackTrace();
				}
			}
		}

		// If the CTS line status has changed, then the method
		// event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
		else if (event.isCTS()) {
			if (event.getEventValue() == 1) {
				System.out.println(this.serialPort.getClass() + ": CTS - ON");
			} else {
				System.out.println(this.serialPort.getClass() + ":CTS - OFF");
			}
		} else if (event.isDSR()) {
			if (event.getEventValue() == 1) {
				System.out.println(this.serialPort.getClass() + ":DSR - ON");
			} else {
				System.out.println(this.serialPort.getClass() + ":DSR - OFF");
			}
		}

		return message;
	}
}

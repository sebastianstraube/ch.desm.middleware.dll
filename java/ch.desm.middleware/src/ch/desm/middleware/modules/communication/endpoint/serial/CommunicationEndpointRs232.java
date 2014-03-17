package ch.desm.middleware.modules.communication.endpoint.serial;

import jssc.SerialPort;
import jssc.SerialPortException;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointAbstract;

public class CommunicationEndpointRs232 extends CommunicationEndpointAbstract {

	protected SerialPort serialPort;

	public static enum EnumSerialPorts {
		COM1, COM2, COM3, COM4, COM5, COM6, COM7, COM8, COM9, COM10;
	}

	public CommunicationEndpointRs232(EnumSerialPorts enumSerialPort) {
		serialPort = new SerialPort(enumSerialPort.name());
	}

	public void initialize() {
		this.initializeSerialPorts();
		this.showSerialPortName();
	}

	public SerialPort getSerialPorts() {
		return serialPort;
	}

	/**
	 * Method getPortNames() returns an array of strings. Elements of the array
	 * is already sorted.
	 */
	public void showSerialPortName() {
		serialPort.getPortName();
	}

	private void initializeSerialPorts() {
		System.out.println("intialize port(s)...");

		try {
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			// Preparing a mask. In a mask, we need to specify the types of
			// events that we want to track.
			// Well, for example, we need to know what came some data, thus in
			// the mask must have the
			// following value: MASK_RXCHAR. If we, for example, still need to
			// know about changes in states
			// of lines CTS and DSR, the mask has to look like this:
			// SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS +
			// SerialPort.MASK_DSR

			// Set the prepared mask
			serialPort.setEventsMask(SerialPort.MASK_RXCHAR);

			// TODO refactor add event listener
			// serialPort.addEventListener(listener);
		}

		catch (SerialPortException e) {
			System.err.println(e);
		}
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
			System.err.println(e);
		}
	}

	public void testSeriaPort() {
		try {
			serialPort.writeString("Write Test to Serialport ..."
					+ serialPort.getPortName() + "\r\n");
		} catch (SerialPortException e) {
			System.err.println(e);
		}
	}

	protected void send(String commandString) {
		boolean isSendOk = false;
		try {
			isSendOk = serialPort.writeString(commandString);

			if (isSendOk) {
				System.out.println("command successfull sended "
						+ commandString);
			}

		} catch (SerialPortException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

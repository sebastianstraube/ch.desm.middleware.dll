package ch.desm.middleware.communication.endpoint.serial;

import jssc.SerialPort;

public class EndpointRs232Config {

	public static final int BAUDRATE_110 = SerialPort.BAUDRATE_110;
	public static final int BAUDRATE_300 = SerialPort.BAUDRATE_300;
	public static final int BAUDRATE_600 = SerialPort.BAUDRATE_600;
	public static final int BAUDRATE_1200 = SerialPort.BAUDRATE_1200;
	public static final int BAUDRATE_4800 = SerialPort.BAUDRATE_4800;
	public static final int BAUDRATE_9600 = SerialPort.BAUDRATE_9600;
	public static final int BAUDRATE_14400 = SerialPort.BAUDRATE_14400;
	public static final int BAUDRATE_19200 = SerialPort.BAUDRATE_19200;
	public static final int BAUDRATE_38400 = SerialPort.BAUDRATE_38400;
	public static final int BAUDRATE_57600 = SerialPort.BAUDRATE_57600;
	public static final int BAUDRATE_115200 = SerialPort.BAUDRATE_115200;
	public static final int BAUDRATE_128000 = SerialPort.BAUDRATE_128000;
	public static final int BAUDRATE_256000 = SerialPort.BAUDRATE_256000;

	public static final int DATABITS_5 = SerialPort.DATABITS_5;
	public static final int DATABITS_6 = SerialPort.DATABITS_6;
	public static final int DATABITS_7 = SerialPort.DATABITS_7;
	public static final int DATABITS_8 = SerialPort.DATABITS_8;

	public static final int STOPBITS_1 = SerialPort.STOPBITS_1;
	public static final int STOPBITS_2 = SerialPort.STOPBITS_2;
	public static final int STOPBITS_1_5 = SerialPort.STOPBITS_1_5;

	public static final int PARITY_NONE = SerialPort.PARITY_NONE;
	public static final int PARITY_ODD = SerialPort.PARITY_ODD;
	public static final int PARITY_EVEN = SerialPort.PARITY_EVEN;
	public static final int PARITY_MARK = SerialPort.PARITY_MARK;
	public static final int PARITY_SPACE = SerialPort.PARITY_SPACE;
	 
	public static final int PURGE_RXABORT = SerialPort.PURGE_RXABORT;
	public static final int PURGE_RXCLEAR = SerialPort.PURGE_RXCLEAR;
	public static final int PURGE_TXABORT = SerialPort.PURGE_TXABORT;
	public static final int PURGE_TXCLEAR = SerialPort.PURGE_TXCLEAR;

	public static final int MASK_RXCHAR = SerialPort.MASK_RXCHAR;
	public static final int MASK_RXFLAG = SerialPort.MASK_RXFLAG;
	public static final int MASK_TXEMPTY = SerialPort.MASK_TXEMPTY;
	public static final int MASK_CTS = SerialPort.MASK_CTS;
	public static final int MASK_DSR = SerialPort.MASK_DSR;
	public static final int MASK_RLSD = SerialPort.MASK_RLSD;
	public static final int MASK_BREAK = SerialPort.MASK_BREAK;
	public static final int MASK_ERR = SerialPort.MASK_ERR;
	public static final int MASK_RING = SerialPort.MASK_RING;

	public static final int FLOWCONTROL_NONE = SerialPort.FLOWCONTROL_NONE;
	public static final int FLOWCONTROL_RTSCTS_IN = SerialPort.FLOWCONTROL_RTSCTS_IN;
	public static final int FLOWCONTROL_RTSCTS_OUT = SerialPort.FLOWCONTROL_RTSCTS_OUT;
	public static final int FLOWCONTROL_XONXOFF_IN = SerialPort.FLOWCONTROL_XONXOFF_IN;
	public static final int FLOWCONTROL_XONXOFF_OUT = SerialPort.FLOWCONTROL_XONXOFF_OUT;

	private int baudRate;
	private int dataBits;
	private int stopBits;
	private int parity;
	private int eventMask;
	private int flowControl;
	private boolean rts;
	private boolean dtr;
	
	public EndpointRs232Config(int baudRate, int dataBits, int stopBits,
			int parity, int eventMask, int flowControl, boolean rts,
			boolean dtr) {
		super();
		this.baudRate = baudRate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
		this.eventMask = eventMask;
		this.flowControl = flowControl;
		this.rts = rts;
		this.dtr = dtr;
	}
	
	public int getBaudRate() {
		return baudRate;
	}
	
	public int getDataBits() {
		return dataBits;
	}
	
	public int getStopBits() {
		return stopBits;
	}
	
	public int getParity() {
		return parity;
	}
	
	public int getEventMask() {
		return eventMask;
	}
	
	public int getFlowControl() {
		return flowControl;
	}
	
	public boolean isRTS() {
		return rts;
	}
	
	public boolean isDTR() {
		return dtr;
	}
	
}

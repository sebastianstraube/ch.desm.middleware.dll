package ch.desm.middleware.communication.endpoint.serial;

public class EndpointRs232ConfigBuilder {

	private int baudRate;
	private int dataBits;
	private int stopBits;
	private int parity;
	private int eventMask;
	private int flowControl;
	private boolean setRTS;
	private boolean setDTR;
	
	public EndpointRs232ConfigBuilder() {
		baudRate = EndpointRs232Config.BAUDRATE_9600;
		dataBits = EndpointRs232Config.DATABITS_8;
		stopBits = EndpointRs232Config.STOPBITS_1;
		parity = EndpointRs232Config.PARITY_NONE;
		eventMask = EndpointRs232Config.MASK_RXCHAR;
		flowControl = EndpointRs232Config.FLOWCONTROL_NONE;
		setRTS = true;
		setDTR = true;
	}
	
	public EndpointRs232Config build() {
		return new EndpointRs232Config(baudRate, dataBits, stopBits, parity, eventMask, flowControl, setRTS, setDTR);
	}

	public EndpointRs232ConfigBuilder setBaudRate(int baudRate) {
		this.baudRate = baudRate;
		return this;
	}

	public EndpointRs232ConfigBuilder setDataBits(int dataBits) {
		this.dataBits = dataBits;
		return this;
	}

	public EndpointRs232ConfigBuilder setStopBits(int stopBits) {
		this.stopBits = stopBits;
		return this;
	}

	public EndpointRs232ConfigBuilder setParity(int parity) {
		this.parity = parity;
		return this;
	}

	public EndpointRs232ConfigBuilder setEventMask(int eventMask) {
		this.eventMask = eventMask;
		return this;
	}
	
	public EndpointRs232ConfigBuilder setFlowControl(int flowControl) {
		this.flowControl = flowControl;
		return this;
	}

	public EndpointRs232ConfigBuilder setRTS(boolean setRTS) {
		this.setRTS = setRTS;
		return this;
	}

	public EndpointRs232ConfigBuilder setDTR(boolean setDTR) {
		this.setDTR = setDTR;
		return this;
	}
	
}

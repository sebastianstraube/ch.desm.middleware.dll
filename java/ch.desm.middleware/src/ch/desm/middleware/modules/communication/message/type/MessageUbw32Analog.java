package ch.desm.middleware.modules.communication.message.type;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32PortAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;

public class MessageUbw32Analog extends MessageUbw32Base {

	private ArrayList<EndpointUbw32PortAnalog> list;

	/**
	 * TODO refactoring
	 */
	public EndpointUbw32PortAnalog portAN0;
	public EndpointUbw32PortAnalog portAN1;
	public EndpointUbw32PortAnalog portAN2;
	public EndpointUbw32PortAnalog portAN3;
	public EndpointUbw32PortAnalog portAN4;
	public EndpointUbw32PortAnalog portAN5;
	public EndpointUbw32PortAnalog portAN6;
	public EndpointUbw32PortAnalog portAN7;
	public EndpointUbw32PortAnalog portAN8;
	public EndpointUbw32PortAnalog portAN9;
	public EndpointUbw32PortAnalog portAN10;
	public EndpointUbw32PortAnalog portAN11;
	public EndpointUbw32PortAnalog portAN12;
	public EndpointUbw32PortAnalog portAN13;
	public EndpointUbw32PortAnalog portAN14;
	public EndpointUbw32PortAnalog portAN15;
	
	public MessageUbw32Analog(String payload, String topic) {
		super(payload, topic);

		this.list = new ArrayList<EndpointUbw32PortAnalog>();

		payload = cleanPayload(payload);
		this.parsePayload(payload);
		this.initialize();
	}

	/**
	 * TODO implementation
	 * 
	 * @param payload
	 */
	protected void parsePayload(String payload) {
		String parts[] = payload.split(",");

		portAN0 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[1]);
		portAN1 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[2]);
		portAN2 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[3]);
		portAN3 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[4]);
		portAN4 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[5]);
		portAN5 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[6]);
		portAN6 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[7]);
		portAN7 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[8]);
		portAN8 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[9]);
		portAN9 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[10]);
		portAN10 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[11]);
		portAN11 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[12]);
		portAN12 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[13]);
		portAN13 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[14]);
		portAN14 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[15]);
		portAN15 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0, parts[16]);
	}

	/**
	 * TODO implementation analog ports (refactoring)
	 */
	protected void initialize() {
		list.add(portAN0);
		list.add(portAN1);
		list.add(portAN2);
		list.add(portAN3);
		list.add(portAN4);
		list.add(portAN5);
		list.add(portAN6);
		list.add(portAN7);
		list.add(portAN8);
		list.add(portAN9);
		list.add(portAN10);
		list.add(portAN11);
		list.add(portAN12);
		list.add(portAN13);
		list.add(portAN14);
		
	}

	
	public String getInputValue(EnumEndpointUbw32RegisterAnalog register){
		
		String value = "";
		
		switch(register){
		case AN0: {value = portAN0.getValue(); break;}	case AN1: {value = portAN1.getValue(); break;}	case AN2: {value = portAN2.getValue(); break;}	case AN3: {value = portAN3.getValue(); break;}	case AN4: {value = portAN4.getValue(); break;}	case AN5: {value = portAN5.getValue(); break;}	case AN6: {value = portAN6.getValue(); break;}	case AN7: {value = portAN7.getValue(); break;}	case AN8: {value = portAN8.getValue(); break;}	case AN9: {value = portAN9.getValue(); break;}	case AN10: {value = portAN10.getValue(); break;}	case AN11: {value = portAN11.getValue(); break;}	case AN12: {value = portAN12.getValue(); break;}	case AN13: {value = portAN13.getValue(); break;}	case AN14: {value = portAN14.getValue(); break;}	case AN15: {value = portAN15.getValue(); break;}
			default: System.err.println("register is not assignable: "+ register);
			}		
		return value;
	}
	
	/**
	 * TODO implementation
	 */
	public String toString() {
		String s = super.toString();
		
		s += "analog message: ";
		s += "PortAN0: " + portAN0.getValue();
		 s += "PortAN1: " + portAN1.getValue();
		 s += "PortAN2: " + portAN2.getValue();
		 s += "PortAN3: " + portAN3.getValue();
		 s += "PortAN4: " + portAN4.getValue();
		 s += "PortAN5: " + portAN5.getValue();
		 s += "PortAN6: " + portAN6.getValue();
		 s += "PortAN7: " + portAN7.getValue();
		 s += "PortAN8: " + portAN8.getValue();
		 s += "PortAN9: " + portAN9.getValue();
		 s += "PortAN10: " + portAN10.getValue();
		 s += "PortAN11: " + portAN11.getValue();
		 s += "PortAN12: " + portAN12.getValue();
		 s += "PortAN13: " + portAN13.getValue();
		 s += "PortAN14: " + portAN14.getValue();
		 s += "PortAN15: " + portAN15.getValue();

		return s;
	}
}

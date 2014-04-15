package ch.desm.middleware.modules.communication.message.type;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32PortAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32PortDigital;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageUbw32 extends MessageBase {

	ArrayList<EndpointUbw32PortAnalog> listAnalog;
	ArrayList<EndpointUbw32PortDigital> listDigital;
	
	public EndpointUbw32PortDigital portA;
	public EndpointUbw32PortDigital portB;
	public EndpointUbw32PortDigital portC;
	public EndpointUbw32PortDigital portD;
	public EndpointUbw32PortDigital portE;
	public EndpointUbw32PortDigital portF;
	public EndpointUbw32PortDigital portG;
	
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

	public boolean isDigital;
	
	public MessageUbw32(String payload, EnumMessageTopic topic) {
		super(payload, topic);
		
		this.listAnalog = new ArrayList<EndpointUbw32PortAnalog>();
		this.listDigital = new ArrayList<EndpointUbw32PortDigital>();
		
		payload = this.cleanPayload(payload);
		this.processPayload(payload);
		this.createList();
	}

	private String cleanPayload(String payload){
		
		if(payload.contains("IA")){
			int msg = payload.lastIndexOf("IA");
			payload = payload.substring(msg, payload.length());
			
		}else if(payload.contains("II")){
			payload = payload.replaceFirst("II", "I");
		}
		
		return payload;
	}
	
	/**
	 * TODO implementation
	 * 
	 * @param payload
	 */
	private void processPayload(String payload) {
		String parts[] = payload.split(",");

		if (parts[0].equals("I")) {
			// input digital
			portA = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.A,
					parts[1]);
			portB = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.B,
					parts[2]);
			portC = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.C,
					parts[3]);
			portD = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.D,
					parts[4]);
			portE = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.E,
					parts[5]);
			portF = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.F,
					parts[6]);
			portG = new EndpointUbw32PortDigital(EnumEndpointUbw32PortDigital.G,
					parts[7]);

			isDigital = true;
			
		} else if (parts[0].equals("IA")) {
			// input analog
			portAN0 = new EndpointUbw32PortAnalog(EnumEndpointUbw32PortAnalog.AN0,
						parts[1]);
			
			isDigital = false;
		}
	}
	
	/**
	 * TODO implementation analog ports (refactoring)
	 */
	private void createList(){
		if(isDigital){
			listDigital.add(portA);
			listDigital.add(portB);
			listDigital.add(portC);
			listDigital.add(portD);
			listDigital.add(portE);
			listDigital.add(portF);
			listDigital.add(portG);
		}else{
			listAnalog.add(portAN0);
		}
	}
	
	public ArrayList<EndpointUbw32PortAnalog> getPortsAnalog(){
		return this.listAnalog;
	}
	
	public ArrayList<EndpointUbw32PortDigital> getPortsDigital(){
		return this.listDigital;
	}

	public boolean getInputDigitalValue(EnumEndpointUbw32RegisterDigital register) {

		String pin = register.name().substring(1);
		
		boolean isInputOn = false;
	
		switch(register){
			case A0: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A1: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A2: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A3: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A4: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A5: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A6: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A7: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A8: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A9: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A10: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A11: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A12: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A13: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A14: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}	case A15: {isInputOn = getInputDigital(portA.getPinBitMask(), pin); break;}
			case B0: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B1: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B2: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B3: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B4: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B5: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B6: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B7: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B8: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B9: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B10: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B11: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B12: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B13: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B14: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}	case B15: {isInputOn = getInputDigital(portB.getPinBitMask(), pin); break;}
			case C0: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C1: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C2: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C3: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C4: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C5: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C6: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C7: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C8: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C9: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C10: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C11: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C12: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C13: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C14: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}	case C15: {isInputOn = getInputDigital(portC.getPinBitMask(), pin); break;}
			case D0: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D1: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D2: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D3: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D4: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D5: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D6: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D7: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D8: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D9: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D10: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D11: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D12: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D13: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D14: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}	case D15: {isInputOn = getInputDigital(portD.getPinBitMask(), pin); break;}
			case E0: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E1: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E2: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E3: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E4: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E5: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E6: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E7: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E8: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E9: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E10: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E11: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E12: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E13: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E14: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}	case E15: {isInputOn = getInputDigital(portE.getPinBitMask(), pin); break;}
			case F0: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F1: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F2: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F3: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F4: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F5: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F6: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F7: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F8: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F9: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F10: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F11: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F12: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F13: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F14: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}	case F15: {isInputOn = getInputDigital(portF.getPinBitMask(), pin); break;}
			case G0: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G1: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G2: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G3: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G4: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G5: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G6: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G7: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G8: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G9: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G10: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G11: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G12: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G13: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G14: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}	case G15: {isInputOn = getInputDigital(portG.getPinBitMask(), pin); break;}
			default: System.err.println("register is not assignable: "+ register);
		}
	
		return isInputOn;
	}
	
	public String getInputAnalogValue(EnumEndpointUbw32RegisterAnalog register){
		
		String value = "";
		
		switch(register){
		case AN0: {value = portAN0.getValue(); break;}	case AN1: {value = portAN1.getValue(); break;}	case AN2: {value = portAN2.getValue(); break;}	case AN3: {value = portAN3.getValue(); break;}	case AN4: {value = portAN4.getValue(); break;}	case AN5: {value = portAN5.getValue(); break;}	case AN6: {value = portAN6.getValue(); break;}	case AN7: {value = portAN7.getValue(); break;}	case AN8: {value = portAN8.getValue(); break;}	case AN9: {value = portAN9.getValue(); break;}	case AN10: {value = portAN10.getValue(); break;}	case AN11: {value = portAN11.getValue(); break;}	case AN12: {value = portAN12.getValue(); break;}	case AN13: {value = portAN13.getValue(); break;}	case AN14: {value = portAN14.getValue(); break;}	case AN15: {value = portAN15.getValue(); break;}
			default: System.err.println("register is not assignable: "+ register);
			}		
		return value;
	}
	
	/**
	 * TODO Implementation for multi analog messaging
	 * 
	 * @param register
	 * @return
	 */
//	private String getInputAnalog(EndpointUbw32PortAnalog port){
//		return null;
//	}
	
	private boolean getInputDigital(int pinBitMask, String pin) {
		String mask = "";
		for (int i = 15; i >= 0; i--) {
			mask = (pinBitMask & 1) + mask;
			pinBitMask = pinBitMask >>>= 1;
		}
		boolean isInputOn = mask.charAt(Integer.valueOf(pin)) == '1';

		return isInputOn;
	}	
	
	/**
	 * TODO implementation
	 */
	public String toString() {
	 String s = super.toString();
	 
	 if(isDigital){
		 s += "analog message: ";
		 s += "PortAN0: " + portAN0.getValue();
//		 s += "PortAN1: " + portAN0.getValue();
//		 s += "PortAN2: " + portAN0.getValue();
//		 s += "PortAN3: " + portAN0.getValue();
//		 s += "PortAN4: " + portAN0.getValue();
//		 s += "PortAN5: " + portAN0.getValue();
//		 s += "PortAN6: " + portAN0.getValue();
//		 ....
	 }else{
		 s += "digital message: ";
		 s += "PortA: " + portA.getPinBitMask();
		 s += ", ";
		 s += "PortB: " + portB.getPinBitMask();
		 s += ", ";
		 s += "PortC: " + portC.getPinBitMask();
		 s += ", ";
		 s += "PortD: " + portD.getPinBitMask();
		 s += ", ";
		 s += "PortE: " + portE.getPinBitMask();
		 s += ", ";
		 s += "PortF: " + portF.getPinBitMask();
		 s += ", ";
		 s += "PortG: " + portG.getPinBitMask();
	 }

	
	 return s;
	 }
}

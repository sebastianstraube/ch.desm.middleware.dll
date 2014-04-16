package ch.desm.middleware.modules.component.interlocking;

import java.util.HashMap;
import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;

public class OMLEndpointUbw32Configuration {

	//TODO refactoring
	public static final String PINBITMASK_CONFIGURATION_DIGITAL = "14784,199,65505,16383,64528,52939,64575";
	public static final String PINBITMASK_INPUT_ANALOG = "11";
	
	private static Map<EnumEndpointUbw32RegisterDigital, String> mapDigital;
	private static Map<EnumEndpointUbw32RegisterAnalog, String> mapAnalog;
	
	public OMLEndpointUbw32Configuration(){
		mapDigital = new HashMap<EnumEndpointUbw32RegisterDigital, String>();
		mapAnalog = new HashMap<EnumEndpointUbw32RegisterAnalog, String>();
		
		this.initializeDigital();
	}
	
	public Map<EnumEndpointUbw32RegisterDigital, String> getMapInputDigital(){
		return mapDigital;
	}
	
	public Map<EnumEndpointUbw32RegisterAnalog, String> getMapInputAnalog(){
		return mapAnalog;
	}
	
	public boolean isKeyAvailable(String id){
		return mapDigital.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return mapDigital.containsValue(id);
	}
	
	private void initializeDigital(){
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C4,"1.90.01"); //Störungslampe Einfahrvorsignal F*
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G6,"1.90.02"); //Einfahrsignal F; Notrot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G7,"1.90.03"); //Einfahrsignal F; Grün FB 3
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G8,"1.90.04"); //Einfahrsignal F; Rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C3,"1.90.05"); //Einfahrsignal F; Grün FB1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G9,"1.90.06"); //Einfahrsignal E; Grün FB1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A0,"1.90.07"); //Einfahrsignal E; Rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E8,"1.90.08"); //Einfahrsignal E; Notrot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E9,"1.90.09"); //Einfahrsignal G; Grün FB1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A15,"1.90.10"); //Einfahrsignal G; Rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B4,"1.90.11"); //Einfahrsignal G; Grün FB3
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B3,"1.90.12"); //Einfahrsignal D EMM; Rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B9,"1.90.14"); //Ausfahrsignal C EMM; Grün
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B10,"1.90.15"); //Ausfahrsingal C EMM; Rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B11,"1.90.16"); //Störungslampe Einfahrvorsignal G*
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F13,"1.91.22"); //Rückmelden möglich nach Zollbrück
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F12,"1.90.31"); //Block von Langnau, rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B12,"1.90.32"); //Block von Langnau, weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B13,"1.90.33"); //Block nach Langnau, weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B14,"1.90.34"); //Block nach Langnau, rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B15,"1.90.35"); //Isolierung egf
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D14,"1.90.36"); //Isolierung 1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F5,"1.90.37"); //Fahrstrasse ef
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F2,"1.90.38"); //Isolierung ef
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F8,"1.90.39"); //Isolierung cd
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A2,"1.90.40"); //Fahrstrasse gf
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A3,"1.90.41"); //Block nach Zollbrück, rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A4,"1.90.42"); //Block nach Zollbrück, weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A5,"1.90.43"); //Block von Zollbrück, weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A14,"1.90.44"); //Block von Zollbrück, rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C4,"1.90.01"); //Rückmelden möglich nach Langnau
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G6,"1.91.02"); //Fahrtrichtung verlangt von Langnau
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A9,"2.92.01"); //Weichenwecker
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A10,"2.92.02"); //Blockwecker
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D15,"2.90.01"); //Signalwecker
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F4,"3.01.01"); //WS1 Freigabemagnet
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E6,"3.04.01"); //FSS Sperrmagnet
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E5,"3.04.02"); //FSS Kuppelstrommagnet
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D13,"6.91.02"); //Freie Bahn anfordern nach Zollbrück
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D12,"6.91.01"); //Freie Bahn festhalten nach Zollbrück
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D4,"6.91.03"); //Rückmelden nach Zollbrück
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D5,"6.91.04"); //Rückmelden nach Langnau
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D6,"6.91.05"); //Freie Bahn anfordern nach Langnau
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D7,"6.91.06"); //Freie Bahn festhalten nach Langnau
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F0,"6.91.07"); //Blockumgehung EG
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D8,"6.91.08"); //Richtung Emmenmatt
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G1,"6.91.10"); //Nottaste W1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D9,"6.91.13"); //Notauflösung
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A6,"6.91.14"); //Richtung Zollbrück
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A7,"6.91.15"); //Isolierumgehung für Signalfahrtstellung
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D10,"7.91.01"); //WS1 Grundstellung +
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D11,"7.91.02"); //WS1 gedrückt in + Lage
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D13,"7.91.04"); //WS1 in Grundstellung - (45° Lage)
	}
	
	public void initializeAnalog(){
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.07");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.06");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.05");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.04");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.03");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.01");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.19");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.20");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.21");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.22");
		mapAnalog.put(EnumEndpointUbw32RegisterAnalog.AN0,"8.91.23");
	}
	
	/**
	 * TODO implementation for multi messaging analog
	 * @param register
	 */
	public String getGlobalIdFSS(EnumEndpointUbw32RegisterAnalog register, int value){
		
		String globalId = "";
		
		//Quelle: Verdrahtungsliste - Analog Input Definition
		switch(register){
			case AN0: 	if(value > 235 && value < 246){globalId = "8.91.07";} else //FSS 90° F
						if(value > 296 && value < 308){globalId = "8.91.06";} else //FSS 80° F
						if(value > 349 && value < 362){globalId = "8.91.05";} else //FSS 45° F
						if(value > 390 && value < 402){globalId = "8.91.04";} else //FSS 30° F
						if(value > 417 && value < 429){globalId = "8.91.03";} else //FSS 10° F
						if(value > 461 && value < 474){globalId = "8.91.01";} else //FSS Grundstellung
						if(value > 496 && value < 513){globalId = "8.91.19";} else //FSS 10° EG
						if(value > 522 && value < 534){globalId = "8.91.20";} else //FSS 30° EG
						if(value > 570 && value < 582){globalId = "8.91.21";} else //FSS 45° EG
						if(value > 637 && value < 654){globalId = "8.91.22";} else //FSS 80° EG
						if(value > 667 && value < 682){globalId = "8.91.23";} else //FSS 90° EG 
						{
							System.out.println("the register " + register.name() + " has no mapped value: " + value);
						}
			default: System.err.println("no mapping defined for analog register:" + register.name());
		}
		
		return globalId;
	}

}

package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;

public class Re420EndpointUbw32Configuration {

	//TODO refactoring
	public static final String PINBITMASK_CONFIGURATION_DIGITAL = "14784,199,65505,16383,64528,52939,64575";
	public static final String PINBITMASK_INPUT_ANALOG = "1";

	private static Map<EnumEndpointUbw32RegisterDigital, String> mapDigital;
	private static Map<EnumEndpointUbw32RegisterAnalog, String> mapAnalog;
	
	public Re420EndpointUbw32Configuration(){
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
	
	public void initializeDigital(){
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C4,"S126"); //Steuerstrom
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G8,"S129"); //Stromabnehmer
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G9,"S132"); //Hauptschalter
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A4,"S172.1"); //Kompressor Automat
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A14,"S172.2"); //Kompressor direkt
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B8,"S169"); //Zugsammelschiene
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B11,"S311"); //Beleuchtung Zug
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A1,"S316_1"); //Dienstbeleuchtung 1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E9,"S140a"); //Wendeschalter 140a vorwärts
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B3,"S140b"); //Wendeschalter 140b rückwärts
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A9,"S150a"); //Fahrschalter 150a
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B9,"S150b"); //Fahrschalter 150b
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B10,"S150d"); //Fahrschalter 150d
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F13,"S150e"); //Fahrschalter 150e
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F12,"S150f"); //Fahrschalter 150f
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B14,"S150g"); //Fahrschalter 150g
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B15,"S150l"); //Fahrschalter 150l
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G7,"S189.1"); //Pfeife Stufe 1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A0,"S189.2"); //Pfeife Stufe 2
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A2,"S242.01"); //Rückstelltaste Zugsicherung
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G6,"S242.03"); //Rückstelltaste ZUB befreien
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F5,"S242.02"); //M-Taste
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F4,"S281"); //Schleuderschutztaste
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B5,"S182.3"); //Türfreigabe links
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A10,"S182.4"); //Türfreigabe rechts
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B4,"S182"); //Türverriegelung
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D15,"S316.1"); //Stirnlampe links weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B12,"S316.4"); //Stirnlampe links rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F2,"S316.2"); //Strinlampe oben weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B13,"S316.5"); //Strinlampe oben rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F8,"S316.3"); //Stirnlampe rechts weiss
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D14,"S316.6"); //Stirnlampe rechts rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E8,"S235"); //Totmannpedal
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D6,"W238_t"); //Tiefton (Schnellgang, Signum, Totmann)
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D4,"W238_h"); //Hochton (Langsamgang)
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E3,"L242b"); //Signumschalter gelb
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G15,"L242a"); //Signumschalter rot
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C2,"D94VI.1"); //Vist-LZB.1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E7,"D94VI.2"); //Vist-LZB.2
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E5,"D94VI.3"); //Vist-LZB.3
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E4,"D94VI.4"); //Vist-LZB.4
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E2,"D94VI.5"); //Vist-LZB.5
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G12,"D94VI.6"); //Vist-LZB.6
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E1,"D94VI.7"); //Vist-LZB.7
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A7,"D94VI.8"); //Vist-LZB.8
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G0,""); //Vsoll-LZB.1
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F1,""); //Vsoll-LZB.2
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D7,""); //Vsoll-LZB.3
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D5,""); //Vsoll-LZB.4
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D13,""); //Vsoll-LZB.5
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D3,""); //Vsoll-LZB.6
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D1,""); //Vsoll-LZB.7
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C13,""); //Vsoll-LZB.8
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D0,"D94u.1"); //Uhr (hh)
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D10,"D94u.2"); //Uhr (mm)
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D8,"D94U.3"); //Uhr
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B1,"AO269"); //Drucksensor Hauptleitung
		mapDigital.put(EnumEndpointUbw32RegisterDigital.B0,"AO173"); //Drucksensor Bremszylinder
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C3,"L281"); //Lampe Schleuderbremse
		mapDigital.put(EnumEndpointUbw32RegisterDigital.D12,"L281.1"); //Schleuderbremse von Simulator
		mapDigital.put(EnumEndpointUbw32RegisterDigital.C1,"L175"); //Lampe Ventilation/ Oelpumpe
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E6,"L163"); //Lampe Stufenschalter
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G13,"L181"); //Lampe Abfahrbefehl
		mapDigital.put(EnumEndpointUbw32RegisterDigital.A6,"L182.3"); //Lampe Türfreigabe links
		mapDigital.put(EnumEndpointUbw32RegisterDigital.G1,"L185"); //Lampe Tür offen
		mapDigital.put(EnumEndpointUbw32RegisterDigital.F0,"L182.4"); //Lampe Türfreigabe rechts
		mapDigital.put(EnumEndpointUbw32RegisterDigital.E0,"L242.2"); //Lampe M-Taste
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
							System.out.println("the register " + register + " has no mapped value: " + value);
						}
			default: System.err.println("no mapping defined for analog register:" + register);
		}
		
		return globalId;
	}

}

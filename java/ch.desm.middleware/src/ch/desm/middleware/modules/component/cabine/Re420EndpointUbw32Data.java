package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;

public class Re420EndpointUbw32Data {

	//TODO refactoring
	public static final String PINBITMASK_CONFIGURATION_DIGITAL = "17943,65336,16,49152,768,12596,960";
	public static final String PINBITMASK_INPUT_ANALOG = "0";
	
	private static Map<String, EnumEndpointUbw32RegisterDigital> mapDigital;
	private static Map<String, EnumEndpointUbw32RegisterAnalog> mapAnalog;
	
	public Re420EndpointUbw32Data(){
		mapDigital = new HashMap<String, EnumEndpointUbw32RegisterDigital>();
		mapAnalog = new HashMap<String, EnumEndpointUbw32RegisterAnalog>();
		
		this.initializeDigital();
		this.initializeAnalog();
	}
	
	public Map<String, EnumEndpointUbw32RegisterDigital> getMapInputDigital(){
		return mapDigital;
	}
	
	public Map<String, EnumEndpointUbw32RegisterAnalog> getMapInputAnalog(){
		return mapAnalog;
	}
	
	public boolean isKeyAvailable(String id){
		return mapDigital.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return mapDigital.containsValue(id);
	}
	
	private void initializeDigital(){
		mapDigital.put("S126", EnumEndpointUbw32RegisterDigital.C4); //Steuerstrom
		mapDigital.put("S129", EnumEndpointUbw32RegisterDigital.G8); //Stromabnehmer
		mapDigital.put("S132", EnumEndpointUbw32RegisterDigital.G9); //Hauptschalter
		mapDigital.put("S172.1", EnumEndpointUbw32RegisterDigital.A4); //Kompressor Automat
		mapDigital.put("S172.2", EnumEndpointUbw32RegisterDigital.A14); //Kompressor direkt
		mapDigital.put("S169", EnumEndpointUbw32RegisterDigital.B8); //Zugsammelschiene
		mapDigital.put("S311", EnumEndpointUbw32RegisterDigital.B11); //Beleuchtung Zug
		mapDigital.put("S316_1", EnumEndpointUbw32RegisterDigital.A1); //Dienstbeleuchtung 1

		mapDigital.put("S140a", EnumEndpointUbw32RegisterDigital.E9); //Wendeschalter 140a vorwärts
		mapDigital.put("S140b", EnumEndpointUbw32RegisterDigital.B3); //Wendeschalter 140b rückwärts
		mapDigital.put("S150a", EnumEndpointUbw32RegisterDigital.A9); //Fahrschalter 150a
		mapDigital.put("S150b", EnumEndpointUbw32RegisterDigital.B9); //Fahrschalter 150b
		mapDigital.put("S150d", EnumEndpointUbw32RegisterDigital.B10); //Fahrschalter 150d
		mapDigital.put("S150e", EnumEndpointUbw32RegisterDigital.F13); //Fahrschalter 150e
		mapDigital.put("S150f", EnumEndpointUbw32RegisterDigital.F12); //Fahrschalter 150f
		mapDigital.put("S150g", EnumEndpointUbw32RegisterDigital.B14); //Fahrschalter 150g
		mapDigital.put("S150l", EnumEndpointUbw32RegisterDigital.B15); //Fahrschalter 150l
		mapDigital.put("S189.1", EnumEndpointUbw32RegisterDigital.G7); //Pfeife Stufe 1
		mapDigital.put("S189.2", EnumEndpointUbw32RegisterDigital.A0); //Pfeife Stufe 2
		mapDigital.put("S242.01", EnumEndpointUbw32RegisterDigital.A2); //Rückstelltaste Zugsicherung
		mapDigital.put("S242.03", EnumEndpointUbw32RegisterDigital.G6); //Rückstelltaste ZUB befreien
		mapDigital.put("S242.02", EnumEndpointUbw32RegisterDigital.F5); //M-Taste





		mapDigital.put("S281", EnumEndpointUbw32RegisterDigital.F4); //Schleuderschutztaste
		mapDigital.put("S182.3", EnumEndpointUbw32RegisterDigital.B5); //Türfreigabe links
		mapDigital.put("S182.4", EnumEndpointUbw32RegisterDigital.A10); //Türfreigabe rechts
		mapDigital.put("S182", EnumEndpointUbw32RegisterDigital.B4); //Türverriegelung



		mapDigital.put("S316.1", EnumEndpointUbw32RegisterDigital.D15); //Stirnlampe links weiss
		mapDigital.put("S316.4", EnumEndpointUbw32RegisterDigital.B12); //Stirnlampe links rot
		mapDigital.put("S316.2", EnumEndpointUbw32RegisterDigital.F2); //Strinlampe oben weiss
		mapDigital.put("S316.5", EnumEndpointUbw32RegisterDigital.B13); //Strinlampe oben rot
		mapDigital.put("S316.3", EnumEndpointUbw32RegisterDigital.F8); //Stirnlampe rechts weiss
		mapDigital.put("S316.6", EnumEndpointUbw32RegisterDigital.D14); //Stirnlampe rechts rot


		mapDigital.put("S235", EnumEndpointUbw32RegisterDigital.E8); //Totmannpedal
		mapDigital.put("W238_t", EnumEndpointUbw32RegisterDigital.D6); //Tiefton (Schnellgang, Signum, Totmann)
		mapDigital.put("W238_h", EnumEndpointUbw32RegisterDigital.D4); //Hochton (Langsamgang)

		mapDigital.put("L242b", EnumEndpointUbw32RegisterDigital.E3); //Signumschalter gelb
		mapDigital.put("L242a", EnumEndpointUbw32RegisterDigital.G15); //Signumschalter rot
		mapDigital.put("D94VI.1", EnumEndpointUbw32RegisterDigital.C2); //Vist-LZB.1
		mapDigital.put("D94VI.2", EnumEndpointUbw32RegisterDigital.E7); //Vist-LZB.2
		mapDigital.put("D94VI.3", EnumEndpointUbw32RegisterDigital.E5); //Vist-LZB.3
		mapDigital.put("D94VI.4", EnumEndpointUbw32RegisterDigital.E4); //Vist-LZB.4
		mapDigital.put("D94VI.5", EnumEndpointUbw32RegisterDigital.E2); //Vist-LZB.5
		mapDigital.put("D94VI.6", EnumEndpointUbw32RegisterDigital.G12); //Vist-LZB.6
		mapDigital.put("D94VI.7", EnumEndpointUbw32RegisterDigital.E1); //Vist-LZB.7
		mapDigital.put("D94VI.8", EnumEndpointUbw32RegisterDigital.A7); //Vist-LZB.8

//		mapDigital.put("D94u.1", EnumEndpointUbw32RegisterDigital.D0); //Uhr (hh)
//		mapDigital.put("D94u.2", EnumEndpointUbw32RegisterDigital.D10); //Uhr (mm)
//		mapDigital.put("D94U.3", EnumEndpointUbw32RegisterDigital.D8); //Uhr












		mapDigital.put("AO269", EnumEndpointUbw32RegisterDigital.B1); //Drucksensor Hauptleitung
		mapDigital.put("AO173", EnumEndpointUbw32RegisterDigital.B0); //Drucksensor Bremszylinder
//		mapDigital.put("L281", EnumEndpointUbw32RegisterDigital.C3); //Lampe Schleuderbremse
//		mapDigital.put("L281.1", EnumEndpointUbw32RegisterDigital.D12); //Schleuderbremse von Simulator

		mapDigital.put("L175", EnumEndpointUbw32RegisterDigital.C1); //Lampe Ventilation/ Oelpumpe
		mapDigital.put("L163", EnumEndpointUbw32RegisterDigital.E6); //Lampe Stufenschalter
		mapDigital.put("L181", EnumEndpointUbw32RegisterDigital.G13); //Lampe Abfahrbefehl
		mapDigital.put("L182.3", EnumEndpointUbw32RegisterDigital.A6); //Lampe Türfreigabe links
		mapDigital.put("L185", EnumEndpointUbw32RegisterDigital.G1); //Lampe Tür offen
		mapDigital.put("L182.4", EnumEndpointUbw32RegisterDigital.F0); //Lampe Türfreigabe rechts
		mapDigital.put("L242.2", EnumEndpointUbw32RegisterDigital.E0); //Lampe M-Taste
	}
	
	public void initializeAnalog(){
		mapAnalog.put("8.91.01", EnumEndpointUbw32RegisterAnalog.AN0); //FSS Grundstellung
		mapAnalog.put("8.91.03", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10° F 
		mapAnalog.put("8.91.04", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30° F 
		mapAnalog.put("8.91.05", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45° F 
		mapAnalog.put("8.91.06", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80° F 
		mapAnalog.put("8.91.07", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90° F 
		mapAnalog.put("8.91.19", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10° EG 
		mapAnalog.put("8.91.20", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30° EG 
		mapAnalog.put("8.91.21", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45° EG 
		mapAnalog.put("8.91.22", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80° EG 
		mapAnalog.put("8.91.23", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90° EG 

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

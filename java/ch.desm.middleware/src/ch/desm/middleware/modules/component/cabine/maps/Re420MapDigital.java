package ch.desm.middleware.modules.component.cabine.maps;

import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.store.MessageMapBase;

public class Re420MapDigital extends MessageMapBase {

	public Map<String, EnumEndpointUbw32RegisterDigital> map;
	
	public Re420MapDigital(){
		super();
	}
		
	public boolean isKeyAvailable(String id){
		return map.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return map.containsValue(id);
	}
	
	public Map<String, EnumEndpointUbw32RegisterDigital> getMap(){
		return this.map;
	}
	
	protected void initialize(){
		map.put("S126", EnumEndpointUbw32RegisterDigital.C4); //Steuerstrom
		map.put("S129", EnumEndpointUbw32RegisterDigital.G8); //Stromabnehmer
		map.put("S132", EnumEndpointUbw32RegisterDigital.G9); //Hauptschalter
		map.put("S172.1", EnumEndpointUbw32RegisterDigital.A4); //Kompressor Automat
		map.put("S172.2", EnumEndpointUbw32RegisterDigital.A14); //Kompressor direkt
		map.put("S169", EnumEndpointUbw32RegisterDigital.B8); //Zugsammelschiene
		map.put("S311", EnumEndpointUbw32RegisterDigital.B11); //Beleuchtung Zug
		map.put("S316_1", EnumEndpointUbw32RegisterDigital.A1); //Dienstbeleuchtung 1

		map.put("S140a", EnumEndpointUbw32RegisterDigital.E9); //Wendeschalter 140a vorwärts
		map.put("S140b", EnumEndpointUbw32RegisterDigital.B3); //Wendeschalter 140b rückwärts
		map.put("S150a", EnumEndpointUbw32RegisterDigital.A9); //Fahrschalter 150a
		map.put("S150b", EnumEndpointUbw32RegisterDigital.B9); //Fahrschalter 150b
		map.put("S150d", EnumEndpointUbw32RegisterDigital.B10); //Fahrschalter 150d
		map.put("S150e", EnumEndpointUbw32RegisterDigital.F13); //Fahrschalter 150e
		map.put("S150f", EnumEndpointUbw32RegisterDigital.F12); //Fahrschalter 150f
		map.put("S150g", EnumEndpointUbw32RegisterDigital.B14); //Fahrschalter 150g
		map.put("S150l", EnumEndpointUbw32RegisterDigital.B15); //Fahrschalter 150l
		map.put("S189.1", EnumEndpointUbw32RegisterDigital.G7); //Pfeife Stufe 1
		map.put("S189.2", EnumEndpointUbw32RegisterDigital.A0); //Pfeife Stufe 2
		map.put("S242.01", EnumEndpointUbw32RegisterDigital.A2); //Rückstelltaste Zugsicherung
		map.put("S242.03", EnumEndpointUbw32RegisterDigital.G6); //Rückstelltaste ZUB befreien
		map.put("S242.02", EnumEndpointUbw32RegisterDigital.F5); //M-Taste





		map.put("S281", EnumEndpointUbw32RegisterDigital.F4); //Schleuderschutztaste
		map.put("S182.3", EnumEndpointUbw32RegisterDigital.B5); //Türfreigabe links
		map.put("S182.4", EnumEndpointUbw32RegisterDigital.A10); //Türfreigabe rechts
		map.put("S182", EnumEndpointUbw32RegisterDigital.B4); //Türverriegelung



		map.put("S316.1", EnumEndpointUbw32RegisterDigital.D15); //Stirnlampe links weiss
		map.put("S316.4", EnumEndpointUbw32RegisterDigital.B12); //Stirnlampe links rot
		map.put("S316.2", EnumEndpointUbw32RegisterDigital.F2); //Strinlampe oben weiss
		map.put("S316.5", EnumEndpointUbw32RegisterDigital.B13); //Strinlampe oben rot
		map.put("S316.3", EnumEndpointUbw32RegisterDigital.F8); //Stirnlampe rechts weiss
		map.put("S316.6", EnumEndpointUbw32RegisterDigital.D14); //Stirnlampe rechts rot


		map.put("S235", EnumEndpointUbw32RegisterDigital.E8); //Totmannpedal
		map.put("W238_t", EnumEndpointUbw32RegisterDigital.D6); //Tiefton (Schnellgang, Signum, Totmann)
		map.put("W238_h", EnumEndpointUbw32RegisterDigital.D4); //Hochton (Langsamgang)

		map.put("L242b", EnumEndpointUbw32RegisterDigital.E3); //Signumschalter gelb
		map.put("L242a", EnumEndpointUbw32RegisterDigital.G15); //Signumschalter rot
		map.put("D94VI.1", EnumEndpointUbw32RegisterDigital.C2); //Vist-LZB.1
		map.put("D94VI.2", EnumEndpointUbw32RegisterDigital.E7); //Vist-LZB.2
		map.put("D94VI.3", EnumEndpointUbw32RegisterDigital.E5); //Vist-LZB.3
		map.put("D94VI.4", EnumEndpointUbw32RegisterDigital.E4); //Vist-LZB.4
		map.put("D94VI.5", EnumEndpointUbw32RegisterDigital.E2); //Vist-LZB.5
		map.put("D94VI.6", EnumEndpointUbw32RegisterDigital.G12); //Vist-LZB.6
		map.put("D94VI.7", EnumEndpointUbw32RegisterDigital.E1); //Vist-LZB.7
		map.put("D94VI.8", EnumEndpointUbw32RegisterDigital.A7); //Vist-LZB.8
//		map.put("", EnumEndpointUbw32RegisterDigital.G0); //Vsoll-LZB.1
//		map.put("", EnumEndpointUbw32RegisterDigital.F1); //Vsoll-LZB.2
//		map.put("", EnumEndpointUbw32RegisterDigital.D7); //Vsoll-LZB.3
//		map.put("", EnumEndpointUbw32RegisterDigital.D5); //Vsoll-LZB.4
//		map.put("", EnumEndpointUbw32RegisterDigital.D13); //Vsoll-LZB.5
//		map.put("", EnumEndpointUbw32RegisterDigital.C13); //Vsoll-LZB.6
//		map.put("", EnumEndpointUbw32RegisterDigital.D10); //Vsoll-LZB.7
//		map.put("", EnumEndpointUbw32RegisterDigital.D8); //Vsoll-LZB.8

//		map.put("D94u.1", EnumEndpointUbw32RegisterDigital.); //Uhr (hh)
//		map.put("D94u.2", EnumEndpointUbw32RegisterDigital.); //Uhr (mm)
//		map.put("D94U.3", EnumEndpointUbw32RegisterDigital.); //Uhr














		map.put("L281", EnumEndpointUbw32RegisterDigital.C3); //Lampe Schleuderbremse
		map.put("L281.1", EnumEndpointUbw32RegisterDigital.D12); //Schleuderbremse von Simulator
		map.put("L83", EnumEndpointUbw32RegisterDigital.C14); //Lampe Zugsammelschiene
		map.put("L175", EnumEndpointUbw32RegisterDigital.C1); //Lampe Ventilation/ Oelpumpe
		map.put("L163", EnumEndpointUbw32RegisterDigital.E6); //Lampe Stufenschalter
		map.put("L181", EnumEndpointUbw32RegisterDigital.G13); //Lampe Abfahrbefehl
		map.put("L182.3", EnumEndpointUbw32RegisterDigital.A6); //Lampe Türfreigabe links
		map.put("L185", EnumEndpointUbw32RegisterDigital.G1); //Lampe Tür offen
		map.put("L182.4", EnumEndpointUbw32RegisterDigital.F0); //Lampe Türfreigabe rechts
		map.put("L242.2", EnumEndpointUbw32RegisterDigital.E0); //Lampe M-Taste
	}
	
}

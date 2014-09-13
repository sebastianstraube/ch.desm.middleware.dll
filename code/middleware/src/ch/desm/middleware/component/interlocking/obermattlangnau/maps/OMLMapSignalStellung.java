package ch.desm.middleware.component.interlocking.obermattlangnau.maps;

import java.util.HashMap;
import java.util.Map;

public class OMLMapSignalStellung {

	private Map<String, String> mapSignalStellung;
	
	public OMLMapSignalStellung() {
		this.mapSignalStellung = new HashMap<String, String>();
		
		this.initializeSignalStellung();
	}

	/**
	 * TODO mapping to Locsim DLL data
	 * 
	 * source: MiddlewareMessages.xlsx: Legende
	 * 
	 */
	private void initializeSignalStellung() {
		mapSignalStellung.put("11", "ZSH"); //Signaltyp:Zwergsignal, Bedeutung: Halt, Signalbild :zwei Lampen horizontal
		mapSignalStellung.put("11", "ZSV"); //Signaltyp:Zwergsignal, Bedeutung: Vorsicht, Signalbild :zwei Lampen schr�g
		mapSignalStellung.put("11", "ZSF"); //Signaltyp:Zwergsignal, Bedeutung: freie Fahrt, Signalbild :zwei Lampen vertikal
		mapSignalStellung.put("11", "LH"); //Signaltyp:Hauptsignal Typ L, Bedeutung: Halt, Signalbild :Hauptsignal rot
		mapSignalStellung.put("11", "LF1"); //Signaltyp:Hauptsignal Typ L, Bedeutung: freie Fahrt, Signalbild :Hauptsignal gr�n
		mapSignalStellung.put("11", "LF2"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausf�hrung 40 km/h, Signalbild :Hauptsignal gr�n-gelb
		mapSignalStellung.put("11", "LF3"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausf�hrung 60 km/h, Signalbild :Hauptsignal gr�n-gr�n
		mapSignalStellung.put("11", "LF5"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausf�hrung 90 km/h, Signalbild :Hauptsignal gr�n-gr�n-gr�n
		mapSignalStellung.put("11", "LF6"); //Signaltyp:Hauptsignal Typ L, Bedeutung: Kurze Fahrt, Signalbild :Hauptsignal gelb-gelb
		mapSignalStellung.put("11", "LWStern"); //Signaltyp:Vorsignal Typ L, Bedeutung: Warnung, Signalbild :Vorsignal gelb-gelb
		mapSignalStellung.put("11", "LF1Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: freie Fahrt, Signalbild :Vorsignal gr�n-gr�n
		mapSignalStellung.put("11", "LF2Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ank�ndigung 40 km/h, Signalbild :Vorsignal gelb-gr�n
		mapSignalStellung.put("11", "LF3Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ank�ndigung 60 km/h, Signalbild :Vorsignal gelb-gr�n-gr�n
		mapSignalStellung.put("11", "LF5Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ank�ndigung 90 km/h, Signalbild :Vorsignal gr�n-gr�n-gelb
		mapSignalStellung.put("11", "NH"); //Signaltyp:Signal Typ N, Bedeutung: Halt, Signalbild :rot
		mapSignalStellung.put("11", "NW"); //Signaltyp:Signal Typ N, Bedeutung: Warnung, Signalbild :gelb
		mapSignalStellung.put("11", "NF"); //Signaltyp:Signal Typ N, Bedeutung: freie Fahrt, Signalbild :gr�n
		mapSignalStellung.put("11", "N1"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 10 km/h, Signalbild :Ziffer 1
		mapSignalStellung.put("11", "N2"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 20 km/h, Signalbild :Ziffer 2
		mapSignalStellung.put("11", "N3"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 30 km/h, Signalbild :Ziffer 3
		mapSignalStellung.put("11", "N4"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 40 km/h, Signalbild :Ziffer 4
		mapSignalStellung.put("11", "N5"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 50 km/h, Signalbild :Ziffer 5
		mapSignalStellung.put("11", "N6"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 60 km/h, Signalbild :Ziffer 6
		mapSignalStellung.put("11", "N7"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 70 km/h, Signalbild :Ziffer 7
		mapSignalStellung.put("11", "N8"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 80 km/h, Signalbild :Ziffer 8
		mapSignalStellung.put("11", "N9"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 90 km/h, Signalbild :Ziffer 9
		mapSignalStellung.put("11", "N10"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 100 km/h, Signalbild :Ziffer 10
		mapSignalStellung.put("11", "N11"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 110 km/h, Signalbild :Ziffer 11
		mapSignalStellung.put("11", "N12"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 120 km/h, Signalbild :Ziffer 12
		mapSignalStellung.put("11", "N13"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 130 km/h, Signalbild :Ziffer 13
		mapSignalStellung.put("11", "N14"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 140 km/h, Signalbild :Ziffer 14
		mapSignalStellung.put("11", "N15"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 150 km/h, Signalbild :Ziffer 15
		mapSignalStellung.put("11", "N16"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 160 km/h, Signalbild :Ziffer 16
	}

	/**
	 * find the Stellung from DLL
	 * 
	 * @param dllValue
	 */
	public String getSignalStellung(int dllValue) {
		String middlewareMessageStellung = mapSignalStellung.get(String
				.valueOf(dllValue));

		if (middlewareMessageStellung == null
				|| middlewareMessageStellung.isEmpty()) {
			System.err
					.println("mapping error: find no equivalent signal stellung from locsim dll data to middleware message data, dll signal stellung: "
							+ dllValue);
		}

		return middlewareMessageStellung;
	}
	
}

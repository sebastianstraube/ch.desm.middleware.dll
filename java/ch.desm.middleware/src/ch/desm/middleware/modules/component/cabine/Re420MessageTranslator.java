package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointObjectDllSignal;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointObjectDllTrainPosition;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointObjectDllWeiche;
import ch.desm.middleware.modules.component.simulation.locsim.messages.LocsimMessageDll;

public class Re420MessageTranslator {

	private Map<String, String> map;
	
	public Re420MessageTranslator() {
		this.map = new HashMap<String, String>();
		
		this.initialize();
	}

	/**
	 * TODO mapping to Locsim DLL data
	 * 
	 * source: MiddlewareMessages.xlsx: Legende
	 * 
	 */
	protected void initialize() {
		map.put("11", "ZSH"); //Signaltyp:Zwergsignal, Bedeutung: Halt, Signalbild :zwei Lampen horizontal
		map.put("11", "ZSV"); //Signaltyp:Zwergsignal, Bedeutung: Vorsicht, Signalbild :zwei Lampen schr�g
		map.put("11", "ZSF"); //Signaltyp:Zwergsignal, Bedeutung: freie Fahrt, Signalbild :zwei Lampen vertikal
		map.put("11", "LH"); //Signaltyp:Hauptsignal Typ L, Bedeutung: Halt, Signalbild :Hauptsignal rot
		map.put("11", "LF1"); //Signaltyp:Hauptsignal Typ L, Bedeutung: freie Fahrt, Signalbild :Hauptsignal gr�n
		map.put("11", "LF2"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausf�hrung 40 km/h, Signalbild :Hauptsignal gr�n-gelb
		map.put("11", "LF3"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausf�hrung 60 km/h, Signalbild :Hauptsignal gr�n-gr�n
		map.put("11", "LF5"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausf�hrung 90 km/h, Signalbild :Hauptsignal gr�n-gr�n-gr�n
		map.put("11", "LF6"); //Signaltyp:Hauptsignal Typ L, Bedeutung: Kurze Fahrt, Signalbild :Hauptsignal gelb-gelb
		map.put("11", "LWStern"); //Signaltyp:Vorsignal Typ L, Bedeutung: Warnung, Signalbild :Vorsignal gelb-gelb
		map.put("11", "LF1Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: freie Fahrt, Signalbild :Vorsignal gr�n-gr�n
		map.put("11", "LF2Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ank�ndigung 40 km/h, Signalbild :Vorsignal gelb-gr�n
		map.put("11", "LF3Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ank�ndigung 60 km/h, Signalbild :Vorsignal gelb-gr�n-gr�n
		map.put("11", "LF5Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ank�ndigung 90 km/h, Signalbild :Vorsignal gr�n-gr�n-gelb
		map.put("11", "NH"); //Signaltyp:Signal Typ N, Bedeutung: Halt, Signalbild :rot
		map.put("11", "NW"); //Signaltyp:Signal Typ N, Bedeutung: Warnung, Signalbild :gelb
		map.put("11", "NF"); //Signaltyp:Signal Typ N, Bedeutung: freie Fahrt, Signalbild :gr�n
		map.put("11", "N1"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 10 km/h, Signalbild :Ziffer 1
		map.put("11", "N2"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 20 km/h, Signalbild :Ziffer 2
		map.put("11", "N3"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 30 km/h, Signalbild :Ziffer 3
		map.put("11", "N4"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 40 km/h, Signalbild :Ziffer 4
		map.put("11", "N5"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 50 km/h, Signalbild :Ziffer 5
		map.put("11", "N6"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 60 km/h, Signalbild :Ziffer 6
		map.put("11", "N7"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 70 km/h, Signalbild :Ziffer 7
		map.put("11", "N8"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 80 km/h, Signalbild :Ziffer 8
		map.put("11", "N9"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 90 km/h, Signalbild :Ziffer 9
		map.put("11", "N10"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 100 km/h, Signalbild :Ziffer 10
		map.put("11", "N11"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 110 km/h, Signalbild :Ziffer 11
		map.put("11", "N12"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 120 km/h, Signalbild :Ziffer 12
		map.put("11", "N13"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 130 km/h, Signalbild :Ziffer 13
		map.put("11", "N14"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 140 km/h, Signalbild :Ziffer 14
		map.put("11", "N15"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 150 km/h, Signalbild :Ziffer 15
		map.put("11", "N16"); //Signaltyp:Geschwindigkeitsanzeige N, Bedeutung: 160 km/h, Signalbild :Ziffer 16
	}

	/**
	 * find the Stellung from DLL
	 * 
	 * @param stellung
	 */
	private String getSignalStellungFromDllValue(int stellung) {
		String middlewareMessageStellung = map.get(String
				.valueOf(stellung));

		if (middlewareMessageStellung == null
				|| middlewareMessageStellung.isEmpty()) {
			System.err
					.println("mapping error: find no equivalent signal stellung from locsim dll data to middleware message data, dll signal stellung: "
							+ stellung);
		}

		return middlewareMessageStellung;
	}

	/**
	 * 
	 * @param obj
	 */
	public String translateObjectToMiddlewareMessage(
			EndpointObjectDllTrainPosition obj) {

		String s = LocsimMessageDll.DLL_MESSAGE_GLEISLIST;
		s += "gleislist";
		s += ";";
		s += obj.gleisList.toString();
		s += ";";

		return s;
	}

	/**
	 * 
	 * @param obj
	 */
	public String translateObjectToMiddlewareMessage(EndpointObjectDllSignal obj) {

		String s = LocsimMessageDll.DLL_MESSAGE_SIGNAL;
		s += obj.signalId;
		s += ";";
		s += getSignalStellungFromDllValue(obj.stellung);
		s += ";";

		return s;
	}

	/**
	 * TODO implementation stellung
	 * 
	 * @param obj
	 */
	public String translateObjectToMiddlewareMessage(EndpointObjectDllWeiche obj) {

		String s = LocsimMessageDll.DLL_MESSAGE_WEICHE;
		s += obj.weicheId;
		s += ";";
		s += obj.gleisId;
		s += ";";
		// s += obj.stellung;
		// s+=";";

		return s;
	}
}

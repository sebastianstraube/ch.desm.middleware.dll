package ch.desm.middleware.modules.communication.message.translator;

import java.util.HashMap;
import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectSignal;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectTrainPosition;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectWeiche;
import ch.desm.middleware.modules.communication.message.type.MessageLocsimDll;

public class MessageTranslatorDll extends MessageTranslatorMiddlewareBase {

	private Map<String, String> mapSignalStellung;
	
	public MessageTranslatorDll() {
		this.mapSignalStellung = new HashMap<String, String>();
		
		this.initializeMapSignalStellung();
	}

	/**
	 * TODO mapping to Locsim DLL data
	 * 
	 * source: MiddlewareMessages.xlsx: Legende
	 * 
	 */
	private void initializeMapSignalStellung() {
		mapSignalStellung.put("11", "ZSH"); //Signaltyp:Zwergsignal, Bedeutung: Halt, Signalbild :zwei Lampen horizontal
		mapSignalStellung.put("11", "ZSV"); //Signaltyp:Zwergsignal, Bedeutung: Vorsicht, Signalbild :zwei Lampen schräg
		mapSignalStellung.put("11", "ZSF"); //Signaltyp:Zwergsignal, Bedeutung: freie Fahrt, Signalbild :zwei Lampen vertikal
		mapSignalStellung.put("11", "LH"); //Signaltyp:Hauptsignal Typ L, Bedeutung: Halt, Signalbild :Hauptsignal rot
		mapSignalStellung.put("11", "LF1"); //Signaltyp:Hauptsignal Typ L, Bedeutung: freie Fahrt, Signalbild :Hauptsignal grün
		mapSignalStellung.put("11", "LF2"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausführung 40 km/h, Signalbild :Hauptsignal grün-gelb
		mapSignalStellung.put("11", "LF3"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausführung 60 km/h, Signalbild :Hauptsignal grün-grün
		mapSignalStellung.put("11", "LF5"); //Signaltyp:Hauptsignal Typ L, Bedeutung: V Ausführung 90 km/h, Signalbild :Hauptsignal grün-grün-grün
		mapSignalStellung.put("11", "LF6"); //Signaltyp:Hauptsignal Typ L, Bedeutung: Kurze Fahrt, Signalbild :Hauptsignal gelb-gelb
		mapSignalStellung.put("11", "LWStern"); //Signaltyp:Vorsignal Typ L, Bedeutung: Warnung, Signalbild :Vorsignal gelb-gelb
		mapSignalStellung.put("11", "LF1Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: freie Fahrt, Signalbild :Vorsignal grün-grün
		mapSignalStellung.put("11", "LF2Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ankündigung 40 km/h, Signalbild :Vorsignal gelb-grün
		mapSignalStellung.put("11", "LF3Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ankündigung 60 km/h, Signalbild :Vorsignal gelb-grün-grün
		mapSignalStellung.put("11", "LF5Stern"); //Signaltyp:Vorsignal Typ L, Bedeutung: V Ankündigung 90 km/h, Signalbild :Vorsignal grün-grün-gelb
		mapSignalStellung.put("11", "NH"); //Signaltyp:Signal Typ N, Bedeutung: Halt, Signalbild :rot
		mapSignalStellung.put("11", "NW"); //Signaltyp:Signal Typ N, Bedeutung: Warnung, Signalbild :gelb
		mapSignalStellung.put("11", "NF"); //Signaltyp:Signal Typ N, Bedeutung: freie Fahrt, Signalbild :grün
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
	 * @param stellung
	 */
	private String getSignalStellungFromDllValue(int stellung) {
		String middlewareMessageStellung = mapSignalStellung.get(String
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
			EndpointDllObjectTrainPosition obj) {

		String s = MessageLocsimDll.DLL_MESSAGE_GLEISLIST;
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
	public String translateObjectToMiddlewareMessage(EndpointDllObjectSignal obj) {

		String s = MessageLocsimDll.DLL_MESSAGE_SIGNAL;
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
	public String translateObjectToMiddlewareMessage(EndpointDllObjectWeiche obj) {

		String s = MessageLocsimDll.DLL_MESSAGE_WEICHE;
		s += obj.weicheId;
		s += ";";
		s += obj.gleisId;
		s += ";";
		// s += obj.stellung;
		// s+=";";

		return s;
	}
}

package ch.desm.middleware.modules.component.interlocking;

import java.util.HashMap;
import java.util.Map;

public class ObermattLangnauConfiguration {

	//TODO refactoring
	public static final String CONFIGURATION = "14784,199,65505,16383,64528,52939,64575";
	public Map<String, String> map;
	
	public ObermattLangnauConfiguration(){
		map = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public boolean isKeyAvailable(String id){
		return map.containsKey(id);
	}
	
	public void initialize(){
		map.put("1.90.01","C4"); //Störungslampe Einfahrvorsignal F*
		map.put("1.90.02","G6"); //Einfahrsignal F; Notrot
		map.put("1.90.02","G6"); //Einfahrsignal F; Grün FB 3
		map.put("1.90.04","G8"); //Einfahrsignal F; Rot
		map.put("1.90.05","C3"); //Einfahrsignal F; Grün FB1
		map.put("1.90.06","G9"); //Einfahrsignal E; Grün FB1
		map.put("1.90.07","A0"); //Einfahrsignal E; Rot
		map.put("1.90.08","E8"); //Einfahrsignal E; Notrot
		map.put("1.90.09","E9"); //Einfahrsignal G; Grün FB1
		map.put("1.90.10","A15"); //Einfahrsignal G; Rot
		map.put("1.90.11","B4"); //Einfahrsignal G; Grün FB3
		map.put("1.90.12","B3"); //Einfahrsignal D EMM; Rot
		map.put("1.90.13",""); //Einfahrsignal D EMM; Grün
		map.put("1.90.14","B9"); //Ausfahrsignal C EMM; Grün
		map.put("1.90.15","B10"); //Ausfahrsingal C EMM; Rot
		map.put("1.90.16","B11"); //Störungslampe Einfahrvorsignal G*
		map.put("1.91.21",""); //Fahrtrichtung verlangt von Zollbrück
		map.put("1.91.22","F13"); //Rückmelden möglich nach Zollbrück
		map.put("1.90.31","F12"); //Block von Langnau, rot
		map.put("1.90.32","B12"); //Block von Langnau, weiss
		map.put("1.90.33","B13"); //Block nach Langnau, weiss
		map.put("1.90.34","B14"); //Block nach Langnau, rot
		map.put("1.90.35","B15"); //Isolierung egf
		map.put("1.90.36","D14"); //Isolierung 1
		map.put("1.90.37","F5"); //Fahrstrasse ef
		map.put("1.90.38","F2"); //Isolierung ef
		map.put("1.90.39","F8"); //Isolierung cd
		map.put("1.90.40","A2"); //Fahrstrasse gf
		map.put("1.90.41","A3"); //Block nach Zollbrück, rot
		map.put("1.90.42","A4"); //Block nach Zollbrück, weiss
		map.put("1.90.43","A5"); //Block von Zollbrück, weiss
		map.put("1.90.44","A14"); //Block von Zollbrück, rot
		map.put("1.91.01",""); //Rückmelden möglich nach Langnau
		map.put("1.91.02",""); //Fahrtrichtung verlangt von Langnau
		map.put("1.91.03",""); //Störung, Wecker abschalten
		map.put("1.01.01",""); //WS1 Freigabelampe
		map.put("1.01.02",""); //WS1 Überwachungslampe
		map.put("1.04.01",""); //FSS Sperrenlampe
		map.put("1.04.02",""); //FSS Kuppelstromlampe
		map.put("2.92.01","A9"); //Weichenwecker
		map.put("2.92.02","A10"); //Blockwecker
		map.put("2.90.01","D15"); //Signalwecker
		map.put("9.99.04",""); //Abläuten von Emmenmatt
		map.put("9.99.05",""); //Abläuten von Langnau
		map.put("9.99.06",""); //Abläuten von Zollbrück
		map.put("9.99.07",""); //Abläuten nach Emmenmatt
		map.put("9.99.08",""); //Abläuten nach Langnau
		map.put("9.99.09",""); //Abläuten nach Zollbrück
		map.put("9.99.10",""); //Stellstrom
		map.put("9.99.11",""); //Überwachungsstrom
		map.put("9.99.12",""); //Gleisisolierung
		map.put("3.01.01","F4"); //WS1 Freigabemagnet
		map.put("3.04.01","E6"); //FSS Sperrmagnet
		map.put("3.04.02","E5"); //FSS Kuppelstrommagnet
		map.put("4.01.01",""); //WS1 Relais
		map.put("5.01.01",""); //WS1 Stützer
		map.put("5.04.01",""); //FSS Stützer
		map.put("9.93.01",""); //Weichenbeleuchtung
		map.put("9.93.02",""); //Weichenantrieb
		map.put("6.90.01",""); //Anforderung für Durchfahrt nach Emmenmatt
		map.put("6.90.01",""); //Anforderung für Durchfahrt nach Emmenmatt
		map.put("6.91.02",""); //Freie Bahn anfordern nach Zollbrück
		map.put("6.91.01",""); //Freie Bahn festhalten nach Zollbrück
		map.put("6.91.03",""); //Rückmelden nach Zollbrück
		map.put("6.91.04",""); //Rückmelden nach Langnau
		map.put("6.91.05",""); //Freie Bahn anfordern nach Langnau
		map.put("6.91.06",""); //Freie Bahn festhalten nach Langnau
		map.put("6.91.07",""); //Blockumgehung EG
		map.put("6.91.08",""); //Richtung Emmenmatt
		map.put("6.91.09",""); //Wecker abschalten
		map.put("6.91.10",""); //Nottaste W1
		map.put("6.91.11",""); //Weichenbeleuchtung aus/ein
		map.put("6.91.12",""); //Zeitschalter-Umgehung Ein/Norm.
		map.put("6.91.13",""); //Notauflösung
		map.put("6.91.14",""); //Richtung Zollbrück
		map.put("6.91.15",""); //Isolierumgehung für Signalfahrtstellung
		map.put("7.91.01",""); //WS1 Grundstellung +
		map.put("7.91.02",""); //WS1 gedrückt in + Lage
		map.put("7.91.03",""); //WS1 aus + Lage gedreht
		map.put("7.91.04",""); //WS1 in Grundstellung - (45° Lage)
		map.put("7.91.06",""); //WS1 gedrück in - Lage
		map.put("7.91.07",""); //WS1 aus - Lage gedreht
		map.put("8.91.01",""); //FSS Grundstellung
		map.put("8.91.02",""); //FSS Grundstellung gedrückt
		map.put("8.91.03",""); //FSS 10° F Richtung Emmenmatt
		map.put("8.91.04",""); //FSS 30° F Richtung Emmenmatt
		map.put("8.91.05",""); //FSS 45° F Richtung Emmenmatt
		map.put("8.91.06",""); //FSS 80° F Richtung Emmenmatt
		map.put("8.91.07",""); //FSS 90° F Richtung Emmenmatt
		map.put("8.91.08",""); //FSS von 90° auf 45° Richtung Emmenmatt
		map.put("8.91.09",""); //FSS von 45° auf 10° Richtung Emmenmatt
		map.put("8.91.10",""); //FSS von 10° in Grundstellung Richtung Emmenmatt
		map.put("8.91.11",""); //FSS 10° F Richtung Zollbrück
		map.put("8.91.12",""); //FSS 30° F Richtung Zollbrück
		map.put("8.91.13",""); //FSS 45° F Richtung Zollbrück
		map.put("8.91.14",""); //FSS 80° F Richtung Zollbrück
		map.put("8.91.15",""); //FSS 90° F Richtung Zollbrück
		map.put("8.91.16",""); //FSS von 90° auf 45° Richtung Zollbrück
		map.put("8.91.17",""); //FSS von 45° auf 10° Richtung Zollbrück
		map.put("8.91.18",""); //FSS von 10° in Grundstellung Richtung Zollbrück
		map.put("8.91.19",""); //FSS 10° EG aus Richtung Emmenmatt
		map.put("8.91.20",""); //FSS 30° EG aus Richtung Emmenmatt
		map.put("8.91.21",""); //FSS 45° EG aus Richtung Emmenmatt
		map.put("8.91.22",""); //FSS 80° EG aus Richtung Emmenmatt
		map.put("8.91.23",""); //FSS 90° EG aus Richtung Emmenmatt
		map.put("8.91.24",""); //FSS von 90° auf 45° aus Richtung Emmenmatt
		map.put("8.91.25",""); //FSS von 45° auf 10° aus Richtung Emmenmatt
		map.put("8.91.26",""); //FSS von 10° in Grundstellung aus Richtung Emmenmatt
		map.put("8.91.27",""); //FSS 10° EG aus Richtung Zollbrück
		map.put("8.91.28",""); //FSS 30° EG aus Richtung Zollbrück
		map.put("8.91.29",""); //FSS 45° EG aus Richtung Zollbrück
		map.put("8.91.30",""); //FSS 80° EG aus Richtung Zollbrück
		map.put("8.91.31",""); //FSS 90° EG aus Richtung Zollbrück
		map.put("8.91.32",""); //FSS von 90° auf 45° aus Richtung Zollbrück
		map.put("8.91.33",""); //FSS von 45° auf 10° aus Richtung Zollbrück
		map.put("8.91.34",""); //FSS von 10° in Grundstellung aus Richtung Zollbrück

		
		//Externe Vorgänge (Einflüsse) 
		map.put("10.99.01",""); //Isolierung egf belegt
		map.put("10.99.02",""); //Isolierung egf direkt nach Isolierung 1 belegt
		map.put("10.99.03",""); //Isolierung 1 belegt
		map.put("10.99.04",""); //Isolierung 1 belegt
		map.put("10.99.05",""); //Isolierung 1 direkt nach Isolierung ef belegt
		map.put("10.99.06",""); //Isolierung 1 direkt nach Isolierung egf belegt
		map.put("10.99.07",""); //Isolierung ef belegt
		map.put("10.99.08",""); //Isolierung cd belegt
		map.put("10.99.09",""); //Freigabe Isolierung 1 
		map.put("10.99.10",""); //Freigabe Isolierung egf
		map.put("11.99.01",""); //Freie Bahn verlangen von Langnau
		map.put("11.99.02",""); //Freie Bahn zustimmen nach Langnau
		map.put("11.99.03",""); //Freie Bahn festhalten von Langnau
		map.put("11.99.04",""); //Rückmelden von Langnau
		map.put("11.99.05",""); //Freie Bahn verlangen von Zollbrück
		map.put("11.99.06",""); //Freie Bahn zustimmen nach Zollbrück
		map.put("11.99.07",""); //Freie Bahn festhalten von Zollbrück
		map.put("11.99.08",""); //Rückmelden von Zollbrück
		map.put("9.99.13",""); //Anfordern von Emmenmatt
		map.put("9.99.14",""); //Ausfahrt von Emmenmatt
		map.put("9.99.15",""); //Zug eingefahren im Emmenmatt
		map.put("11.99.09",""); //Vorblocken von Langnau
		map.put("11.99.10",""); //Vorblocken von Zollbrück
		map.put("9.99.01",""); //Abläuten von Emmenmatt (Zug abgefahren)
		map.put("9.99.02",""); //Abläuten von Langnau (Zug abgefahren)
		map.put("9.99.03",""); //Abläuten von Zollbrück (Zug abgefahren)
		map.put("6.99.01",""); //Abläuten nach Emmenmatt
		map.put("6.99.02",""); //Abläuten nach Langnau
		map.put("6.99.03",""); //Abläuten nach Zollbrück
		map.put("12.99.01",""); //Weichenüberwachung 1+
		map.put("12.99.02",""); //Weichenüberwachung 1 -
		map.put("12.99.03",""); //Überwachungssicherung defekt/aus
		map.put("12.99.04",""); //Stellstromsicherung defekt/aus
		map.put("90.99.01",""); //Signallampe defekt; F* Warnung
		map.put("90.99.02",""); //Signallampe defekt; F* Fahrt
		map.put("90.99.03",""); //Signallampe defekt; F notrot
		map.put("90.99.04",""); //Signallampe defekt; F grün FB3
		map.put("90.99.05",""); //Signallampe defekt; F rot
		map.put("90.99.06",""); //Signallampe defekt; F grün FB 1
		map.put("90.99.07",""); //Signallampe defekt; E grün
		map.put("90.99.08",""); //Signallampe defekt; E rot
		map.put("90.99.09",""); //Signallampe defekt; E notrot
		map.put("90.99.10",""); //Signallampe defekt; D rot
		map.put("90.99.11",""); //Signallampe defekt; D grün
		map.put("90.99.12",""); //Signallampe defekt; C grün
		map.put("90.99.13",""); //Signallampe defekt; C rot 
		map.put("90.99.14",""); //Signallampe defekt; G* Warnung
		map.put("90.99.15",""); //Signallampe defekt; G* Fahrt
		map.put("90.99.16",""); //Signallampe defekt; G grün FB 1
		map.put("90.99.17",""); //Signallampe defekt; G rot
		map.put("90.99.18",""); //Signallampe defekt; G Reserve-rot
		map.put("90.99.19",""); //Signallampe defekt; G grün FB3
	}
	

}

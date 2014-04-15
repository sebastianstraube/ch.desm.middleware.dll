package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

public class Re420Ubw32Configuration {

	// TODO refactoring
	public static final String CONFIGURATION = "14784,199,65505,16383,64528,52939,64575";
	public Map<String, String> map;

	public Re420Ubw32Configuration() {
		map = new HashMap<String, String>();

		this.initialize();
	}

	public void initialize() {
		map.put("1.90.01", "C4"); // Störungslampe Einfahrvorsignal F*
		map.put("1.90.02", "G6"); // Einfahrsignal F; Notrot
		map.put("1.90.03", "G7"); // Einfahrsignal F; Grün FB 3
		map.put("1.90.04", "G8"); // Einfahrsignal F; Rot
		map.put("1.90.05", "C3"); // Einfahrsignal F; Grün FB1
		map.put("1.90.06", "G9"); // Einfahrsignal E; Grün FB1
		map.put("1.90.07", "A0"); // Einfahrsignal E; Rot
		map.put("1.90.08", "E8"); // Einfahrsignal E; Notrot
		map.put("1.90.09", "E9"); // Einfahrsignal G; Grün FB1
		map.put("1.90.10", "A15"); // Einfahrsignal G; Rot
		map.put("1.90.11", "B4"); // Einfahrsignal G; Grün FB3
		map.put("1.90.12", "B3"); // Einfahrsignal D EMM; Rot
		map.put("1.90.14", "B9"); // Ausfahrsignal C EMM; Grün
		map.put("1.90.15", "B10"); // Ausfahrsingal C EMM; Rot
		map.put("1.90.16", "B11"); // Störungslampe Einfahrvorsignal G*
		map.put("1.91.22", "F13"); // Rückmelden möglich nach Zollbrück
		map.put("1.90.31", "F12"); // Block von Langnau, rot
		map.put("1.90.32", "B12"); // Block von Langnau, weiss
		map.put("1.90.33", "B13"); // Block nach Langnau, weiss
		map.put("1.90.34", "B14"); // Block nach Langnau, rot
		map.put("1.90.35", "B15"); // Isolierung egf
		map.put("1.90.36", "D14"); // Isolierung 1
		map.put("1.90.37", "F5"); // Fahrstrasse ef
		map.put("1.90.38", "F2"); // Isolierung ef
		map.put("1.90.39", "F8"); // Isolierung cd
		map.put("1.90.40", "A2"); // Fahrstrasse gf
		map.put("1.90.41", "A3"); // Block nach Zollbrück, rot
		map.put("1.90.42", "A4"); // Block nach Zollbrück, weiss
		map.put("1.90.43", "A5"); // Block von Zollbrück, weiss
		map.put("1.90.44", "A14"); // Block von Zollbrück, rot
		map.put("1.90.01", "C4"); // Rückmelden möglich nach Langnau
		map.put("1.91.02", "G6"); // Fahrtrichtung verlangt von Langnau
		map.put("2.92.01", "A9"); // Weichenwecker
		map.put("2.92.02", "A10"); // Blockwecker
		map.put("2.90.01", "D15"); // Signalwecker
		map.put("3.01.01", "F4"); // WS1 Freigabemagnet
		map.put("3.04.01", "E6"); // FSS Sperrmagnet
		map.put("3.04.02", "E5"); // FSS Kuppelstrommagnet
		map.put("6.91.02", "D13"); // Freie Bahn anfordern nach Zollbrück
		map.put("6.91.01", "D12"); // Freie Bahn festhalten nach Zollbrück
		map.put("6.91.03", "D4"); // Rückmelden nach Zollbrück
		map.put("6.91.04", "D5"); // Rückmelden nach Langnau
		map.put("6.91.05", "D6"); // Freie Bahn anfordern nach Langnau
		map.put("6.91.06", "D7"); // Freie Bahn festhalten nach Langnau
		map.put("6.91.07", "F0"); // Blockumgehung EG
		map.put("6.91.08", "D8"); // Richtung Emmenmatt
		map.put("6.91.10", "G1"); // Nottaste W1
		map.put("6.91.13", "D9"); // Notauflösung
		map.put("6.91.14", "A6"); // Richtung Zollbrück
		map.put("6.91.15", "A7"); // Isolierumgehung für Signalfahrtstellung
		map.put("7.91.01", "D10"); // WS1 Grundstellung +
		map.put("7.91.02", "D11"); // WS1 gedrückt in + Lage
		map.put("7.91.04", "D13"); // WS1 in Grundstellung - (45° Lage)
		map.put("8.91.01", "B0"); // FSS Grundstellung
		map.put("8.91.03", "B0"); // FSS 10° F
		map.put("8.91.04", "B0"); // FSS 30° F
		map.put("8.91.05", "B0"); // FSS 45° F
		map.put("8.91.06", "B0"); // FSS 80° F
		map.put("8.91.07", "B0"); // FSS 90° F
		map.put("8.91.19", "B0"); // FSS 10° EG
		map.put("8.91.20", "B0"); // FSS 30° EG
		map.put("8.91.21", "B0"); // FSS 45° EG
		map.put("8.91.22", "B0"); // FSS 80° EG
		map.put("8.91.23", "B0"); // FSS 90° EG
	}

	public boolean isKeyAvailable(String id) {
		return map.containsKey(id);
	}

	public boolean isValueAvailable(String value) {
		return map.containsValue(value);
	}
}

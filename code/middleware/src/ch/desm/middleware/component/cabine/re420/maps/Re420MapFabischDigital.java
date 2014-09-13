package ch.desm.middleware.component.cabine.re420.maps;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;

public class Re420MapFabischDigital extends ComponentMapBase {

	public Re420MapFabischDigital() {
		super();
	}
	
	public boolean isKeyAvailable(String id) {
		return map.containsKey(id);
	}

	public boolean isValueAvailable(String id) {
		return map.containsValue(id);
	}

	@Override
	public Map<String, String> getMap() {
		return map;
	}

	/**
	 *	
	 */
	protected void initialize() {

		map.put("w238_t","5d5c");//Tiefton (Schnellgang, Signum, Totmann)
		map.put("w238_h","5d5c");//Hochton (Langsamgang)
//		map.put("cabine.zusi.hauptsignalueberfahren","5dc0");//cabine.zusi.hauptsignalueberfahren
//		map.put("cabine.zusi.nachvorgesorgt","5dc0");//cabine.zusi.nachvorgesorgt
//		map.put("cabine.zusi.m","5dc0");//cabine.zusi.m
//		map.put("cabine.zusi.quittiert","5dc0");//cabine.zusi.quittiert
//		map.put("cabine.zusi.vorgesorgt","5dc0");//cabine.zusi.vorgesorgt
//		map.put("cabine.zusi.vorsignalwarnung","5dc0");//cabine.zusi.vorsignalwarnung
		map.put("l281","23f0");//Lampe Schleuderbremse
		map.put("l83","4236");//Lampe Zugsammelschiene
		map.put("l175","445c");//Lampe Ventilation/ Oelpumpe
		map.put("l163","3fac");//Lampe Stufenschalter
		map.put("l181","46b4");//Lampe Abfahrbefehl
		map.put("l182.3","4791");//Lampe Türfreigabe links
		map.put("l185","4844");//Lampe Tür offen gelb
		map.put("l185","4844");//Lampe Tür offen
		map.put("l182.4","4787");//Lampe Türfreigabe rechts
//		map.put("l242.2","");//Lampe M-Taste
//		map.put("l281.1","");//Schleuderbremse von Simulator

	}

}

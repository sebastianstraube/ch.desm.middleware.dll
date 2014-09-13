package ch.desm.middleware.component.cabine.re420.maps;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;

public class Re420MapFabischValue extends ComponentMapBase {

	public Re420MapFabischValue() {
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
	 *	if has no mapping then is analog
	 *	if has more the on mapping: 01#02 then it is a switch and has 2 states
	 *	if has only one value then it is no switch with a state
	 */
	protected void initialize() {

		map.put("5d5c","01#03");//Tiefton (Schnellgang, Signum, Totmann)
		map.put("5d5c","01#02");//Hochton (Langsamgang)
		map.put("23f0","01#02");//Lampe Schleuderbremse
		map.put("4236","01#02");//Lampe Zugsammelschiene
		map.put("445c","01#02");//Lampe Ventilation/ Oelpumpe
		map.put("3fac","01#02");//Lampe Stufenschalter
		map.put("46b4","01#02");//Lampe Abfahrbefehl
		map.put("4791","01#02");//Lampe Türfreigabe links
		map.put("4844","02#03");//Lampe Tür offen
		map.put("4787","01#02");//Lampe Türfreigabe rechts
//		map.put("","");//Lampe M-Taste
//		map.put("","");//Schleuderbremse von Simulator
	}

}

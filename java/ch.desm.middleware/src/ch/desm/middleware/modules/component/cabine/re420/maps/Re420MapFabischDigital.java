package ch.desm.middleware.modules.component.cabine.re420.maps;

import java.util.Map;

import ch.desm.middleware.modules.component.ComponentMapBase;

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

	protected void initialize() {

		map.put("s126", "C4"); //Steuerstrom
		map.put("s129", "G8"); //Stromabnehmer
		map.put("s132", "G9"); //Hauptschalter



	}

}
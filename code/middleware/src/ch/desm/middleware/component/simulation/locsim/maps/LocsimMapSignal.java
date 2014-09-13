package ch.desm.middleware.component.simulation.locsim.maps;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;


public class LocsimMapSignal extends ComponentMapBase{

	@Override
	public Map<String, String> getMap() {
		return map;
	}
	
	@Override
	protected void initialize(){
	}
}

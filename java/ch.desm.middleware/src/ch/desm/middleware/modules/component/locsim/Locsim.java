package ch.desm.middleware.modules.component.locsim;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.component.ComponentAbstract;


public class Locsim extends ComponentAbstract{
	
	public Locsim(){
		
	}

	@Override
	public String getType() {
		return enumComponentType.SIMULATION.name();
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.INTERLOCKING.name());
	}

}

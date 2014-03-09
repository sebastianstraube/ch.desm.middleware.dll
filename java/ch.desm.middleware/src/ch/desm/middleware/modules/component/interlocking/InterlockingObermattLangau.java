package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.component.ComponentAbstract;


public class InterlockingObermattLangau extends ComponentAbstract{
	
	public InterlockingObermattLangau(){
		
	}

	@Override
	public String getType() {
		return enumComponentType.INTERLOCKING.name();
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.SIMULATION.name());
	}

}

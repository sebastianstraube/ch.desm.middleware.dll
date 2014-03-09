package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.ModulesCommonAbstract;

	

public abstract class ComponentAbstract extends ModulesCommonAbstract implements ComponentsBridge {

	public static enum enumComponentType{
		SIMULATION, INTERLOCKING, CABINE
	}
	
	public ComponentAbstract(){
		
	}
}

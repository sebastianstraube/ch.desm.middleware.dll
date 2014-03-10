package ch.desm.middleware.modules.service;

import java.util.List;

import ch.desm.middleware.modules.component.ComponentAbstract;
import ch.desm.middleware.modules.component.ComponentHandler;

public class ComponentHandlerService {

	ComponentHandler componentHandler;
	
	public ComponentHandlerService(){
		this.componentHandler = new ComponentHandler();
	}
	
	public List<ComponentAbstract> getComponentList(){
		return componentHandler.getComponentList();
	}
}

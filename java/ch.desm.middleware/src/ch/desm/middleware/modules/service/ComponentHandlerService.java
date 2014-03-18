package ch.desm.middleware.modules.service;

import java.util.List;

import ch.desm.middleware.modules.component.ComponentBase;
import ch.desm.middleware.modules.component.ComponentHandler;

/**
 * 
 * @author Sebastian
 *
 */
public class ComponentHandlerService {

	ComponentHandler componentHandler;
	
	public ComponentHandlerService(){
		this.componentHandler = new ComponentHandler();
	}
	
	public List<ComponentBase> getComponentList(){
		return componentHandler.getComponentList();
	}
}

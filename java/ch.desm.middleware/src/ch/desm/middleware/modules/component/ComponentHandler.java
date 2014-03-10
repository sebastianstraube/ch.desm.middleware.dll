package ch.desm.middleware.modules.component;

import java.util.ArrayList;
import java.util.List;

public class ComponentHandler {

	List<ComponentAbstract> componentList;
	
	public ComponentHandler(){
		this.componentList = new ArrayList<ComponentAbstract>();
	}
	
	public void addComponent(ComponentAbstract component){		
		if(isComponentListCompatible(componentList)){
			this.componentList.add(component);
		}else{
			try {
				throw new Exception("The component list is incompatible and will not setted. Please change to compatible components.");
			} catch (Exception e) {
				this.componentList.remove(component);
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void setComponentList(ArrayList<ComponentAbstract> componentList){
		if(isComponentListCompatible(componentList)){
			this.componentList = componentList;
		}else{
			try {
				throw new Exception("The component list is incompatible and will not setted. Please change to compatible components.");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public List<ComponentAbstract> getComponentList(){
		return componentList;
	}
	
	private boolean isComponentListCompatible(List<ComponentAbstract> componentList){
		
		boolean isRequiredTypeExisting = false;
		String missingComponentType = "";
		
		for(ComponentAbstract component : componentList){
			for(String requiredComponentType : component.getRequiredTypes()){
				if(component.getType().equals(requiredComponentType)){
					isRequiredTypeExisting = true;
					break;
				}
			}
			
			if(!isRequiredTypeExisting){
				missingComponentType = component.getType();
				break;
			}
		}
		
		if(!isRequiredTypeExisting){
			try {
				throw new Exception("The list of components is incompatible, a required compenent type is missing: " + missingComponentType +" .");
			} catch (Exception e) {
				System.err.println(e.getMessage());
				
				return false;
			}
		}
		
		return isRequiredTypeExisting;
	}
	
	
}

package ch.desm.middleware.modules.component.cabine.re420.maps;

import java.util.Map;

import ch.desm.middleware.modules.component.ComponentMapBase;

public class Re420MapUbw32Analog extends ComponentMapBase {

	//B0 = AN0, B1 = AN1
	//11bin = 3dez 
	public static final String PINBITMASK_INPUT_ANALOG = "3";
	
	public Re420MapUbw32Analog(){
		super();
	}
		
	public boolean isKeyAvailable(String id){
		return map.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return map.containsValue(id);
	}
	
	@Override
	public Map<String, String> getMap(){
		return this.map;
	}
	
	public void initialize(){
		map.put("ao269", "B1"); //Drucksensor Hauptleitung
		map.put("ao173", "B0"); //Drucksensor Bremszylinder
	}
}

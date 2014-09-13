package ch.desm.middleware.component.cabine.re420.maps;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;

public class Re420MapFabischAnalog extends ComponentMapBase {

	public Re420MapFabischAnalog(){
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
	
	/**
	 * 
	 */
	public void initialize(){
		map.put("a74","1ce8");//kV-Anzeige
		map.put("a79","1edc");//A-Anzeige Motorenstrom
		map.put("a79.1","1ee6");//A-Anzeige Differenzstrom
		map.put("d94vi","24e0");//Vist-LZB
//		map.put("d94vi.2","");//Vist-LZB.2
//		map.put("d94vi.3","");//Vist-LZB.3
//		map.put("d94vi.4","");//Vist-LZB.4
//		map.put("d94vi.5","");//Vist-LZB.5
//		map.put("d94vi.6","");//Vist-LZB.6
//		map.put("d94vi.7","");//Vist-LZB.7
//		map.put("d94vi.8","");//Vist-LZB.8
//		map.put("d94u.1","");//Uhr (hh)
//		map.put("d94u.2","");//Uhr (mm)
//		map.put("d94u.3","");//Uhr
		
		
	}
}

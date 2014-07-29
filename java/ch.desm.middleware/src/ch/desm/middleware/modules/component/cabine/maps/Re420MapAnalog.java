package ch.desm.middleware.modules.component.cabine.maps;

import java.util.Map;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.message.store.MessageMapBase;

public class Re420MapAnalog extends MessageMapBase {

	public static final String PINBITMASK_INPUT_ANALOG = "0";
	public Map<String, EnumEndpointUbw32RegisterAnalog> map;
	
	public Re420MapAnalog(){
		super();
	}
		
	public boolean isKeyAvailable(String id){
		return map.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return map.containsValue(id);
	}
	
	public Map<String, EnumEndpointUbw32RegisterAnalog> getMap(){
		return this.map;
	}
	
	public void initialize(){
		map.put("8.91.01", EnumEndpointUbw32RegisterAnalog.AN0); //FSS Grundstellung
		map.put("8.91.03", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10� F 
		map.put("8.91.04", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30� F 
		map.put("8.91.05", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45� F 
		map.put("8.91.06", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80� F 
		map.put("8.91.07", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90� F 
		map.put("8.91.19", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10� EG 
		map.put("8.91.20", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30� EG 
		map.put("8.91.21", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45� EG 
		map.put("8.91.22", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80� EG 
		map.put("8.91.23", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90� EG 

	}
}

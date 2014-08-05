package ch.desm.middleware.modules.component.interlocking.obermattlangnau;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32RegisterBase.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32RegisterBase.EnumEndpointUbw32RegisterDigital;

public class OMLEndpointUbw32Data {

	private static Logger log = Logger.getLogger(OMLEndpointUbw32Data.class);
	
	//TODO refactoring
	public static final String PINBITMASK_CONFIGURATION_DIGITAL = "192,0,0,24560,96,1,2";
	public static final String PINBITMASK_INPUT_ANALOG = "1";
	
	private static Map<String, EnumEndpointUbw32RegisterDigital> mapDigital;
	private static Map<String, EnumEndpointUbw32RegisterAnalog> mapAnalog;
	
	public OMLEndpointUbw32Data(){
		mapDigital = new HashMap<String, EnumEndpointUbw32RegisterDigital>();
		mapAnalog = new HashMap<String, EnumEndpointUbw32RegisterAnalog>();
		
		this.initializeDigital();
	}
	
	public Map<String, EnumEndpointUbw32RegisterDigital> getMapInputDigital(){
		return mapDigital;
	}
	
	public Map<String, EnumEndpointUbw32RegisterAnalog> getMapInputAnalog(){
		return mapAnalog;
	}
	
	public boolean isKeyAvailable(String id){
		return mapDigital.containsKey(id);
	}
	
	public boolean isValueAvailable(String id){
		return mapDigital.containsValue(id);
	}
	
	private void initializeDigital(){
		mapDigital.put("1.90.02", EnumEndpointUbw32RegisterDigital.E0); //St�rungslampe Einfahrvorsignal F*

	
	}
	
	public void initializeAnalog(){
		mapAnalog.put("8.91.01", EnumEndpointUbw32RegisterAnalog.AN0); //FSS Grundstellung
		mapAnalog.put("8.91.03", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10� F 
		mapAnalog.put("8.91.04", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30� F 
		mapAnalog.put("8.91.05", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45� F 
		mapAnalog.put("8.91.06", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80� F 
		mapAnalog.put("8.91.07", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90� F 
		mapAnalog.put("8.91.19", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 10� EG 
		mapAnalog.put("8.91.20", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 30� EG 
		mapAnalog.put("8.91.21", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 45� EG 
		mapAnalog.put("8.91.22", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 80� EG 
		mapAnalog.put("8.91.23", EnumEndpointUbw32RegisterAnalog.AN0); //FSS 90� EG 

	}
	
	/**
	 * TODO implementation for multi messaging analog
	 * @param register
	 */
	public String getGlobalIdFSS(EnumEndpointUbw32RegisterAnalog register, int value){
		
		String globalId = "";
		
		//Quelle: Verdrahtungsliste - Analog Input Definition
		switch(register){
			case AN0: 	if(value > 235 && value < 246){globalId = "8.91.07";} else //FSS 90� F
						if(value > 296 && value < 308){globalId = "8.91.06";} else //FSS 80� F
						if(value > 349 && value < 362){globalId = "8.91.05";} else //FSS 45� F
						if(value > 390 && value < 402){globalId = "8.91.04";} else //FSS 30� F
						if(value > 417 && value < 429){globalId = "8.91.03";} else //FSS 10� F
						if(value > 461 && value < 474){globalId = "8.91.01";} else //FSS Grundstellung
						if(value > 496 && value < 513){globalId = "8.91.19";} else //FSS 10� EG
						if(value > 522 && value < 534){globalId = "8.91.20";} else //FSS 30� EG
						if(value > 570 && value < 582){globalId = "8.91.21";} else //FSS 45� EG
						if(value > 637 && value < 654){globalId = "8.91.22";} else //FSS 80� EG
						if(value > 667 && value < 682){globalId = "8.91.23";} else //FSS 90� EG 
						{
							log.warn("the register " + register.name() + " has no mapped value: " + value);
						}
			default: log.error("no mapping defined for analog register:" + register.name());
		}
		
		return globalId;
	}
}
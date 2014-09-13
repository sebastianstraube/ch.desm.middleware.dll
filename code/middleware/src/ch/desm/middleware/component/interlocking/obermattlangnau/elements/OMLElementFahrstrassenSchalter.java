package ch.desm.middleware.component.interlocking.obermattlangnau.elements;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.message.MessageCommon;
import ch.desm.middleware.component.interlocking.obermattlangnau.maps.OMLMapFahrstrassenSchalter;

//Register = AN, Pin = 0
public class OMLElementFahrstrassenSchalter {

	private static Logger log = Logger.getLogger(OMLElementFahrstrassenSchalter.class);
	
	private OMLMapFahrstrassenSchalter map;
	
	public OMLElementFahrstrassenSchalter(){
		map = new OMLMapFahrstrassenSchalter();
	}
	
	public String getglobalId(int value){
		
		//source: Verdrahtungsliste - Analog Input Definition
		if(value > 235 && value < 246){return map.getValue("8.91.07");} else //FSS 90� F
		if(value > 296 && value < 308){return map.getValue("8.91.06");} else //FSS 80� F
		if(value > 349 && value < 362){return map.getValue("8.91.05");} else //FSS 45� F
		if(value > 390 && value < 402){return map.getValue("8.91.04");} else //FSS 30� F
		if(value > 417 && value < 429){return map.getValue("8.91.03");} else //FSS 10� F
		if(value > 461 && value < 474){return map.getValue("8.91.01");} else //FSS Grundstellung
		if(value > 496 && value < 513){return map.getValue("8.91.19");} else //FSS 10� EG
		if(value > 522 && value < 534){return map.getValue("8.91.20");} else //FSS 30� EG
		if(value > 570 && value < 582){return map.getValue("8.91.21");} else //FSS 45� EG
		if(value > 637 && value < 654){return map.getValue("8.91.22");} else //FSS 80� EG
		if(value > 667 && value < 682){return map.getValue("8.91.23");} else //FSS 90� EG 
		{
//			try {
//				throw new Exception("no mapping defined for analog register: AN0, value: " + value);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				log.error(e);
//			}
			
		}
		
		//TODO revert
		return map.getValue("8.91.01");
//		return "";
	}
	
	public String getStream(String enabledGlobalId){
		HashMap<String, String> temp = new HashMap<String, String>(map.getMap());
		String stream = "";
		for(Entry<String, String> element : temp.entrySet()){
			
			String message = element.getValue();
			
			if(element.getKey().equals(enabledGlobalId)){
				stream += message.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, MessageCommon.MESSAGE_PARAMETER_ON);
			}else{
				stream += message.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, MessageCommon.MESSAGE_PARAMETER_OFF);
			}
		}
		
		return stream;
	}
}

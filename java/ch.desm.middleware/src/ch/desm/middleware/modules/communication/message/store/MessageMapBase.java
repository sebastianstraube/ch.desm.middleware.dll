package ch.desm.middleware.modules.communication.message.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class MessageMapBase {

	protected Map<String, String> map;
	
	public MessageMapBase() {
		this.map = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public String getKey(String value){
		
		for(Entry<String, String> entry : map.entrySet()){
			if(entry.getValue().equalsIgnoreCase(value)){
				return entry.getKey();
			}
		}
		
		return "";
	}
	
	public String getValue(String key){
		
		for(Entry<String, String> entry : map.entrySet()){
			if(entry.getKey().equalsIgnoreCase(key)){
				return entry.getValue();
			}
		}
		
		return "";
	}
	
	public abstract Map getMap();
	
	/**
	 * map initializing
	 */
	protected void initialize(){
		
	};
	
}

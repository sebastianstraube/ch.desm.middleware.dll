package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

public class Re420Messages {
	public final static String PARAMETER_PLACEHOLDER = "\\?";
	
	private static Map<String, String> messages;
	
	public Re420Messages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public Map<String, String> getMessages(){
		return messages;
	}

	
	private void initialize(){
		messages.put("1.90.02", "1.90.02;o;0;lampe;signalf;notrot;?;#");
	}
}

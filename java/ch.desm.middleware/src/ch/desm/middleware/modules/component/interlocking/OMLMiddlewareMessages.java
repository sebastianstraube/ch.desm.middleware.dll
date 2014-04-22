package ch.desm.middleware.modules.component.interlocking;

import java.util.HashMap;
import java.util.Map;

public class OMLMiddlewareMessages {
	
	public static Map<String, String> messages;
	
	public OMLMiddlewareMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	private void initialize(){
		messages.put("1.90.02", "1.90.02;o;0;lampe;signalf;notrot;?;#");
	}
}

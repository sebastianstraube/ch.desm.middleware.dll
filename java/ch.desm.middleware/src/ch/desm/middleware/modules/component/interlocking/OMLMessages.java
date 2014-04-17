package ch.desm.middleware.modules.component.interlocking;

import java.util.HashMap;
import java.util.Map;

public class OMLMessages {
	public final static String PARAMETER_PLACEHOLDER = "\\?";
	
	public static Map<String, String> messages;
	
	public OMLMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	private void initialize(){
		messages.put("1.90.02", "1.90.02;o;0;lampe;signalf;notrot;?;#");
	}
}

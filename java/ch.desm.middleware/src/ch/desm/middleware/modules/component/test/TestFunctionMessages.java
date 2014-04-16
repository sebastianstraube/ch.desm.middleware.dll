package ch.desm.middleware.modules.component.test;

import java.util.HashMap;
import java.util.Map;

public class TestFunctionMessages {
	public final static String PARAMETER_PLACEHOLDER = "\\?";
	
	public static Map<String, String> messages;
	
	public TestFunctionMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	private void initialize(){
		messages.put("1.90.02", "1.90.02;o;0;lampe;signalf;notrot;?;#");
	}
}

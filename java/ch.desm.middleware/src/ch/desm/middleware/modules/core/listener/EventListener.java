package ch.desm.middleware.modules.core.listener;

import java.util.EventObject;


public class EventListener extends EventObject {
	
	private String message;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2026849935605792165L;

	public EventListener(Object source, String message){
		super(source);
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	protected void setMessage(String message){
		this.message = message;
	}
}

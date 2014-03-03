package ch.desm.middleware.modules.service;

import java.util.ArrayList;

import ch.desm.middleware.modules.components.ComponentsBridge;
import ch.desm.middleware.modules.core.listener.EventListener;
import ch.desm.middleware.modules.core.listener.EventListenerBridge;

public class EventListenerService {
	
	String message;

	/**
	 * 
	 */
	private ArrayList<EventListenerBridge> eventListenerList;
	
	/**
	 * 
	 * @param source
	 */
	public EventListenerService(){
		eventListenerList = new ArrayList<EventListenerBridge>();
	}
	
	/**	
	 * 
	 * @param eventListener
	 */
	public synchronized void addEventListener(EventListenerBridge eventListener){
		eventListenerList.add(eventListener);
	}
	
	private synchronized void fireEvent(){
		EventListener eventListener = new EventListener(this, message);
		for(EventListenerBridge event : eventListenerList){
			event.handleEvent(eventListener);
		}
	}

}

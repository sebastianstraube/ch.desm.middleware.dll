package ch.desm.middleware.modules.service;

import java.util.ArrayList;

import ch.desm.middleware.modules.core.bridge.EventListenerBridge;

public class EventListenerService {

	public ArrayList<EventListenerBridge> eventListenerList;
	
	public EventListenerService(){
		eventListenerList = new ArrayList<EventListenerBridge>();
	}
	
	public void addEventListener(EventListenerBridge eventListener){
		eventListenerList.add(eventListener);
	}

}

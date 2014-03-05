package ch.desm.middleware.modules.core.event;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
	
	List<EventListenerBridge> eventListenerList;
	
	public EventHandler(){
		eventListenerList = new ArrayList<EventListenerBridge>();
	}
	
	public synchronized void addEventListener(EventListenerBridge eventListener){
		eventListenerList.add(eventListener);
	}
	
	public synchronized void removeEventListener(EventListenerBridge eventListener){
		eventListenerList.remove(eventListener);
	}

	public synchronized void fireEvent(){
		for(EventListenerBridge eventObject : eventListenerList){
			eventObject.handleEvent(new Event(this));
		}
	}
}

package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.core.event.EventHandler;

/**
 * TODO implementation
 * @author Sebastian
 *
 */
public class EventHandlerService {
	
	private static EventHandler eventHandler;
	
	public EventHandlerService(){
		eventHandler = new EventHandler();
	}
	
	public EventHandler getEventHandler(){
		return (eventHandler == null? EventHandlerService.eventHandler = new EventHandler() : EventHandlerService.eventHandler);
	}
	
}

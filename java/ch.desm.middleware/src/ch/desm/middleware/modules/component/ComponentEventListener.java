package ch.desm.middleware.modules.component;

import java.util.EventObject;

import ch.desm.middleware.modules.core.event.EventListenerBridge;

public class ComponentEventListener implements EventListenerBridge{

	@Override
	public void handleEvent(EventObject event) {
		System.out.println("Component event listener handles the Event: "+ event.getClass());		
	}

}

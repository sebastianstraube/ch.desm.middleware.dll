package ch.desm.middleware.modules.core.event;

import java.util.EventObject;

public interface EventListenerBridge {

	public void handleEvent(EventObject event);
}

package ch.desm.middleware.modules.component.interlocking;

import java.util.EventObject;

import ch.desm.middleware.modules.component.ComponentEventListener;

public class InterlockingObermattLangauEventListener extends ComponentEventListener{

	public InterlockingObermattLangauEventListener(){
		
	}

	@Override
	public void handleEvent(EventObject event) {
		System.out.println("Locsim event listener handles the Event: "+ event.getClass());
	}
	
}

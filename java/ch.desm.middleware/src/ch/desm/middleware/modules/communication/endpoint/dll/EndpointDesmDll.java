package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.Dll;
import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;

import java.util.ArrayList;

public abstract class EndpointDesmDll extends EndpointCommon {

    private Dll dll;

    public EndpointDesmDll() {
        dll = new Dll();
        dll.onStartProgramm("locsim.json");
    }

	@Override
	public void addEndpointListener(EndpointBaseListenerInterface listener) throws Exception {
		
		if(listener instanceof EndpointDesmDllListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only DLL endpoints supported.");
		}

	}

    private void loop() {
        while(true) {
            ArrayList<Integer> events = new ArrayList<Integer>();
            //dll.getEvents(events);
            for(Integer eventType : events) {
                switch (eventType) {
                    case 10: // TODO: where do we get the event types from?
                        int richtung = dll.getKilometerDirection();
                        for(EndpointBaseListenerInterface listener : listeners) {
                            //((EndpointDesmDllListenerInterface)listener).onKilometerDirection()
                        }
                        break;
                }
            }
        }
    }
}

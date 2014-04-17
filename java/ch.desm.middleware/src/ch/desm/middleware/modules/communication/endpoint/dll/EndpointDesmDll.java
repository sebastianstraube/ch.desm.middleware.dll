package ch.desm.middleware.modules.communication.endpoint.dll;

import java.util.ArrayList;

import ch.desm.Dll;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommonListenerInterface;

public abstract class EndpointDesmDll extends EndpointCommon {

    private Dll dll;

    public EndpointDesmDll() {
        dll = new Dll();
        dll.onStartProgramm("locsim.json");
    }

	@Override
	public void addEndpointListener(EndpointCommonListenerInterface listener) throws Exception {
		
		if(listener instanceof EndpointDesmDllListenerInterface){
			this.listeners.add(listener);
		}else{
			throw new Exception("Only DLL endpoints supported.");
		}

	}

    private void loop() {
        while(true) {
            ArrayList<Integer> events = null;
            try {
                events = new ArrayList<Integer>();
                //dll.getEvents(events);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(Integer eventType : events) {
                try {
                    switch (eventType) {
                        case 10: // TODO: where do we get the event types from?
                            int richtung = dll.getKilometerDirection();
                            for(EndpointCommonListenerInterface listener : listeners) {
                                //((EndpointDesmDllListenerInterface)listener).onKilometerDirection()
                            }
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

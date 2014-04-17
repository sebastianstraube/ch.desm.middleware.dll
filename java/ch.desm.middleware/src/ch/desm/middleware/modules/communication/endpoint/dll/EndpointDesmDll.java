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
            ArrayList<Dll.Event> events = null;
            try {
                events = dll.getEvents();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            for(Dll.Event event : events) {
                ArrayList<Integer> params = event.params;
                try {
                    switch (event.type) {
                        case Dll.ENUM_CMD_TRACK:
                            int gleisId = params.get(0);
                            Dll.Track track = dll.getTrack(gleisId);
                            // TODO: do something with track...
                            break;
                        case Dll.ENUM_CMD_TRACK_CONNECTION:
                            int gleis1Id = params.get(0);
                            int gleis2Id = params.get(1);
                            Dll.TrackConnection trackConnection = dll.getTrackConnection(gleis1Id, gleis2Id);
                            // TODO: do something with track connection...
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

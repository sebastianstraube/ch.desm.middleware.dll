package ch.desm.middleware.modules.component.petrinet.obermattlangnau;

import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import org.apache.log4j.Logger;

public class OMLPetriNetEndpoint extends EndpointCommon {

    protected static Logger log = Logger.getLogger(OMLPetriNetEndpoint.class);

    OMLPetriNetThread petriNetThread;

    public OMLPetriNetEndpoint() {
        petriNetThread = new OMLPetriNetThread("PetriNetThread", this);
        petriNetThread.start();
    }

    protected void onTransitionFired(String message) {
        onIncomingEndpointMessage(message);
    }

    public void setSensor(String message, int value) {
    	petriNetThread.setSensor(message, value);
    }
    
}

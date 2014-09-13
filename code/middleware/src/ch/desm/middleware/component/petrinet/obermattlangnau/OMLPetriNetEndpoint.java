package ch.desm.middleware.component.petrinet.obermattlangnau;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.endpoint.EndpointCommon;

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

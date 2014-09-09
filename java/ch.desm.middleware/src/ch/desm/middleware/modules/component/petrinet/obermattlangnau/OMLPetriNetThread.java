package ch.desm.middleware.modules.component.petrinet.obermattlangnau;

import ch.desm.middleware.modules.core.daemon.DaemonThread;
import ch.desm.middleware.modules.core.util.Pair;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OMLPetriNetThread extends DaemonThread {

    protected static Logger log = Logger.getLogger(OMLPetriNetThread.class);

    private static int SLEEP_INTERVAL = 100;

    private Object pendingSensorEventsLock = new Object();
    private List<Pair<String, Integer>> pendingSensorEvents = new ArrayList<Pair<String, Integer>>();

    private OMLPetriNetEndpoint endpoint;
    private OMLPetriNetAdapter petriNet;

    public OMLPetriNetThread(String threadName, OMLPetriNetEndpoint endpoint) {
        super(threadName);
        this.endpoint = endpoint;
        petriNet = new OMLPetriNetAdapter();
        petriNet.init();
    }

    public void setSensor(String signalName, int value) {
        synchronized (pendingSensorEvents) {
            pendingSensorEvents.add(new Pair<String, Integer>(signalName, value));
        }
    }

    public void run() {
        while(!isInterrupted()) {

            applySensorEvents();
            simulatePetriNet();
            applyFiredTransitions();

            try {
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }

    }

    private void applySensorEvents() {
        List<Pair<String, Integer>> pendingSensorEventsCopy = new ArrayList<Pair<String, Integer>>();

        synchronized (pendingSensorEventsLock) {
            pendingSensorEventsCopy.addAll(pendingSensorEvents);
            pendingSensorEvents.clear();
        }

        for (Pair<String, Integer> sensorEvent : pendingSensorEventsCopy) {
            petriNet.setSensor(sensorEvent.getLeft(), sensorEvent.getRight());
        }
    }

    private void simulatePetriNet() {
        while (petriNet.fireOneTransition()) {
            petriNet.writeActors();
        }
    }

    private void applyFiredTransitions() {
        List<String> firedTransitions = new ArrayList<String>();
        firedTransitions.addAll(petriNet.firedTransitions);
        petriNet.firedTransitions.clear();

        for (String firedTransition : firedTransitions) {
            endpoint.onTransitionFired(firedTransition);
        }
    }

}

package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232ListenerInterface;


public interface LocsimListenerRs232 extends EndpointRs232ListenerInterface{

	public void onStufenschalter(int id, String value);
}

package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;

public interface Re420ListenerUbw32 extends EndpointUbw32ListenerInterface {

	public void onHaupthahn1(String value);
}

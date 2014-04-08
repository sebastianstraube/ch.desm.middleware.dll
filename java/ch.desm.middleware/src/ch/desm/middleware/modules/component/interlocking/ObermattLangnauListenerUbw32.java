package ch.desm.middleware.modules.component.interlocking;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;

public interface ObermattLangnauListenerUbw32 extends EndpointUbw32ListenerInterface {

	public void onBlockVonLangnau(String payload);
}

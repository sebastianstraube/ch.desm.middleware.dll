package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;

public interface EndpointDesmDllListenerInterface extends
		EndpointBaseListenerInterface {

	public void onSignalAn(int signalId);
	public void onSignalAus(int signalId);
	public void onZugPositionChanged(double x, double y, double z);
}

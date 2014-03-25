package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.EndpointCommonListenerInterface;

public interface EndpointDesmDllListenerInterface extends
	EndpointCommonListenerInterface {

	public void onSignalAn(int signalId);
	public void onSignalAus(int signalId);
	public void onZugPositionChanged(double x, double y, double z);
}

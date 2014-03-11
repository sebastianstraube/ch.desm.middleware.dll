package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointListenerInterface;

public interface CommunicationEndpointDesmDllListenerInterface extends
		CommunicationEndpointListenerInterface {

	public void onSignalAn(int signalId);
	public void onSignalAus(int signalId);
	public void onZugPositionChanged(double x, double y, double z);
}

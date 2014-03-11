package ch.desm.middleware.modules.communication.broker.endpoint;


public interface BrokerEndpointListenerDesmDllBridge extends BrokerEndpointListenerBridge {
    public void onSignalAn(int signalId);
    public void onSignalAus(int signalId);
    public void onZugPositionChanged(double x, double y, double z);
}

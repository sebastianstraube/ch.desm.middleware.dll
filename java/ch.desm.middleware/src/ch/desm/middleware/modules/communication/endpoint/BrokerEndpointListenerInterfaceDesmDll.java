package ch.desm.middleware.modules.communication.endpoint;


public interface BrokerEndpointListenerInterfaceDesmDll extends BrokerEndpointListenerInterface {
    public void onSignalAn(int signalId);
    public void onSignalAus(int signalId);
    public void onZugPositionChanged(double x, double y, double z);
}

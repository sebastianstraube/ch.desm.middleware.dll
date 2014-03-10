package ch.desm.middleware.modules.communication.broker.message;


public class BrokerMessageSignalLampeAus implements BrokerMessageBridge {

    private int signalId;

    public BrokerMessageSignalLampeAus(int signalId) {
        this.signalId = signalId;
    }

    public int getSignalId() {
        return signalId;
    }

    public void setSignalId(int signalId) {
        this.signalId = signalId;
    }
}

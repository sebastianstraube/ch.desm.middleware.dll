package ch.desm.middleware.modules.communication.broker.message;


public class BrokerMessageSignalLampeAn implements BrokerMessageBridge {

    private int signalId;

    public BrokerMessageSignalLampeAn(int signalId) {
        this.signalId = signalId;
    }

    public int getSignalId() {
        return signalId;
    }

    public void setSignalId(int signalId) {
        this.signalId = signalId;
    }
}

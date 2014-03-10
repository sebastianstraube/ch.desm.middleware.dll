package ch.desm.middleware.modules.communication.broker.message;


public class BrokerMessageWeichenSchalterAus implements BrokerMessageBridge {

    private int weicheId;

    public BrokerMessageWeichenSchalterAus(int weicheId) {
        this.weicheId = weicheId;
    }

    public int getWeicheId() {
        return weicheId;
    }

    public void setWeicheId(int weicheId) {
        this.weicheId = weicheId;
    }
}

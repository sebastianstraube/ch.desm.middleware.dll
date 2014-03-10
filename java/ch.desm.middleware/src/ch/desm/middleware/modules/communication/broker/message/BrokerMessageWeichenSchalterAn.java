package ch.desm.middleware.modules.communication.broker.message;


public class BrokerMessageWeichenSchalterAn implements BrokerMessageBridge {

    private int weicheId;

    public BrokerMessageWeichenSchalterAn(int weicheId) {
        this.weicheId = weicheId;
    }

    public int getWeicheId() {
        return weicheId;
    }

    public void setWeicheId(int weicheId) {
        this.weicheId = weicheId;
    }
}

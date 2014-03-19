package ch.desm.middleware.modules.communication.broker.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;


public class BrokerMessageSignalLampeAn extends BrokerMessageCommon {

	private int signalId;  

    public BrokerMessageSignalLampeAn(int signalId) {
    	super();
        this.signalId = signalId;
    }

    public int getSignalId() {
        return signalId;
    }

    public void setSignalId(int signalId) {
        this.signalId = signalId;
    }
}

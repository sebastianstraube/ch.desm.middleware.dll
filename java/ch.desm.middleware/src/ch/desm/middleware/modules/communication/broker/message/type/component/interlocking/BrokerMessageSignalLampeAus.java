package ch.desm.middleware.modules.communication.broker.message.type.component.interlocking;

import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;


public class BrokerMessageSignalLampeAus extends BrokerMessageCommon{

	private int signalId;  

    public BrokerMessageSignalLampeAus(int signalId) {
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

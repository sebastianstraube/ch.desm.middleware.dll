package ch.desm.middleware.modules.communication.broker.message.type;

import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;

public class CommunicationBrokerMessageTypeWeichenSchalterAn extends CommunicationBrokerMessage{

    public CommunicationBrokerMessageTypeWeichenSchalterAn(int id, int sourceComponent, int destinationComponent) {
        super(id, sourceComponent, destinationComponent);
    }
}
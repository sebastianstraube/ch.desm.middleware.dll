package ch.desm.middleware.modules.communication.broker.message;

public class CommunicationBrokerMessage implements CommunicationBrokerMessageInterface{

    private int id;

    /**
     * 
     * @param id depends on message context, e.g. signal id
     */
    public CommunicationBrokerMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

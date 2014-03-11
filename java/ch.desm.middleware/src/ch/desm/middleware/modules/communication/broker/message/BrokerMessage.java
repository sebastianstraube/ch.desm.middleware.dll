package ch.desm.middleware.modules.communication.broker.message;

public class BrokerMessage implements BrokerMessageInterface{

    private int id;

    /**
     * 
     * @param id depends on message context, e.g. signal id
     */
    public BrokerMessage(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

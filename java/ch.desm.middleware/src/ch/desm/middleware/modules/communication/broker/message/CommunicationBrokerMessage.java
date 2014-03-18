package ch.desm.middleware.modules.communication.broker.message;


public class CommunicationBrokerMessage{
	
    private int id;
    private int sourceComponent;
    private int destinationComponent;
    private String message;
	
    
    /**
     * 
     * @param id
     */
    public CommunicationBrokerMessage(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @param id
     * @param message
     */
    public CommunicationBrokerMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }
    
    /**
     * 
     * @param id depends on message context, e.g. signal id
     */
    public CommunicationBrokerMessage(int id, String message, int sourceComponent, int destinationComponent) {
        this.id = id;
        this.sourceComponent = sourceComponent;
        this.destinationComponent = destinationComponent;
    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "id: "+id;
    	s+= ", ";
    	s+= "sourceComponent: "+sourceComponent;
    	s+= ", ";
    	s+= "destinationComponent: "+destinationComponent;
    	
    	return s;
    }
    
    public int getId() {
        return id;
    }
    
    public int getSource(){
    	return this.sourceComponent;
    }
    
    public int getDestination(){
    	return this.destinationComponent;
    }
    
    public String getMessage(){
    	return this.message;
    }
    
}

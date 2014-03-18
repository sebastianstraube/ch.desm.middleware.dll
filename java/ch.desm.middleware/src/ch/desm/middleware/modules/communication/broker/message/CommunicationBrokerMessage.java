package ch.desm.middleware.modules.communication.broker.message;


public class CommunicationBrokerMessage{
	
    private int id;
    private int sourceComponent;
    private int destinationComponent;
    private String message;
	
    /**
     * 
     * @param id depends on message context, e.g. signal id
     */
    public CommunicationBrokerMessage(int id, int sourceComponent, int destinationComponent) {
        this.id = id;
        this.sourceComponent = sourceComponent;
        this.destinationComponent = destinationComponent;
    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "id: "+id;
    	s+= "\n";
    	s+= "sourceComponent: "+sourceComponent;
    	s+= "\n";
    	s+= "destinationComponent: "+destinationComponent;
    	s+= "\n";
    	
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

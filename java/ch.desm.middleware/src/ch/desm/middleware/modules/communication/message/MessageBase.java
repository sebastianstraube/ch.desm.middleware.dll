package ch.desm.middleware.modules.communication.message;

public abstract class MessageBase{
	
    private static double messageId = 0;
	private String payload;
	
	//TODO move refactoring
	private String instance;
	
    /**
     * 
     */
    private MessageBase() {
    	MessageBase.messageId++;
    }
        
    /**
     * 
     * @param idParameterType depends on message context, e.g. signal id
     * @param messageType 
     * 
     */
    public MessageBase(String instance, String payload) {
    	this();
    	this.payload = payload;
    	this.instance = instance;
    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "messageId: "+messageId;
    	s+= ", ";
    	s+= "payload: "+payload;
    	s+= ", ";
    	s+= "instance: "+instance;
    	
    	return s;
    }
    
    public double getMessageId() {
        return messageId;
    }
    
    public String getPayload(){
    	return this.payload;
    }
    
    public String getInstance(){
    	return this.instance;
    }
}

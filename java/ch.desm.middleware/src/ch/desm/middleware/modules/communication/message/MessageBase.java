package ch.desm.middleware.modules.communication.message;

public abstract class MessageBase{
	
	
    private static double messageId = 0;
    private boolean isReturnMessage;
	private String payload;
	
	
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
    public MessageBase(String payload) {
    	this();
    	this.payload = payload;

    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "messageId: "+messageId;
    	s+= ", ";
    	s+= "payload: "+payload;
    	s+= ", ";
    	s+= "isReturnMessage: " + isReturnMessage;
    	
    	return s;
    }
    
    public double getMessageId() {
        return messageId;
    }
    
    public String getPayload(){
    	return this.payload;
    }
    
    public void setReturnMessage(boolean isReturnMessage){
    	this.isReturnMessage = isReturnMessage;
    }
    
    public boolean isReturnMessage(){
    	return isReturnMessage;
    }
}

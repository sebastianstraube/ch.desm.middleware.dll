package ch.desm.middleware.modules.communication.broker.message;

import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;



public class BrokerMessageCommon{
	
	
    private static double messageId = 0;
	private EnumComponentType messageType;
	private int parameterTypeId;
	private String message;
	
    /**
     * 
     */
    public BrokerMessageCommon() {
    	BrokerMessageCommon.messageId++;
    }
        
    /**
     * 
     * @param parameterTypeId depends on message context, e.g. signal id
     * 
     */
    public BrokerMessageCommon(EnumComponentType messageType, int parameterTypeId, String message) {
    	this();
    	this.messageType = messageType;
    	this.parameterTypeId = parameterTypeId;
    	this.message = message;
    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "id: "+messageId;
    	s+= ", ";
    	s+= "message: \""+message+"\"";
    	
    	return s;
    }
    
    public double getMessageId() {
        return messageId;
    }
        
    public String getMessage(){
    	return this.message;
    }
    
    public EnumComponentType getMessageType(){
    	return this.messageType;
    }
    
    public int getParameterTypeId(){
    	return this.parameterTypeId;
    }
    
}

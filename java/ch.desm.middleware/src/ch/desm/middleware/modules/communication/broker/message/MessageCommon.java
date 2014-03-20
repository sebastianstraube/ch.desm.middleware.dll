package ch.desm.middleware.modules.communication.broker.message;




public class MessageCommon{
	
	
    private static double messageId = 0;
	private EnumMessageType messageType;
	private int parameterTypeId;
	
	public enum EnumMessageType{
		SIMULATION, INTERLOCKING, CABINE, CORBA, DLL
	}
    /**
     * 
     */
    private MessageCommon() {
    	MessageCommon.messageId++;

    }
        
    /**
     * 
     * @param parameterTypeId depends on message context, e.g. signal id
     * @param messageType 
     * 
     */
    public MessageCommon(EnumMessageType messageType, int parameterTypeId) {
    	this();
    	this.messageType = messageType;
    	this.parameterTypeId = parameterTypeId;
    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "id: "+messageId;
    	
    	return s;
    }
    
    public double getMessageId() {
        return messageId;
    }
    
    public EnumMessageType getMessageType(){
    	return this.messageType;
    }
    
    public int getParameterTypeId(){
    	return this.parameterTypeId;
    }
    
}

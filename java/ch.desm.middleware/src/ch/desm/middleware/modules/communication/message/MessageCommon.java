package ch.desm.middleware.modules.communication.message;

public class MessageCommon{
	
	
    private static double messageId = 0;
	private EnumMessageType messageType;
	private int idParameterType;
	private String value;
	
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
     * @param idParameterType depends on message context, e.g. signal id
     * @param messageType 
     * 
     */
    public MessageCommon(EnumMessageType messageType, int idParameterType, String value) {
    	this();
    	this.messageType = messageType;
    	this.idParameterType = idParameterType;
    	this.value = value;
    }

    @Override
    public String toString(){
    	String s = "";
    	s+= "id: "+messageId;
    	s+= ", ";
    	s+= "messageType: "+messageType;
    	s+= ", ";
    	s+= "idParameterType: "+idParameterType;
    	s+= ", ";
    	s+= "value: "+value;
    	
    	return s;
    }
    
    public double getMessageId() {
        return messageId;
    }
    
    public EnumMessageType getMessageType(){
    	return this.messageType;
    }
    
    public int getIdParameterType(){
    	return this.idParameterType;
    }
    
    public void setValue(String value){
    	this.value = value;
    }
    
    public String getValue(){
    	return this.value;
    }
        
}

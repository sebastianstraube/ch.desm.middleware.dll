package ch.desm.middleware.modules.communication.message;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public abstract class MessageBase{
	
	public enum EnumMessageTopic{
		SUMLATION, INTERLOCKING, CABINE
	}
	
	public EnumMessageTopic topic;
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
    public MessageBase(String payload, EnumMessageTopic topic) {
    	this();
    	this.payload = payload;
    	this.topic = topic;

    }

    @Override
    public String toString(){
    	String s = "";
		s += "topic: " + topic;
		s += ", ";
    	s+= "messageId: "+messageId;
    	s+= ", ";
    	s+= "isReturnMessage: " + isReturnMessage;
//    	s+= ", ";
//    	s+= "payload: "+payload;
    	
    	return s;
    }
    
    public double getMessageId() {
        return messageId;
    }
    
    public String getPayload(){
    	return this.payload;
    }
    
	/**
	 * 
	 * @param message
	 * @return true if the ubw32 returns a state package
	 */
	public boolean isReturnMessage() {
		boolean isReturnMessage = false;

		if (payload.startsWith(EndpointUbw32.RETURN_INPUT_ANALOG)) {
			isReturnMessage = true;
		} else if (payload.startsWith(EndpointUbw32.RETURN_INPUT_STATE)) {
			isReturnMessage = true;
		} else if (payload.startsWith(EndpointUbw32.RETURN_PIN_INPUT)) {
			isReturnMessage = true;
		}

		return isReturnMessage;
	}
}

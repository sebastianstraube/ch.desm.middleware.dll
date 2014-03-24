package ch.desm.middleware.modules.communication.message.type;


public abstract class MessageTypeBase {

	private int elementId;
	private String value;
	
	public MessageTypeBase(int id, String value){
		this.elementId = id;
		this.value = value;
	}
	
	public int getElementId(){
		return this.elementId;
	}
	
	public String getValue(){
		return this.value;
	}
}

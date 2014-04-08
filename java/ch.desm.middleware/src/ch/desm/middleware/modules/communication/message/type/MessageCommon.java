package ch.desm.middleware.modules.communication.message.type;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageCommon extends MessageBase {

	protected String globalId;
	protected String process;
	protected String element;
	protected String function;
	protected String parameter;
	protected String instance;

	
	public MessageCommon(MessageBase message){
		super(message.getPayload());
	}
	
	public MessageCommon(String payload){
		super(payload);
	}
	
	public MessageCommon(String instance, String payload){
		super(payload);
		this.instance = instance;
	}
	
	public MessageCommon(String nrMatrix, String process, String element,
			String function, String instance, String parameter, String payload) {
		super(payload);
		this.instance = instance;
		this.globalId = nrMatrix;
		this.process = process;
		this.element = element;
		this.function = function;
		this.parameter = parameter;
	}
	
	public String getGlobalId(){
		return this.globalId;
	}
	
    public String getProcess(){
    	return this.process;
    }
	
	public String getElement(){
		return this.element;
	}
	
	public String getFunction(){
		return this.function;
	}
	
	public String getParameter(){
		return this.parameter;
	}
	
    public String getInstance(){
    	return this.instance;
    }

    
	public String toString() {
		String s = super.toString();
		s += ", ";
		s += "nrMatrix: " + globalId;
		s += ", ";
		s += "process: " + process;
		s += ", ";
		s += "element: " + element;
		s += ", ";
		s += "function: " + function;
		s += ", ";
		s += "parameter: " + parameter;
    	s+= ", ";
    	s+= "instance: "+instance;

		return s;
	}
}

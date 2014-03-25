package ch.desm.middleware.modules.communication.message.type;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageCommon extends MessageBase {

	protected String nrMatrix;
	protected String io;
	protected String element;
	protected String function;
	protected String parameter;

	public MessageCommon(String instance, String payload){
		super(instance, payload);
	}
	
	public MessageCommon(String nrMatrix, String io, String element,
			String function, String instance, String parameter, String payload) {
		super(instance, payload);
		this.nrMatrix = nrMatrix;
		this.io = io;
		this.element = element;
		this.function = function;
		this.parameter = parameter;
	}
	
	public String getNrMatrix(){
		return this.nrMatrix;
	}
	
	public String getIo(){
		return this.io;
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

	public String toString() {
		String s = super.toString();
		s += ", ";
		s += "nrMatrix: " + nrMatrix;
		s += ", ";
		s += "io: " + io;
		s += ", ";
		s += "element: " + element;
		s += ", ";
		s += "function: " + function;
		s += ", ";
		s += "parameter: " + parameter;
		
		return s;
	}
}

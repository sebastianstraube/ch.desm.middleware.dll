package ch.desm.middleware.modules.communication.message;

public class MessageCommon extends MessageBase{

	private String nrMatrix;
	private String io;
	private String element;
	private String function;
	private String instance;
	private String parameter1;
	private String parameter2;
	
	public MessageCommon(String nrMatrix, String io, String element, String function, String instance, String parameter1, String parameter2, String payload) {
		super(payload);
		this.nrMatrix = nrMatrix;
		this.io = io;
		this.element = element;
		this.function = function;
		this.instance = instance;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}
	
	public String toString(){
		String s= super.toString();
		
		s+="nrMatrix: " + nrMatrix;
		s+=", ";
		s+="io: " + io;
		s+=", ";
		s+="element: " + element;
		s+=", ";
		s+="function: " + function;
		s+=", ";
		s+="instance: " + instance;
		s+=", ";
		s+="parameter1: " + parameter1;
		s+=", ";
		s+="parameter2: " + parameter2;

		return s;
	}
}

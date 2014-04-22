package ch.desm.middleware.modules.communication.message.type;


public class MessageMiddleware extends MessageCommon {
	
	protected String outputInput;

	public MessageMiddleware(MessageUbw32 message) {
		super(message);
	}


	
	public MessageMiddleware(EnumMessageTopic topic, String globalId, String externIntern,
			String element, String function, String instance, String parameter,
			String payload, String outputInput) {
		super(topic, globalId, externIntern, element, element, function, 
				instance, parameter);
		
		this.outputInput = outputInput;
	}

	public String getOutputInput() {
		return this.outputInput;
	}

	public String toString() {
		String s = super.toString();
		s += ", ";
		s += "outputInput: " + outputInput;

		return s;
	}
}

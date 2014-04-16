package ch.desm.middleware.modules.communication.message.type;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageCommon extends MessageBase {

	public static final String CHAR_OUTPUT = "o";
	public static final String CHAR_INPUT = "i";
	public static final String CHAR_EXTERN = "e";
	public static final String CHAR_INTERN = "i";
	public static final String CHAR_EXTER_INTERN = "ei";

	protected String globalId;
	
	/**
	 * the signal coming from
	 * extern components,
	 * only from system intern or
	 * from extern components and system intern
	 */
	protected String externIntern;
	protected String outputInput;
	protected String process;
	protected String element;
	protected String function;
	protected String parameter;
	protected String instance;

	public MessageCommon(MessageUbw32 message) {
		super(message.getPayload(), message.topic);
	}

	public MessageCommon(MessageBase message) {
		super(message.getPayload(), message.topic);
	}

	public MessageCommon(EnumMessageTopic topic, String globalId,
			String outputInput, String externIntern, String process,
			String element, String function, String instance, String parameter,
			String payload) {
		super(payload, topic);
		this.globalId = globalId;
		this.outputInput = outputInput;
		this.externIntern = externIntern;
		this.process = process;
		this.element = element;
		this.function = function;
		this.instance = instance;
		this.parameter = parameter;
	}

	public String getGlobalId() {
		return this.globalId;
	}

	public String getOutputInput() {
		return this.outputInput;
	}

	public String getExternIntern() {
		return this.externIntern;
	}

	public String getProcess() {
		return this.process;
	}

	public String getElement() {
		return this.element;
	}

	public String getFunction() {
		return this.function;
	}

	public String getParameter() {
		return this.parameter;
	}

	public String getInstance() {
		return this.instance;
	}

	public String toString() {
		String s = super.toString();
		s += ", ";
		s += "globalId: " + globalId;
		s += ", ";
		s += "outputInput: " + outputInput;
		s += ", ";
		s += "externIntern: " + externIntern;
		s += ", ";
		s += "process: " + process;
		s += ", ";
		s += "element: " + element;
		s += ", ";
		s += "function: " + function;
		s += ", ";
		s += "parameter: " + parameter;
		s += ", ";
		s += "instance: " + instance;

		return s;
	}
}

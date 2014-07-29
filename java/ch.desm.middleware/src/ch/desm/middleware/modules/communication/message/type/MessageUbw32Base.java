package ch.desm.middleware.modules.communication.message.type;

abstract class MessageUbw32Base extends MessageBase {

	public static final String MESSAGE_CHAR_OUTPUT = "o";
	public static final String MESSAGE_CHAR_INPUT = "i";
	public static final String MESSAGE_CHAR_ONLYSOFTWARE = "os";
	/**
	 * TODO refactoring
	 */

	
	public MessageUbw32Base(String payload, String topic){
		super(payload, topic);
	}

	protected abstract void initialize();
	
	protected String cleanPayload(String payload) {

		if (payload.contains("IA")) {
			int msg = payload.lastIndexOf("IA");
			payload = payload.substring(msg, payload.length());

		} else if (payload.contains("II")) {
			payload = payload.replaceFirst("II", "I");
		}

		return payload;
	}
	
	protected abstract void parsePayload(String payload);
}

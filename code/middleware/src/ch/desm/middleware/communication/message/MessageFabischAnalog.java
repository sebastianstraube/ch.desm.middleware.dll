package ch.desm.middleware.communication.message;


public class MessageFabischAnalog extends MessageUbw32Base {

	public MessageFabischAnalog(String payload, String topic) {
		super(payload, topic);
	}

	/**
	 * 
	 */
	@Override
	public String getInputValue(String register, String pin) {
		String value = "";

		return value;
	}

	/**
	 * TODO implementation
	 */
	public String toString() {
		String s = super.toString();

		return s;
	}
}

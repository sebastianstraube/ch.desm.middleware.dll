package ch.desm.middleware.modules.component.interlocking;

import java.util.ArrayList;
import java.util.Map.Entry;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32Analog;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32Digital;

public class OMLBaseImpl extends OMLBase implements
		EndpointUbw32ListenerInterface {

	private OMLMessages omlMessages;

	public OMLBaseImpl(Broker broker, EndpointCommon communicationEndpointUbw32) {
		super(broker, communicationEndpointUbw32);
		// TODO Auto-generated constructor stub

		this.omlMessages = new OMLMessages();
	}

	protected void onIncomingBrokerMessage(String message) {
		System.out.println("broker (" + this.getClass()
				+ ") received message: " + message);

		MessageTranslatorMiddleware translator = new MessageTranslatorMiddleware();
		ArrayList<MessageMiddleware> messageCommon = translator
				.translateToCommonMiddlewareMessageList(message);

		MessageRouter router = new MessageRouter();
		router.processBrokerMessage(this, messageCommon);
	}

	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("endpoint (" + getEndpoint().getSerialPortName()
				+ ") received message: " + message);

		MessageTranslatorMiddleware translator = new MessageTranslatorMiddleware();
		MessageUbw32Digital ubw32Message = translator
				.decodeUbw32EndpointMessage(message,
						MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);

		String messages = processInputs(ubw32Message);

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, messages,
				MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
	}

	/**
	 * TODO multiple analog message
	 * 
	 * @param message
	 */
	public String processInputs(MessageUbw32Digital message) {

		String middlewareMessagesInput = "";

		// Digital messages

		for (Entry<String, EnumEndpointUbw32RegisterDigital> entry : this
				.getEndpoint().getConfiguration().getMapInputDigital()
				.entrySet()) {

			String stream = omlMessages.messages.get(entry.getKey());
			if (message.getInputValue(entry.getValue())) {
				stream = stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER,
						"on");
			} else {
				stream = stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER,
						"off");
			}

			middlewareMessagesInput = middlewareMessagesInput.concat(stream);
		}

		return middlewareMessagesInput;
	}

	/**
	 * TODO multiple analog message
	 * 
	 * @param message
	 */
	public String processInputs(MessageUbw32Analog message) {

		String middlewareMessagesInput = "";

		for (Entry<String, EnumEndpointUbw32RegisterAnalog> entry : this
				.getEndpoint().getConfiguration().getMapInputAnalog()
				.entrySet()) {

			String stream = omlMessages.messages.get(entry.getKey());
			int analogValue = Integer.parseInt(message.getInputValue(entry
					.getValue()));

			// lookup global id from analog value
			String globalId = this.getEndpoint().getConfiguration()
					.getGlobalIdFSS(entry.getValue(), analogValue);

			// if FSS id is equal map entry,
			// then set message stream parameter on else off
			if (entry.getValue().equals(globalId)) {
				stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, "on");
			} else {
				stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, "off");
			}

			middlewareMessagesInput = middlewareMessagesInput.concat(stream);
		}

		return middlewareMessagesInput;
	}

	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	@Override
	public void setFunction(String port, String pin, String value) {
		this.endpoint.sendCommandPinOutput(port, pin, value);
	};

	/**
	 * read the BlockVonLangnau pin
	 * 
	 * @param message
	 */
	@Override
	public void getFunction(String port, String pin) {
		this.endpoint.sendCommandPinInput(port, pin);
	}

	/**
	 * 
	 */
	@Override
	public boolean hasTopicSigned(String topic) {
		return signedTopic.contains(topic);
	}

	/**
	 * 
	 */
	@Override
	protected void intializeSignedTopic() {
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM);
		signedTopic
				.add(MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_TEST);
	}
}

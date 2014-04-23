package ch.desm.middleware.modules.component.test;

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
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;

public class TestBaseImpl extends TestBase implements
		EndpointUbw32ListenerInterface {

	private TestFunctionMessages functionMessages;

	public TestBaseImpl(Broker broker, EndpointCommon communicationEndpointUbw32) {
		super(broker, communicationEndpointUbw32);
		// TODO Auto-generated constructor stub

		this.functionMessages = new TestFunctionMessages();
	}

	protected void onIncomingBrokerMessage(String message) {
		System.out.println("broker (" + this.getClass()
				+ ") received message: " + message);

		MessageTranslatorMiddleware translator = new MessageTranslatorMiddleware();
		ArrayList<MessageMiddleware> messageCommon = translator
				.translateToCommonMessageObjectList(message);

		// TODO route and transmit to endpoint
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
		MessageUbw32 ubw32Message = translator.decodeUbw32EndpointMessage(
				message, MessageBase.MESSAGE_TOPIC_TEST);

		String messages = processInputs(ubw32Message);

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, messages, MessageBase.MESSAGE_TOPIC_TEST);
	}

	/**
	 * TODO multiple analog message
	 * 
	 * @param message
	 */
	public String processInputs(MessageUbw32 message) {

		String middlewareMessagesInput = "";

		// Digital messages
		if (message.isDigital) {
			for (Entry<String, EnumEndpointUbw32RegisterDigital> entry : this
					.getEndpoint().getConfiguration().getMapInputDigital()
					.entrySet()) {

				String stream = functionMessages.messages.get(entry.getKey());
				if (message.getInputDigitalValue(entry.getValue())) {
					stream = stream.replaceAll(
							TestFunctionMessages.PARAMETER_PLACEHOLDER, "on");
				} else {
					stream = stream.replaceAll(
							TestFunctionMessages.PARAMETER_PLACEHOLDER, "off");
				}

				middlewareMessagesInput = middlewareMessagesInput
						.concat(stream);
			}

			// Analog messages
		} else {

			for (Entry<String, EnumEndpointUbw32RegisterAnalog> entry : this
					.getEndpoint().getConfiguration().getMapInputAnalog()
					.entrySet()) {

				String stream = functionMessages.messages.get(entry.getKey());
				int analogValue = Integer.parseInt(message
						.getInputAnalogValue(entry.getValue()));

				// lookup global id from analog value
				String globalId = this.getEndpoint().getConfiguration()
						.getGlobalIdFSS(entry.getValue(), analogValue);

				// if FSS id is equal map entry,
				// then set message stream parameter on else off
				if (entry.getValue().equals(globalId)) {
					stream.replaceAll(
							TestFunctionMessages.PARAMETER_PLACEHOLDER, "on");
				} else {
					stream.replaceAll(
							TestFunctionMessages.PARAMETER_PLACEHOLDER, "off");
				}

				middlewareMessagesInput = middlewareMessagesInput
						.concat(stream);
			}
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
	
	@Override
	protected void intializeSignedTopic() {
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_CABINE_RE420);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_TEST);
	}
}

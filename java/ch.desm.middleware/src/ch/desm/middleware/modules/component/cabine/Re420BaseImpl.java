package ch.desm.middleware.modules.component.cabine;

import java.util.ArrayList;
import java.util.Map.Entry;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageBase;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;

public class Re420BaseImpl extends Re420Base implements
		EndpointUbw32ListenerInterface {

	private Re420MiddlewareMessages messages;

	public Re420BaseImpl(Broker broker, Re420EndpointUbw32 communicationEndpoint) {
		super(broker, communicationEndpoint);
		// TODO Auto-generated constructor stub

		this.messages = new Re420MiddlewareMessages();
	}

	/**
	 * TODO imeplementation
	 */
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
				message, MessageCommon.MESSAGE_TOPIC_CABINE_RE420);

		String messages = convertToMiddlewareMessage(ubw32Message);

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, messages, MessageBase.MESSAGE_TOPIC_CABINE_RE420);
	}

	/**
	 * 
	 * @param message
	 */
	public String convertToMiddlewareMessage(MessageUbw32 message) {

		String middlewareMessagesInput = "";

		// Digital messages
		if (message.isDigital) {
			for (Entry<String, EnumEndpointUbw32RegisterDigital> entry : this
					.getEndpoint().getConfiguration().getMapInputDigital()
					.entrySet()) {

				String stream = messages.getMessages().get(entry.getKey());
				if (stream == null) {
					try {
						throw new Exception(
								"mapping error found no global id in middleware message with key: "
										+ entry.getKey() + " and value: "
										+ entry.getValue() + " in message: " + message);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (message.getInputDigitalValue(entry.getValue())) {
					stream = stream.replaceAll(
							MessageCommon.PARAMETER_PLACEHOLDER, "on");
				} else {
					stream = stream.replaceAll(
							MessageCommon.PARAMETER_PLACEHOLDER, "off");
				}

				middlewareMessagesInput = middlewareMessagesInput
						.concat(stream);
			}

			// Analog messages
		} else {

			for (Entry<String, EnumEndpointUbw32RegisterAnalog> entry : this
					.getEndpoint().getConfiguration().getMapInputAnalog()
					.entrySet()) {

				String stream = messages.getMessages().get(entry.getKey());
				if (stream == null) {
					try {
						throw new Exception(
								"the configuration mapping found a problematic global id:"
										+ entry.getKey() + " with value: "
										+ entry.getValue() + "in message: " + message);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				int analogValue = Integer.parseInt(message
						.getInputAnalogValue(entry.getValue()));

				// lookup global id from analog value
				String globalId = this.getEndpoint().getConfiguration()
						.getGlobalIdFSS(entry.getValue(), analogValue);

				// if id is equal map entry,
				// then set message stream parameter on else off
				if (entry.getValue().equals(globalId)) {
					stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER, "on");
				} else {
					stream.replaceAll(MessageCommon.PARAMETER_PLACEHOLDER,
							"off");
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
	 * 
	 * @param port
	 * @param pin
	 * @param value
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
		signedTopic.add(MessageBase.MESSAGE_TOPIC_CABINE_RE420);
		signedTopic.add(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_RS232);
	}

}

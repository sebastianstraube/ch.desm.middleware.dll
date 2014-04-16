package ch.desm.middleware.modules.component.cabine;

import java.util.ArrayList;
import java.util.Map.Entry;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortAnalog.EnumEndpointUbw32RegisterAnalog;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.MessageBase.EnumMessageTopic;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;
import ch.desm.middleware.modules.component.interlocking.OMLFunctionMessages;

public class Re420BaseImpl extends Re420Base implements
		EndpointUbw32ListenerInterface {

	private Re420FunctionMessages functionMessages;

	public Re420BaseImpl(Broker broker, Re420EndpointUbw32 communicationEndpoint) {
		super(broker, communicationEndpoint);
		// TODO Auto-generated constructor stub

		this.functionMessages = new Re420FunctionMessages();
	}

	/**
	 * TODO imeplementation
	 */
	protected void onIncomingBrokerMessage(String message) {
		if (message != null && !message.isEmpty()) {
			System.out.println("broker (" + this.getClass()
					+ ") received message: " + message);

			MessageTranslator translator = new MessageTranslator();
			ArrayList<MessageCommon> messageCommon = translator
					.translateToCommonMessageObjectList(
							message, EnumMessageTopic.INTERLOCKING);

			// TODO route and transmit to endpoint
			MessageRouter router = new MessageRouter();
			router.processBrokerMessage(this, messageCommon);
		}

	}

	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("endpoint (" + getEndpoint().getSerialPortName()
				+ ") received message: " + message);

		MessageTranslator translator = new MessageTranslator();
		MessageUbw32 ubw32Message = translator.decodeUbw32EndpointMessage(
				message, EnumMessageTopic.INTERLOCKING);

		String messages = processInputs(ubw32Message);

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, messages);
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
					.getEndpoint().getConfiguration().getMapInputDigital().entrySet()) {

				String stream = functionMessages.getMessages().get(entry.getKey());
				if(stream == null){
					try {
						throw new Exception("the configuration mapping found a problematic global id:" + entry.getKey() + "with value: " + entry.getValue());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (message.getInputDigitalValue(entry.getValue())) {
					stream = stream
							.replaceAll(
									OMLFunctionMessages.PARAMETER_PLACEHOLDER,
									"on");
				} else {
					stream = stream
							.replaceAll(
									OMLFunctionMessages.PARAMETER_PLACEHOLDER,
									"off");
				}

				middlewareMessagesInput = middlewareMessagesInput
						.concat(stream);
			}

			// Analog messages
		} else {

			for (Entry<String, EnumEndpointUbw32RegisterAnalog> entry : this
					.getEndpoint().getConfiguration().getMapInputAnalog().entrySet()) {

				String stream = functionMessages.getMessages().get(entry.getKey());
				int analogValue = Integer.parseInt(message
						.getInputAnalogValue(entry.getValue()));

				// lookup global id from analog value
				String globalId = this.getEndpoint().getConfiguration()
						.getGlobalIdFSS(entry.getValue(), analogValue);

				// if FSS id is equal map entry,
				// then set message stream parameter on else off
				if (entry.getValue().equals(globalId)) {
					stream.replaceAll(
							OMLFunctionMessages.PARAMETER_PLACEHOLDER,
							"on");
				} else {
					stream.replaceAll(
							OMLFunctionMessages.PARAMETER_PLACEHOLDER,
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

}

package ch.desm.middleware.component.cabine.re420;

import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.message.MessageCommon;
import ch.desm.middleware.communication.message.MessageUbw32Analog;
import ch.desm.middleware.communication.message.MessageUbw32Base;
import ch.desm.middleware.communication.message.MessageUbw32DigitalRegisterComplete;
import ch.desm.middleware.communication.message.MessageUbw32DigitalRegisterSingle;
import ch.desm.middleware.communication.message.processor.MessageProcessor;
import ch.desm.middleware.component.cabine.re420.elements.Re420ElementFahrschalter;
import ch.desm.middleware.component.cabine.re420.maps.Re420MapBinding;
import ch.desm.middleware.component.cabine.re420.maps.Re420MapFabischValue;
import ch.desm.middleware.component.cabine.re420.maps.Re420MapMiddleware;

public class Re420MessageProcessor extends MessageProcessor {

	private static Logger log = Logger.getLogger(Re420MessageProcessor.class);

	private Re420ElementFahrschalter fahrschalter;
	private Re420MapBinding binding;
	private Re420MapFabischValue mapValues;

	public Re420MessageProcessor() {
		fahrschalter = new Re420ElementFahrschalter();
		binding = new Re420MapBinding();
		mapValues = new Re420MapFabischValue();
	}

	public String handleMessageFahrschalter(String key, boolean isEnabled) {

		String fahrschalterKey = fahrschalter.getMessagePositionFahrschalter(
				key, isEnabled);

		return fahrschalterKey;
	}

	/**
	 * 
	 * @param impl
	 * @param message
	 * @param mapMiddlewareMessages
	 * @return
	 */
	public String convertToMiddlewareMessage(Re420 impl,
			MessageUbw32Base message, Re420MapMiddleware mapMiddlewareMessages) {

		String middlewareMessagesInput = "";

		if (message instanceof MessageUbw32DigitalRegisterSingle) {

			for (Entry<String, String> entry : impl.getEndpointUbw32().re420MapDigital
					.getMap().entrySet()) {

				if (entry.getValue()
						.equals(((MessageUbw32DigitalRegisterSingle) message)
								.getPort())) {

					String key = entry.getKey();
					boolean isInputEnabled = message.getInputValue(
							entry.getValue().substring(0),
							entry.getValue().substring(1)).contains("1");
					String parameter = ((MessageUbw32DigitalRegisterSingle) message)
							.isEnabled() == true ? "on" : "off";
					
					String stream = null;

					// handle Fahrschalter
					if (fahrschalter.keyListFahrschalter.contains(key)) {

						String fahrschalterKey = fahrschalter
								.getMessagePositionFahrschalter(key,
										isInputEnabled);

						if (!fahrschalterKey.isEmpty()) {
							parameter = "on";
							stream = fahrschalter.getMap().getValue(fahrschalterKey);
							stream = stream.replaceAll(
									MessageCommon.PARAMETER_PLACEHOLDER,
									parameter);
							middlewareMessagesInput = middlewareMessagesInput
									.concat(stream);
						}

					}

					else {
						stream = mapMiddlewareMessages.getMap().get(key);
						
						if (stream == null) {
							try {
								throw new Exception(
										"mapping error found no global id in middleware message with key: "
												+ entry.getKey()
												+ " and value: "
												+ entry.getValue()
												+ " in message: " + message);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								log.error(e);
							}
						}

						stream = stream.replaceAll(
								MessageCommon.PARAMETER_PLACEHOLDER, parameter);
						middlewareMessagesInput = middlewareMessagesInput
								.concat(stream);
					}
				}
			}
		}

		else if (message instanceof MessageUbw32DigitalRegisterComplete) {

			for (Entry<String, String> entry : impl.getEndpointUbw32().re420MapDigital
					.getMap().entrySet()) {

				// convert input to common parameter
				boolean isInputEnabled = message.getInputValue(
						entry.getValue().substring(0),
						entry.getValue().substring(1)).contains("1");
				String parameter = isInputEnabled == true ? "on" : "off";
				String key = entry.getKey();

				String stream = null;

				// handle Fahrschalter
				if (fahrschalter.keyListFahrschalter.contains(key)) {

					String fahrschalterKey = fahrschalter
							.getMessagePositionFahrschalter(key, isInputEnabled);

					if (!fahrschalterKey.isEmpty()) {
						parameter = "on";
						stream = fahrschalter.getMap().getValue(fahrschalterKey);
						stream = stream.replaceAll(
								MessageCommon.PARAMETER_PLACEHOLDER, parameter);
						middlewareMessagesInput = middlewareMessagesInput
								.concat(stream);
					}

				} else {

					// lookup for binding key to send directly
					if (binding.isKeyAvailable(entry.getKey())) {
						key = binding.getValue(entry.getKey());

						
						parameter = message.getInputValue(entry.getValue()
								.substring(0), entry.getValue().substring(1));
						
						String value = impl.getEndpointFabisch().mapDigital.getValue(key);

						if (!value.isEmpty()) {
							
							parameter = convertParameter(value, parameter);
							
							impl.getEndpointFabisch().sendStream(
									value + parameter);
						}
					} else {

						stream = mapMiddlewareMessages.getMap().get(key);
						
						if (stream == null) {
							try {
								throw new Exception(
										"mapping error found no global id in middleware message with key: "
												+ entry.getKey()
												+ " and value: "
												+ entry.getValue()
												+ " in message: " + message);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								log.error(e);
							}
						}

						stream = stream.replaceAll(
								MessageCommon.PARAMETER_PLACEHOLDER, parameter);

						middlewareMessagesInput = middlewareMessagesInput
								.concat(stream);

					}

				}
			}

		} else if (message instanceof MessageUbw32Analog) {

			for (Entry<String, String> entry : impl.getEndpointUbw32().re420MapAnalog
					.getMap().entrySet()) {

				String key = entry.getKey();

				if (!key.isEmpty()) {
					String stream = mapMiddlewareMessages.getMap().get(key);

					if (stream == null) {
						try {
							throw new Exception(
									"mapping error found no global id in middleware message with key: "
											+ entry.getKey() + " and value: "
											+ entry.getValue()
											+ " in message: " + message);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					}

					// convert input to common parameter
					String parameter = message.getInputValue(entry.getValue(),
							"");

					stream = stream.replaceAll(
							MessageCommon.PARAMETER_PLACEHOLDER, parameter);
					middlewareMessagesInput = middlewareMessagesInput
							.concat(stream);
				}
			}
		}

		log.trace("processing middleware message: " + middlewareMessagesInput);

		return middlewareMessagesInput;
	}
	
	private String convertParameter(String channelKey, String parameter) {

		if (mapValues.getMap().containsKey(channelKey)) {

			for (Entry<String, String> element : mapValues.getMap().entrySet()) {
				if (element.getKey().equals(channelKey)) {
					// has values
					if (!element.getValue().isEmpty()) {

						// has switch elements
						if (element.getValue().contains("#")) {
							if (parameter.equals("0")) {
								// set off value as new parameter
								parameter = element.getValue().split("#")[0];
								break;
							} else if (parameter.equals("1")) {
								// set on value as new parameter
								parameter = element.getValue().split("#")[1];
								break;
							}
						}
					}
				}
			}
		}

		return parameter;

	}
}

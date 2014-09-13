package ch.desm.middleware.communication.message.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.message.MessageMiddleware;
import ch.desm.middleware.communication.message.MessageUbw32Base;
import ch.desm.middleware.communication.message.MessageUbw32DigitalRegisterComplete;
import ch.desm.middleware.component.ComponentBase;
import ch.desm.middleware.component.cabine.re420.Re420;
import ch.desm.middleware.component.cabine.re420.maps.Re420MapFabischValue;
import ch.desm.middleware.component.interlocking.obermattlangnau.OML;
import ch.desm.middleware.component.petrinet.obermattlangnau.OMLPetriNet;
import ch.desm.middleware.component.simulation.locsim.Locsim;
import ch.desm.middleware.component.simulation.locsim.elements.LocsimElementFahrschalter;
import ch.desm.middleware.component.simulation.locsim.maps.LocsimMapRs232;

/**
 * 
 * @author Sebastian
 * 
 */
public class MessageProcessor extends MessageProcessorBase {

	private static Logger log = Logger.getLogger(MessageProcessor.class);
	private LocsimElementFahrschalter fahrschalter;
	private Re420MapFabischValue mapValues;

	public MessageProcessor() {
		fahrschalter = new LocsimElementFahrschalter();
		mapValues = new Re420MapFabischValue();
	}

	/**
	 * 
	 * @param component
	 * @param message
	 */
	public void processEndpointMessage(ComponentBase component, String message,
			String topic) {
		if (message != null && !message.isEmpty()) {
			component.publish(message, topic);
		}
	}

	/**
	 * 
	 * @param impl
	 * @param messages
	 */
	public void processBrokerMessage(Locsim impl,
			ArrayList<MessageMiddleware> messages) {

		for (MessageMiddleware message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	/**
	 * 
	 * @param impl
	 * @param messages
	 */
	public void processBrokerMessage(OMLPetriNet impl,
			ArrayList<MessageMiddleware> messages) {

		for (MessageMiddleware message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	/**
	 * 
	 * @param impl
	 * @param messages
	 */
	public void processBrokerMessage(Re420 impl,
			ArrayList<MessageMiddleware> messages) {

		for (MessageMiddleware message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	/**
	 * 
	 * @param impl
	 * @param messages
	 */
	public void processBrokerMessage(OML impl,
			ArrayList<MessageMiddleware> messages) {

		for (MessageMiddleware message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	/**
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(OMLPetriNet impl,
			MessageMiddleware message) {
		impl.processBrokerMessage(message);
	}

	/**
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(Locsim impl,
			MessageMiddleware message) {

		if (impl.isLocsimDllMessage(message.getGlobalId())) {

			// TODO implementation of dll messages
			// ....
		}

		else if (impl.isLocsimSoftwareMessage(message.getOutputInput())) {

			// send locsim interface ready to start simulation
			if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini1")) {
				// init message
			}

			else if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini2")) {
				impl.getEndpointRs232().sendMessage("INI2");
			}

			else if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini7")) {
				// wait for polling - nothing to do
			}
		}

		else {

			// delegate needed fahrschalter messages
			if (fahrschalter.getMap().containsValue(message.getGlobalId())) {

				// find locsim needed keys
				HashMap<String, String> fahrschalterKeys = fahrschalter
						.getLocsimNeededKeys(message.getGlobalId());

				if (!fahrschalterKeys.isEmpty()) {

					for (Entry<String, String> element : fahrschalterKeys
							.entrySet()) {

						String channelData = element.getKey();
						if (channelData.isEmpty()) {
							log.warn("no locsim mapping with message: "
									+ message);
						}
						String channelType = channelData.substring(0, 1);
						String channel = channelData.substring(1, 3);
						String parameter = "0000";
						String locsimCommand = "X" + channelType + channel
								+ parameter + "Y";

						impl.getEndpointRs232().sendMessage(locsimCommand);
					}
				}

				String channelData = fahrschalter.getKey(message.getGlobalId());
				if (channelData.isEmpty()) {
					log.warn("no locsim mapping with message: " + message);
				}
				String channelType = channelData.substring(0, 1);
				String channel = channelData.substring(1, 3);
				String parameter = getParameterValueLocsim(message.getParameter());
				String locsimCommand = "X" + channelType + channel + parameter
						+ "Y";

				impl.getEndpointRs232().sendMessage(locsimCommand);

				// no fahrschalter command
			} else {

				LocsimMapRs232 locsimMap = new LocsimMapRs232();
				String channelData = locsimMap.getKey(message.getGlobalId());

				if (channelData.isEmpty()) {
					try {
						throw new Exception("processBrokerMessage: " + message
								+ ", has no mapping");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error(e);
					}
				} else {

					String parameter = message.getParameter();
					String channelType = channelData.substring(0, 1);
					String channel = channelData.substring(1, 3);

					// conversion Hauptleitung, Bremszylinder pressure
					if (channelData.equals("V00") || channelData.equals("V01")) {

						parameter = conversionFahrschalter(parameter);

					} else if (channelData.equals("V02")) {

						parameter = conversionFahrschalter(parameter);

					} else if (channelData.equals("V03")) {

					}

					else {

						parameter = getParameterValueLocsim(parameter);
					}

					if (channelData.isEmpty()) {
						log.warn("no locsim mapping with message: " + message);
					}

					String locsimCommand = "X" + channelType + channel
							+ parameter + "Y";

					impl.getEndpointRs232().sendMessage(locsimCommand);

				}
			}
		}
	}

	private String conversionFahrschalter(String parameter) {
		double x = Double.valueOf(parameter);
		x = (x - 180) / 100;
		// (x^2)/8

		double locsimValue = Math.sqrt(Math.pow(x, 3)); // ((Math.pow(x, 3)) /
														// 100);
		locsimValue *= 100;
		if (locsimValue < 0) {
			locsimValue = 0;
		} else if (locsimValue > 255) {
			locsimValue = 255;
		}

		String locsimParameter = Integer.toHexString((int) locsimValue);

		while (locsimParameter.length() < 4) {
			locsimParameter = locsimParameter + "0";
		}

		log.trace("x: " + x + ", locsimValue: " + locsimValue
				+ ", locsimParameter: " + locsimParameter);

		return locsimParameter;
	}

	boolean init1 = false;

	/**
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(Re420 impl,
			MessageMiddleware message) {

		// is fabisch endpoint digital message
		if (impl.getEndpointFabisch().getMapDigital()
				.isKeyAvailable(message.getGlobalId())) {

			String channel = impl.getEndpointFabisch().getMapDigital()
					.getValue(message.getGlobalId());
			String data = message.getParameter();

			data = convertParameter(channel, data);
			
			impl.getEndpointFabisch().sendStream(channel + data);

			// is fabisch endpoint analog message
		} else if (impl.getEndpointFabisch().getMapAnalog()
				.isKeyAvailable(message.getGlobalId())) {
			String channel = impl.getEndpointFabisch().getMapAnalog()
					.getValue(message.getGlobalId());
			String data = message.getParameter();
			
			//TODO convert from locsim values to fabisch values
			
			impl.getEndpointFabisch().sendStream(channel + data);
		}
		// is software message
		else if (message.getOutputInput().equalsIgnoreCase(
				MessageUbw32Base.MESSAGE_CHAR_ONLYSOFTWARE)) {

			if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini1")
					&& !init1) {
				impl.getEndpointUbw32().setCacheEnabled(false);
				impl.getEndpointUbw32().startPolling();
				init1 = true;
			}

			else if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini2")) {
				// nothing to do
			}

			else if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini7")) {
				impl.getEndpointUbw32().pollingCommand();
				impl.getEndpointUbw32().setCacheEnabled(false);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				impl.getEndpointUbw32().setCacheEnabled(true);
			}

			else if (message.getGlobalId().equalsIgnoreCase(
					"locsim.initialization.ready.ini8")) {
				// nothing to do
			}

			// is hardware message
		} else {

			String value = getParameterValueRe420(message.getParameter());
			boolean isInput = message.getOutputInput().equals(
					MessageUbw32Base.MESSAGE_CHAR_INPUT);

			// is digital message
			if (impl.getEndpointUbw32().getMapDigital()
					.isKeyAvailable(message.getGlobalId())) {

				String endpointRegister = impl.getEndpointUbw32()
						.getMapDigital().getMap().get(message.getGlobalId());
				String registerName = String
						.valueOf(endpointRegister.charAt(0));
				String pin = String.valueOf(endpointRegister.charAt(1));

				if (isInput) {
					impl.getEndpointUbw32().getPinInputDigital(registerName,
							pin);
				} else {
					impl.getEndpointUbw32().setPinOutputDigital(registerName,
							pin, value);
				}

				// is analog message
			} else if (impl.getEndpointUbw32().getMapAnalog()
					.isKeyAvailable(message.getGlobalId())) {

				String endpointRegister = impl.getEndpointUbw32()
						.getMapAnalog().getMap().get(message.getGlobalId());

				if (isInput) {
					impl.getEndpointUbw32().getPinInputAnalog(endpointRegister);
				}

			} else {
				log.warn(impl.getClass() + "> processBrokerMessage skipped:"
						+ message);
			}
		}
	}

	/**
	 * TODO implementation
	 * 
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(OML impl,
			MessageMiddleware message) {

	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private String getParameterValueLocsim(String value) {
		String returnValue = "";

		if (value
				.equals(MessageUbw32DigitalRegisterComplete.MESSAGE_PARAMETER_OFF)) {
			returnValue = "0000";
		} else if (value
				.equals(MessageUbw32DigitalRegisterComplete.MESSAGE_PARAMETER_ON)) {
			returnValue = "0001";
		} else {
			returnValue = value;
			if (returnValue.length() < 4) {
				while (returnValue.length() < 4) {
					returnValue = "0" + returnValue;
				}
			}
		}
		return returnValue.toUpperCase();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String getParameterValueRe420(String value) {
		String returnValue = String.valueOf(Integer.parseInt(value, 16));

		return returnValue.toUpperCase();
	}

	private String convertParameter(String channelKey, String parameter) {

		if (mapValues.getMap().containsKey(channelKey)) {

			for (Entry<String, String> element : mapValues.getMap().entrySet()) {
				if (element.getKey().equals(channelKey)) {
					// has values
					if (!element.getValue().isEmpty()) {

						// has switch elements
						if (element.getValue().contains("#")) {
							if (parameter.equals("on")) {
								// set off value as new parameter
								parameter = element.getValue().split("#")[0];
							} else if (parameter.equals("off")) {
								// set on value as new parameter
								parameter = element.getValue().split("#")[1];
							}
						}
					}
				}
			}
		}

		return parameter;

	}
}

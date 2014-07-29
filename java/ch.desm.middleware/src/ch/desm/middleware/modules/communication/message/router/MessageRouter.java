package ch.desm.middleware.modules.communication.message.router;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.type.MessageMiddleware;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32Digital;
import ch.desm.middleware.modules.component.ComponentBase;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.interlocking.OMLBaseImpl;
import ch.desm.middleware.modules.component.simulation.locsim.LocsimBaseImpl;
import ch.desm.middleware.modules.component.simulation.locsim.maps.LocsimMapRs232;

/**
 * 
 * @author Sebastian
 * 
 */
public class MessageRouter {

	public MessageRouter() {
	}

	/**
	 * 
	 * @param component
	 * @param message
	 */
	public void processEndpointMessage(ComponentBase component, String message, String topic) {
		if (message != null && !message.isEmpty()) {
			component.publish(message, topic);
		}
	}

	/**
	 * 
	 * @param impl
	 * @param messages
	 */
	public void processBrokerMessage(LocsimBaseImpl impl,
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
	public void processBrokerMessage(Re420BaseImpl impl,
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
	public void processBrokerMessage(OMLBaseImpl impl,
			ArrayList<MessageMiddleware> messages) {

		for (MessageMiddleware message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	/**
	 * TODO implementation
	 * 
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(LocsimBaseImpl impl,
			MessageMiddleware message) {
		
		if (impl.hasTopicSigned(message.getTopic())) {
			
			if(impl.isLocsimDllMessage(message.getGlobalId())){
				
				//TODO implementation
				//....
			}else{
				
				LocsimMapRs232 locsimMap = new LocsimMapRs232();
				String channelData = locsimMap.getKey(message.getGlobalId());
				String channelType = channelData.substring(0);
				String channel = channelData.substring(1, 2);
				String parameter = "000" + getParameterValue(message.getParameter());
				String locsimCommand = "X" + channelType + channel + parameter + "Y";
				
				impl.getEndpointRs232().sendMessage(locsimCommand);
			}
			
		} else {
			System.out.println(impl.getClass()
					+ "> processBrokerMessage skipped:" + message);
		}
	}

	/**
	 * TODO refactoring
	 * 
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(Re420BaseImpl impl,
			MessageMiddleware message) {

		if (impl.hasTopicSigned(message.getTopic())) {
			
			String value = getParameterValue(message.getParameter());
			boolean isInput = message.getOutputInput().equals(
					MessageUbw32Digital.MESSAGE_CHAR_INPUT);

			if (impl.getEndpoint().getMapDigital()
					.isKeyAvailable(message.getGlobalId())) {
				System.out.println(impl.getClass() + ">processBrokerMessage:"
						+ message);

				EnumEndpointUbw32RegisterDigital endpointRegister = impl
						.getEndpoint().getMapDigital().getMap()
						.get(message.getGlobalId());
				String registerName = String.valueOf(endpointRegister.name()
						.charAt(0));
				String pin = String.valueOf(endpointRegister.name().charAt(1));

				if (isInput) {
					impl.getFunction(registerName, pin);
				} else {
					impl.setFunction(registerName, pin, value);
				}
			} else {
				System.out.println(impl.getClass()
						+ "> processBrokerMessage skipped:" + message);
			}
		}
	}

	/**
	 * TODO refactoring
	 * 
	 * @param impl
	 * @param message
	 */
	private void processBrokerMessage(OMLBaseImpl impl,
			MessageMiddleware message) {

		if (impl.hasTopicSigned(message.getTopic())) {

			String value = getParameterValue(message.getParameter());
			boolean isInput = message.getOutputInput().equals(
					MessageUbw32Digital.MESSAGE_CHAR_INPUT);

			if (impl.getEndpoint().getConfiguration()
					.isKeyAvailable(message.getGlobalId())) {
				System.out.println(impl.getClass() + ">processBrokerMessage:"
						+ message);

				EnumEndpointUbw32RegisterDigital endpointRegister = impl
						.getEndpoint().getConfiguration().getMapInputDigital()
						.get(message.getGlobalId());
				String registerName = String.valueOf(endpointRegister.name()
						.charAt(0));
				String pin = String.valueOf(endpointRegister.name().charAt(1));

				if (isInput) {
					impl.getFunction(registerName, pin);
				} else {
					impl.setFunction(registerName, pin, value);
				}
			} else {
				System.out.println(impl.getClass()
						+ "> processBrokerMessage skipped:" + message);
			}
		}
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private String getParameterValue(String value) {
		String returnValue = "";

		switch (value) {
		case MessageUbw32Digital.MESSAGE_PARAMETER_OFF: {
			returnValue = "1";
			break;
		}
		case MessageUbw32Digital.MESSAGE_PARAMETER_ON: {
			returnValue = "0";
			break;
		}
		}
		return returnValue;
	}
}

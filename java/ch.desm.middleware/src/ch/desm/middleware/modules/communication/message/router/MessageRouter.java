package ch.desm.middleware.modules.communication.message.router;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32PortDigital.EnumEndpointUbw32RegisterDigital;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.communication.message.type.MessageUbw32;
import ch.desm.middleware.modules.component.ComponentBase;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.interlocking.OMLBaseImpl;
import ch.desm.middleware.modules.component.test.TestBaseImpl;

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
	 * TODO Implementation
	 * 
	 * @param component
	 * @param message
	 */
	public void processEndpointMessage(ComponentBase component, String message) {
		component.publish(message);
	}

	public void processBrokerMessage(TestBaseImpl impl,
			ArrayList<MessageCommon> messages) {
		for (MessageCommon message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	public void processBrokerMessage(Re420BaseImpl impl,
			ArrayList<MessageCommon> messages) {
		for (MessageCommon message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	public void processBrokerMessage(OMLBaseImpl impl,
			ArrayList<MessageCommon> messages) {
		for (MessageCommon message : messages) {
			this.processBrokerMessage(impl, message);
		}
	}

	private void processBrokerMessage(TestBaseImpl impl, MessageCommon message) {

		String value = getParameterValue(message.getParameter());
		
		boolean isInput = message.getOutputInput().equals(
				MessageUbw32.MESSAGE_CHAR_INPUT);

		if (impl.getEndpoint().getConfiguration()
				.isKeyAvailable(message.getGlobalId())) {
			System.out.println(impl.getClass() + ">processBrokerMessage:"
					+ message);

			EnumEndpointUbw32RegisterDigital endpointRegister = impl
					.getEndpoint().getConfiguration().getMapInputDigital()
					.get(message.getGlobalId());
			String registerName = String.valueOf(endpointRegister.name().charAt(0));
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

	private void processBrokerMessage(Re420BaseImpl impl, MessageCommon message) {

		String value = getParameterValue(message.getParameter());
		boolean isInput = message.getOutputInput().equals(
				MessageUbw32.MESSAGE_CHAR_INPUT);

		if (impl.getEndpoint().getConfiguration()
				.isKeyAvailable(message.getGlobalId())) {
			System.out.println(impl.getClass() + ">processBrokerMessage:"
					+ message);

			EnumEndpointUbw32RegisterDigital endpointRegister = impl
					.getEndpoint().getConfiguration().getMapInputDigital()
					.get(message.getGlobalId());
			String registerName = String.valueOf(endpointRegister.name().charAt(0));
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

	private void processBrokerMessage(OMLBaseImpl impl, MessageCommon message) {

		String value = getParameterValue(message.getParameter());
		boolean isInput = message.getOutputInput().equals(
				MessageUbw32.MESSAGE_CHAR_INPUT);

		if (impl.getEndpoint().getConfiguration()
				.isKeyAvailable(message.getGlobalId())) {
			System.out.println(impl.getClass() + ">processBrokerMessage:"
					+ message);

			EnumEndpointUbw32RegisterDigital endpointRegister = impl
					.getEndpoint().getConfiguration().getMapInputDigital()
					.get(message.getGlobalId());
			String registerName = String.valueOf(endpointRegister.name().charAt(0));
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
	
	/**
	 * TODO refactoring move to translator
	 * @param value
	 * @return
	 */
	private String getParameterValue(String value){
		String returnValue = "";
		
		switch(value){
			case MessageUbw32.MESSAGE_PARAMETER_OFF:{
				returnValue = "1";
				break;
			}
			case MessageUbw32.MESSAGE_PARAMETER_ON:{
				returnValue = "0";
				break;
			}
		}
		return returnValue;
	}
}

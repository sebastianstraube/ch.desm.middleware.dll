package ch.desm.middleware.modules.communication.message.router;

import java.util.ArrayList;

import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.interlocking.OMLBaseImpl;
import ch.desm.middleware.modules.component.interlocking.OMLEndpointUbw32;

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

	private void processBrokerMessage(Re420BaseImpl impl,
			MessageCommon message) {

//		System.out.println(impl.getClass() + "> processBrokerMessage:" + message);

		if (impl.getEndpoint().getConfiguration().isKeyAvailable(message.getGlobalId())) {
			String register = impl.getEndpoint().getConfiguration().mapDigital
					.get(message.getGlobalId());
			String port = register.substring(0, 1);
			String pin = register.substring(1, register.length());
			String value = message.getParameter();
			boolean isReturnMessage = message.isReturnMessage();

			if (isReturnMessage) {
				impl.setFunction(port, pin, value);
			} else {
				impl.getFunction(port, pin);
			}
		} else {
			System.out.println(impl.getClass() + "> processBrokerMessage skipped:" + message);
		}
	}

	private void processBrokerMessage(OMLBaseImpl impl,
			MessageCommon message) {

		String value = message.getParameter();
		boolean isReturnMessage = message.isReturnMessage();

		if (impl.getEndpoint().getConfiguration().isKeyAvailable(message.getGlobalId())) {

			System.out.println(impl.getClass() + ">processBrokerMessage:" + message);

			String register = impl.getEndpoint().getConfiguration().mapDigital
					.get(message.getGlobalId());
			String port = register.substring(0, 1);
			String pin = register.substring(1, register.length());

			if (isReturnMessage) {
				impl.setFunction(port, pin, value);
			} else {
				impl.getFunction(port, pin);
			}
		} else {
			System.out.println(impl.getClass() + "> processBrokerMessage skipped:" + message);
		}
	}
}

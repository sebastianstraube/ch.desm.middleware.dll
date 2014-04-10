package ch.desm.middleware.modules.component;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.message.MessageBase;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;

/**
 * 
 * @author Sebastian
 * 
 */
public abstract class ComponentBase extends BrokerClient implements
		EndpointBaseListenerInterface {

	abstract protected void registerEndpointListener(EndpointBase listener);

	abstract public void emulateBrokerMessage(MessageBase message);

	private static double id = 0;

	public static enum EnumComponentCategorie {
		SIMULATION, INTERLOCKING, CABINE, CORBA
	}

	public static enum EnumComponentType {
		CABINE_RE420, INTERLOCKING_OBERMATTLANGNAU, SIMULATION_LOCSIM
	}

	public ComponentBase(Broker broker) {
		super(broker);
		id++;
	}

	/**
	 * TODO implementation
	 * @param message
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());

		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, message);
	}

	public double getId() {
		return id;
	}
}

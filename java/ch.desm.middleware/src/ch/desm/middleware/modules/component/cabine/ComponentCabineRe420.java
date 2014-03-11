package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessageInterface;
import ch.desm.middleware.modules.component.ComponentAbstract;

public class ComponentCabineRe420 extends ComponentAbstract {

	public ComponentCabineRe420(CommunicationBrokerHandler broker) {
		super(broker);
	}

	public String getType() {
		return enumComponentType.CABINE.name();
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.INTERLOCKING.name(),
				enumComponentType.SIMULATION.name());
	}

	@Override
	protected void onBrokerMessage(CommunicationBrokerMessageInterface message) {
		System.out.println("received a broker message:" + message + " in class" + this.getClass());
	}

}

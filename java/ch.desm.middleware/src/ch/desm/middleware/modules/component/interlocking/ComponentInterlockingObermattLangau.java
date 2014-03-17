package ch.desm.middleware.modules.component.interlocking;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerHandler;
import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;
import ch.desm.middleware.modules.component.ComponentAbstract;


public class ComponentInterlockingObermattLangau extends ComponentAbstract{
	
	public ComponentInterlockingObermattLangau(CommunicationBrokerHandler broker){
		super(broker);
	}

	@Override
	public String getType() {
		return enumComponentType.INTERLOCKING.name();
	}

	@Override
	public List<String> getRequiredTypes() {
		return Arrays.asList(enumComponentType.SIMULATION.name(), enumComponentType.CABINE.name());
	}

	@Override
	protected void onBrokerMessage(CommunicationBrokerMessage message) {
		System.out.println("received a broker message:" + message + " in class" + this.getClass());
	}

}

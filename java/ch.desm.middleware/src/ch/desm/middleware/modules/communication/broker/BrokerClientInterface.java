package ch.desm.middleware.modules.communication.broker;

import java.util.List;

import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;


public interface BrokerClientInterface {
	
	public EnumComponentType getType();
    public List<String> getRequiredTypes();
}

package ch.desm.middleware.modules.communication.broker;

import java.util.List;

import ch.desm.middleware.modules.component.ComponentBase.EnumComponentCategorie;


public interface BrokerClientInterface {
	
	public EnumComponentCategorie getType();
    public List<String> getRequiredTypes();
}

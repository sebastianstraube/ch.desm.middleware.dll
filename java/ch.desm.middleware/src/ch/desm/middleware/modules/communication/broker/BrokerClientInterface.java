package ch.desm.middleware.modules.communication.broker;

import java.util.List;


public interface BrokerClientInterface {
	
	public String getType();
    public List<String> getRequiredTypes();
}

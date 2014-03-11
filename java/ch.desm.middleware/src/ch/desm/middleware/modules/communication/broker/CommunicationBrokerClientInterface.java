package ch.desm.middleware.modules.communication.broker;

import java.util.List;


public interface CommunicationBrokerClientInterface {
	
	public String getType();
    public List<String> getRequiredTypes();
}

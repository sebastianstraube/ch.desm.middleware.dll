package ch.desm.middleware.modules.service;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.CommunicationBrokerClient;
import ch.desm.middleware.modules.component.ComponentAbstract;

/**
 * 
 * @author Sebastian
 *
 */
public class CommunicationHandlerService {

	private BrokerHandlerService brokerHandlerService;
	private ComponentHandlerService componentHandlerService;
	
	public CommunicationHandlerService(ComponentHandlerService componentHandlerService){
		this.brokerHandlerService = new BrokerHandlerService();
		this.componentHandlerService = componentHandlerService;
				
		this.initialize();
	}
	
	private void initialize(){
		connectComponents(componentHandlerService.getComponentList());
	}
		
	private void connectComponents(List<ComponentAbstract> componentList){
		for(CommunicationBrokerClient component : componentList){
			brokerHandlerService.getBroker().connect(component);
		}	
	}	
}
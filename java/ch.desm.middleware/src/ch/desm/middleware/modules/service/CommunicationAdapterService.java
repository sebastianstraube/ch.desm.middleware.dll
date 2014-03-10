package ch.desm.middleware.modules.service;

import java.util.List;

import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.component.ComponentAbstract;

/**
 * 
 * @author Sebastian
 *
 */
public class CommunicationAdapterService {

	private BrokerHandlerService brokerService;
//	private EventHandlerService eventHandlerService;
	private ComponentHandlerService componentHandlerService;
	
	public CommunicationAdapterService(ComponentHandlerService componentHandlerService){
		brokerService = new BrokerHandlerService();
//		eventHandlerService = new EventHandlerService();
		this.componentHandlerService = componentHandlerService;
				
		this.initialize();
	}
	
	private void initialize(){
		connectComponents(componentHandlerService.getComponentList());
	}
		
	private void connectComponents(List<ComponentAbstract> componentList){
		for(BrokerClient component : componentList){
			brokerService.getBroker().connect(component);
		}	
	}

//	for(SerialPort serialPort : communicationController.getSerialPorts()){
//	Rs232EventListener eventListener = new Rs232EventListener(components, serialPort);
//	this.eventListenerService.addEventListener(eventListener);
//	
//	//Add an interface through which we will receive information about events
//    //port.addEventListener(new Rs232EventListener(port));
//	try {
//		serialPort.addEventListener(eventListener);
//	} catch (SerialPortException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}

	
}
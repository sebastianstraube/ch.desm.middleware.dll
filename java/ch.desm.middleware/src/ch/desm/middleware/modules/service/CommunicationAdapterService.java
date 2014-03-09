package ch.desm.middleware.modules.service;

import java.util.List;

import ch.desm.middleware.modules.component.ComponentAbstract;


public class CommunicationAdapterService {

	private BrokerService brokerService;
	private EventHandlerService eventHandlerService;
	
	public CommunicationAdapterService(){
		brokerService = new BrokerService();
		eventHandlerService = new EventHandlerService();
		
		this.initialize();
	}
	
	private void initialize(){
		
	}
	
	public void connectComponents(List<ComponentAbstract> componentList){
		
		isComponentListCompatible(componentList);
		registerEventHandlerObjects(componentList);
	}
	
	private void isComponentListCompatible(List<ComponentAbstract> componentList){
		
		boolean isRequiredTypeExisting = false;
		String missingComponentType = "";
		
		for(ComponentAbstract component : componentList){
			for(String requiredComponentType : component.getRequiredTypes()){
				if(component.getType().equals(requiredComponentType)){
					isRequiredTypeExisting = true;
					break;
				}
			}
			
			if(!isRequiredTypeExisting){
				missingComponentType = component.getType();
				break;
			}
		}
		
		if(!isRequiredTypeExisting){
			try {
				throw new Exception("The list of components is not compatible, a required compenent typ is missing. The type is: " + missingComponentType +" .");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	private void registerEventHandlerObjects(List<ComponentAbstract> componentList){
		
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
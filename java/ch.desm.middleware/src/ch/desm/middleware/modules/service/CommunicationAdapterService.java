package ch.desm.middleware.modules.service;

import ch.desm.middleware.modules.communication.broker.BrokerClient;

public class CommunicationAdapterService {

	private BrokerService brokerService;
	
	public CommunicationAdapterService(){
		brokerService = new BrokerService();
		
		this.initialize();
	}
	
	private void initialize(){
		
	}
	
	public void addBrokerClient(BrokerClient client){
		brokerService.getBroker().connect(client);
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
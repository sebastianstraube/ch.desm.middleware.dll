package ch.desm.middleware.modules.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import ch.desm.middleware.modules.communication.controller.Rs232;
import ch.desm.middleware.modules.components.ComponentsAbstract;
import ch.desm.middleware.modules.core.listener.Rs232EventListener;

public class CommunicationAdapterService {

	private Rs232 communicationController;
	private ComponentsAbstract components;
	private EventListenerService eventListenerService;
	
	public CommunicationAdapterService(Rs232 communicationController, ComponentsAbstract components){
		this.communicationController = communicationController;
		this.components = components;
		this.initialize();
	}
	
	private void initialize(){
		this.eventListenerService = new EventListenerService();		 
		this.communicationController.initialize();
		this.components.initialize();
		
		for(SerialPort serialPort : communicationController.getSerialPorts()){
			Rs232EventListener eventListener = new Rs232EventListener(serialPort);
			this.eventListenerService.addEventListener(eventListener);
			
			//Add an interface through which we will receive information about events
		    //port.addEventListener(new Rs232EventListener(port));
			try {
				serialPort.addEventListener(eventListener);
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

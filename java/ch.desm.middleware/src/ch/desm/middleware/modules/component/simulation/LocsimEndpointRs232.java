package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDllListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232;

public class LocsimEndpointRs232 extends EndpointRs232{

	public LocsimEndpointRs232(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort);
		// TODO Auto-generated constructor stub
	}

	public void setStufenschalter(String position){
		for (EndpointBaseListenerInterface listener : super.listeners) {
			
			try{
				if (listener instanceof LocsimListenerDll) {
					LocsimListenerDll dllListener = 
							(LocsimListenerDll) listener;
	
					System.out.println("LocsimListenerDll");

				}else if(listener instanceof LocsimListenerRs232){
					LocsimListenerRs232 dllListener = 
							(LocsimListenerRs232) listener;
					
					dllListener.onStufenschalter(1, position);
					
				}else {
					throw new Exception("only dll endpoints supported");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

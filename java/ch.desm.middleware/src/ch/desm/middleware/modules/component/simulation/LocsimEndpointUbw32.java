package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDllListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;

public class LocsimEndpointUbw32 extends EndpointUbw32{

	public LocsimEndpointUbw32(EnumSerialPorts enumSerialPort) {
		super(enumSerialPort);
		// TODO Auto-generated constructor stub
	}

	/**
	 * TODO implementation 
	 * 
	 * @param weicheId
	 * @param on
	 */
	public void stw_setWeiche(int weicheId, boolean on) {
		// call desm dll stuff here...
		// adding these explicit calls for test purpose only:
		for (EndpointBaseListenerInterface listener : super.listeners) {
			
			try{
				if (listener instanceof EndpointDesmDllListenerInterface) {
					EndpointDesmDllListenerInterface dllListener = 
							(EndpointDesmDllListenerInterface) listener;
	
					if (on) {
						dllListener.onSignalAn(6);
						dllListener.onZugPositionChanged(1., 2., 3.);
					} else {
						dllListener.onSignalAus(6);
						dllListener.onZugPositionChanged(3, 2, 1);
					}
				}else {
					throw new Exception("only dll endpoints supported");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setStufenschalter(String position){
		for (EndpointBaseListenerInterface listener : super.listeners) {
			
			try{
				if (listener instanceof LocsimImplUbw32){
					LocsimImplUbw32 listenerRs232 = 
							(LocsimImplUbw32) listener;
	
					System.out.println("message: stufenschalter." + position + " delivered, endpoint: " + listenerRs232.getClass());
					((LocsimImplUbw32) listener).onStufenschalter(1, position);
					
				}else {
					System.err.println("message not delivered, endpoint not defined.");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

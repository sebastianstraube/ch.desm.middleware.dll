package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.MessageCommon;
import ch.desm.middleware.modules.communication.broker.message.MessageCommon.EnumMessageType;
import ch.desm.middleware.modules.communication.broker.message.type.component.interlocking.MessageSignalLampeAn;
import ch.desm.middleware.modules.communication.broker.message.type.component.interlocking.MessageWeichenSchalterAn;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDll;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDllListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public class Locsim extends ComponentBase implements
		EndpointDesmDllListenerInterface, EndpointRs232ListenerInterface{

	EndpointCommon communicationEndpoint;

	public Locsim(Broker broker,
			EndpointCommon communicationEndpointDll) {
		
		super(broker);
		this.communicationEndpoint = communicationEndpointDll;
		
		this.registerEndpointListener((EndpointBase)communicationEndpointDll);
	}

	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			communicationEndpoint.addEndpointListener(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	/**
	 * TODO implementation
	 * 
	 * @param message
	 */
	protected void onIncomingBrokerMessage(MessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		if(this instanceof EndpointDesmDllListenerInterface){
			
			if(message instanceof MessageSignalLampeAn){
				((EndpointDesmDll)communicationEndpoint).stw_setWeiche(((MessageWeichenSchalterAn) message).getParameterTypeId(), true);
				
			
			}			
		}else if(this instanceof EndpointRs232ListenerInterface){
			
			System.err.println("implementation of onIncomingBrokerMessage needed" + this.getClass().getCanonicalName());
		}else{
			
			System.err.println("unknown implementation in" + this.getClass().getCanonicalName());
		}
	}

	@Override
	/**
	 * TODO implementation
	 * 
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		if(this instanceof EndpointDesmDllListenerInterface){
			
			System.err.println("implementation of onIncomingEndpointMessage needed" + this.getClass().getCanonicalName());
		}else if(this instanceof EndpointRs232ListenerInterface){
			
			System.err.println("implementation of onIncomingEndpointMessage needed" + this.getClass().getCanonicalName());
		}else{
			
			//TODO Exception
		}
		
		
	}
	
	@Override
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateEndpointMessage(String message) {
		onIncomingEndpointMessage(message);
	}

	@Override
	/**
	 * test endpoint message handling
	 * @param message
	 */
	public void emulateBrokerMessage(MessageCommon message) {
		onIncomingBrokerMessage(message);
	}
	
	public EnumComponentType getType() {
		return EnumComponentType.SIMULATION;
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentType.INTERLOCKING.name());
	}

	@Override
	public void onSignalAn(int signalId) {

		publish(new MessageSignalLampeAn(EnumMessageType.DLL, signalId));
	}

	@Override
	public void onSignalAus(int signalId) {
		
//		publish(new MessageSignalLampeAus(signalId));
		
	}

	@Override
	public void onZugPositionChanged(double x, double y, double z) {
		// TODO Auto-generated method stub
		
	}
}

package ch.desm.middleware.modules.component.simulation;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;
import ch.desm.middleware.modules.communication.broker.message.type.component.interlocking.BrokerMessageSignalLampeAn;
import ch.desm.middleware.modules.communication.broker.message.type.component.interlocking.BrokerMessageSignalLampeAus;
import ch.desm.middleware.modules.communication.broker.message.type.component.interlocking.BrokerMessageWeichenSchalterAn;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointBase;
import ch.desm.middleware.modules.communication.endpoint.CommunicationEndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDll;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDllListenerInterface;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public class Locsim extends ComponentBase implements
		EndpointDesmDllListenerInterface, EndpointRs232ListenerInterface{

	CommunicationEndpointCommon communicationEndpoint;

	public Locsim(Broker broker,
			CommunicationEndpointCommon communicationEndpointDll) {
		
		super(broker);
		this.communicationEndpoint = communicationEndpointDll;
		
		this.registerEndpointListener((CommunicationEndpointBase)communicationEndpointDll);
	}

	@Override
	protected void registerEndpointListener(
			CommunicationEndpointBase listener) {
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
	protected void onIncomingBrokerMessage(BrokerMessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		if(this instanceof EndpointDesmDllListenerInterface){
			
			if(message instanceof BrokerMessageSignalLampeAn){
				((EndpointDesmDll)communicationEndpoint).stw_setWeiche(((BrokerMessageWeichenSchalterAn) message).getParameterTypeId(), true);
				
			
			}			
		}else if(this instanceof EndpointRs232ListenerInterface){
			
			System.err.println("implementation of onIncomingBrokerMessage needed" + this.getClass().getCanonicalName());
		}else{
			
			System.err.println("unknown implementation in" + this.getClass().getCanonicalName());
		}
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());
		
		
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
	public void emulateBrokerMessage(BrokerMessageCommon message) {
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
		publish(new BrokerMessageSignalLampeAn(signalId));
		
	}

	@Override
	public void onSignalAus(int signalId) {
		publish(new BrokerMessageSignalLampeAus(signalId));
		
	}

	@Override
	public void onZugPositionChanged(double x, double y, double z) {
		// TODO Auto-generated method stub
		
	}
}

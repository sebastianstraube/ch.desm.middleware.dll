package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.message.MessageBase;

public class LocsimImplDll extends LocsimBase implements LocsimListenerDll{

	public LocsimImplDll(Broker broker, EndpointCommon communicationEndpointDll) {
		super(broker, communicationEndpointDll);
	}

	@Override
	protected void onIncomingBrokerMessage(MessageBase message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		
	}
	
	@Override
	public void onSignalAn(int signalId) {

//		publish(new MessageSignalLampeAn(EnumMessageType.DLL, signalId));
	}

	@Override
	public void onSignalAus(int signalId) {
		
//		publish(new MessageSignalLampeAus(signalId));
		
	}

	@Override
	public void onZugPositionChanged(double x, double y, double z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * TODO implementation
	 * 
	 */
	public void onIncomingEndpointMessage(String message) {
		System.out.println("received an endpoint message :\"" + message
				+ " from endpoint " + this.getClass());

	}
}

package ch.desm.middleware.modules.component.simulation;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDllListenerInterface;
import ch.desm.middleware.modules.communication.message.router.MessageRouter;

public class LocsimBaseImpl extends LocsimBase implements
		EndpointDllListenerInterface {

	public LocsimBaseImpl(Broker broker, LocsimEndpointRs232 endpointRs232, LocsimEndpointDll endpointDll) {
		super(broker, endpointRs232, endpointDll);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onIncomingEndpointMessage(String message) {
		System.out.println("endpoint received message: " + message);
		
	}

	@Override
	protected void onIncomingBrokerMessage(String message) {
		System.out.println("endpoint received broker message: " + message);
		
		MessageRouter router = new MessageRouter();
		router.processEndpointMessage(this, message);
	}
}

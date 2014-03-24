package ch.desm.middleware.modules.component.cabine;

import ch.desm.middleware.modules.communication.broker.Broker;

public class Re420ImplUbw32 extends Re420Base implements Re420ListenerUbw32 {

	public Re420ImplUbw32(Broker broker,
			Re420EndpointUbw32 communicationEndpoint) {
		super(broker, communicationEndpoint);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onHaupthahn1(String value) {
		System.out.println("onHaupthahn1 in " + this.getClass());
		
	}

}

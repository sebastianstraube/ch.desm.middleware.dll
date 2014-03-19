package ch.desm.middleware.modules.component.cabine;

import java.util.Arrays;
import java.util.List;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;
import ch.desm.middleware.modules.communication.broker.message.type.component.cabine.BrokerMessageStufenschalter;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32ListenerInterface;
import ch.desm.middleware.modules.component.ComponentBase;

public class Re420 extends ComponentBase implements
		EndpointUbw32ListenerInterface {

	EndpointUbw32 communicationEndpointUbw32;

	public Re420(Broker broker,
			EndpointUbw32 communicationEndpointUbw32) {
		super(broker);
		
		this.communicationEndpointUbw32 = communicationEndpointUbw32;
		this.registerEndpointListener((EndpointBase)communicationEndpointUbw32);
	}
	
	@Override
	protected void registerEndpointListener(
			EndpointBase listener) {
		try {
			communicationEndpointUbw32.addEndpointListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onIncomingBrokerMessage(BrokerMessageCommon message) {
		System.out.println("received a broker message:" + message
				+ " from component " + this.getClass());
		
		if(message instanceof BrokerMessageStufenschalter) {
			BrokerMessageStufenschalter stufenSchalter = (BrokerMessageStufenschalter)message;
            System.out.println(this.getClass().getCanonicalName() + ": stufenschalter an " + stufenSchalter.getParameterTypeId());
        } else {
            System.err.println("unknown message: "+ message.toString());
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
		return EnumComponentType.CABINE;
	}

	public List<String> getRequiredTypes() {
		return Arrays.asList(EnumComponentType.INTERLOCKING.name(),
				EnumComponentType.SIMULATION.name());
	}
}

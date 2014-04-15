package ch.desm.middleware.modules.component;

import java.util.Map;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.BrokerClient;
import ch.desm.middleware.modules.communication.endpoint.EndpointBase;
import ch.desm.middleware.modules.communication.endpoint.EndpointBaseListenerInterface;

/**
 * 
 * @author Sebastian
 * 
 */
public abstract class ComponentBase extends BrokerClient implements
		EndpointBaseListenerInterface {

	abstract protected void registerEndpointListener(EndpointBase listener);
	abstract public void emulateBrokerMessage(String message);
	
	abstract public Map<String, String> getInpuDigitalOn();
	abstract public Map<String, String> getInpuAnalogOn();
	
	
	private static double id = 0;

	public static enum EnumComponentCategorie {
		SIMULATION, INTERLOCKING, CABINE, CORBA
	}

	public ComponentBase(Broker broker) {
		super(broker);
		this.initialize();
	}
	
	private void initialize(){
		id++;
	}

	public double getId() {
		return id;
	}
}

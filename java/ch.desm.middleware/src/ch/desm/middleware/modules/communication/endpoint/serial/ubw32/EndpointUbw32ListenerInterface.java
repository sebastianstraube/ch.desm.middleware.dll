package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;

import ch.desm.middleware.modules.communication.endpoint.EndpointCommonListenerInterface;



public interface EndpointUbw32ListenerInterface extends EndpointCommonListenerInterface{

	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void setFunction(String port, String pin, String value);

	/**
	 * 
	 * @param port
	 * @param pin
	 * @param value
	 */
	public void getFunction(String port, String pin);

}
package ch.desm.middleware.communication.endpoint;

import ch.desm.middleware.handle.DaemonThreadBase;

/**
 * 
 * @author Sebastian
 * 
 */
public abstract class EndpointThreadBase extends DaemonThreadBase {

	public EndpointThreadBase(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * starts the thread
	 */
	public abstract void run();
}
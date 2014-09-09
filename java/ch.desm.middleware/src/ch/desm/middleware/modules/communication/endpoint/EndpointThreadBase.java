package ch.desm.middleware.modules.communication.endpoint;

import ch.desm.middleware.modules.core.daemon.DaemonThread;

/**
 * 
 * @author Sebastian
 * 
 */
public abstract class EndpointThreadBase extends DaemonThread {

	public EndpointThreadBase(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * starts the thread
	 */
	public abstract void run();
}
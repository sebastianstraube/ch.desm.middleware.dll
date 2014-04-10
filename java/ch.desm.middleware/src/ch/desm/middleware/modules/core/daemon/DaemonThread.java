package ch.desm.middleware.modules.core.daemon;

/**
 * Abstract Thread Daemon
 * 
 * @author Sebastian
 *
 */
public abstract class DaemonThread extends Thread {

	public DaemonThread(String name) {
		this.setName(name);
		setDaemon(true);
	}

}
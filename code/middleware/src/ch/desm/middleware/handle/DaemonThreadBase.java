package ch.desm.middleware.handle;


/**
 * Abstract Thread Daemon
 * 
 * @author Sebastian
 *
 */
public abstract class DaemonThreadBase extends Thread {

	public DaemonThreadBase(String name) {
		this.setName(name);
		setDaemon(true);
	}
}
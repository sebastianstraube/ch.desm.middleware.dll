package ch.desm.middleware.communication.endpoint.serial.fabisch;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.endpoint.dll.EndpointDllThread;
import ch.desm.middleware.handle.DaemonThreadBase;

/**
 * Inherits the Daeon class to execute polling
 * @author Sebastian
 *
 */
class EndpointFabischThread extends DaemonThreadBase {
	
	public static final int THREAD_SLEEP = 256;
	
	private static Logger log = Logger.getLogger(EndpointDllThread.class);
	private EndpointFabisch endpoint;
	
	EndpointFabischThread(EndpointFabisch endpoint) {
		super("EndpointFabischPolling (" + endpoint.getSerialPortName()+")");
		
		this.endpoint = endpoint;
	}	
	
	@Override
	public void run() {
		try {

			while (!isInterrupted()) {
				Thread.sleep(THREAD_SLEEP);
				log.trace("Polling Thread active: " + this.getName() + " wait time: " + THREAD_SLEEP);
				endpoint.pollingCommand();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}

}

package ch.desm.middleware.communication.endpoint.serial.ubw32;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.endpoint.dll.EndpointDllThread;
import ch.desm.middleware.handle.DaemonThreadBase;

/**
 * Inherits the Daeon class to execute polling
 * @author Sebastian
 *
 */
class EndpointUbw32Thread extends DaemonThreadBase {
	
	private static Logger log = Logger.getLogger(EndpointDllThread.class);
	
	private EndpointUbw32Impl endpoint;
	
	EndpointUbw32Thread(EndpointUbw32Impl endpoint) {
		super("EndpointUbw32Polling (" + endpoint.getSerialPortName()+")");
		
		this.endpoint = endpoint;
	}	
	
	@Override
	public void run() {
		try {

			while (!isInterrupted()) {
				log.trace("Polling Thread active: " + this.getName() + " wait time: " + EndpointUbw32Config.SLEEP_POLLING);
				endpoint.pollingCommand();
				Thread.sleep(EndpointUbw32Config.SLEEP_POLLING);
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

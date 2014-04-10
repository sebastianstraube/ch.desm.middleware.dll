package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;

import ch.desm.middleware.modules.core.daemon.DaemonThread;

/**
 * Inherits the Daeon class to execute polling sequence
 * @author Sebastian
 *
 */
class EndpointUbw32Polling extends DaemonThread {

	EndpointUbw32 endpoint;

	EndpointUbw32Polling(EndpointUbw32 endpoint) {
		super("EndpointUbw32Polling");
		this.endpoint = endpoint;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		try {
			while (!isInterrupted()) {
				System.out.println("Thread running, name: " + this.getName());
				endpoint.sendCommandInputState();
				Thread.sleep(250);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;

import ch.desm.middleware.modules.core.daemon.DaemonThread;

/**
 * Inherits the Daeon class to execute polling sequence
 * @author Sebastian
 *
 */
class EndpointUbw32Polling extends DaemonThread {

	private EndpointUbw32 endpoint;
	private String PinbitMaskInputAnalog;

	EndpointUbw32Polling(EndpointUbw32 endpoint, String configurationInputAnalog) {
		super("EndpointUbw32Polling");
		this.endpoint = endpoint;
		this.PinbitMaskInputAnalog = configurationInputAnalog;
	}

	@Override
	/**
	 * TODO refactoring sleep
	 */
	public void run() {

		try {
			while (!isInterrupted()) {
				System.out.println("Thread running, name: " + this.getName());
				Thread.sleep(100);
				endpoint.sendCommandInputState();
				Thread.sleep(100);
				endpoint.sendCommandInputAnalog(PinbitMaskInputAnalog);
				
				Thread.sleep(10000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

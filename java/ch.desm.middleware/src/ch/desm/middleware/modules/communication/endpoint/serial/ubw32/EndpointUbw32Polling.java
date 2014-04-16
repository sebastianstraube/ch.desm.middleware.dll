package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;

import ch.desm.middleware.modules.core.daemon.DaemonThread;

/**
 * Inherits the Daeon class to execute polling sequence
 * @author Sebastian
 *
 */
class EndpointUbw32Polling extends DaemonThread {

	private EndpointUbw32 endpoint;

	EndpointUbw32Polling(EndpointUbw32 endpoint) {
		super("EndpointUbw32Polling (" + endpoint.getSerialPortName()+")");
		this.endpoint = endpoint;
	}

	@Override
	/**
	 * TODO refactoring sleep
	 */
	public void run() {

		try {
			while (!isInterrupted()) {
				System.out.println("Thread: " + this.getName());
				endpoint.sendCommandInputState();
				endpoint.sendCommandInputAnalog(endpoint.getPinBitMaskInputAnalog());
				
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package ch.desm.middleware.communication.endpoint.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.endpoint.EndpointThreadBase;

public class EndpointTcpClientThread extends EndpointThreadBase {

	private static Logger log = Logger.getLogger(EndpointTcpClientThread.class);
	public static final int POLLING_WAIT_TIME = 2048;

	private EndpointTcpClientImpl endpoint;
	String sendBuffer;
	String receiveBuffer;

	public EndpointTcpClientThread(EndpointTcpClientImpl endpoint) {
		super("EndpointTcpClientThread");
		this.endpoint = endpoint;
		this.receiveBuffer = new String();
	}

	public void sendStream(String stream) {

		try {

			OutputStream out = endpoint.socket.getOutputStream();

			byte[] byteBuffer = stream.getBytes();
			out.write(byteBuffer); // Send the encoded string to the server

			log.info("Client (" + this.getClass() + ") send stream: "
					+ new String(byteBuffer));

		} catch (IOException e) {
			log.error(e);
		}

	}

	public synchronized void receive() {

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					endpoint.socket.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				receiveBuffer += inputLine;
			}

			
			if(!receiveBuffer.isEmpty()){
				endpoint.receiveEvent(receiveBuffer);
			}
			
			receiveBuffer = new String();

			log.info("Client (" + this.getClass() + ") received stream: "
					+ in.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		while (!isInterrupted()) {
			try {

				log.trace("Thread active: " + this.getName() + " wait time: "
						+ POLLING_WAIT_TIME);

				receive();

				Thread.sleep(POLLING_WAIT_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Close the socket and its streams
	 */
	public void closeConnection() {
		try {
			interrupt();
			endpoint.socket.close();
		} catch (IOException e) {
			log.error(e);
		}
	}
}

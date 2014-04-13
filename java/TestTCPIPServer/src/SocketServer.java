import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	ServerSocket server;
	Socket client;

	public SocketServer(int port) {

		initialize(port);
	}

	// Mittels "void TransitionFiredName(in string transitionName)" soll die
	// Middleware dem Pi-Tool schaltbefehle für Transitionen erteilen.

	// Mittels
	// "void newPlaceMarking(in string placeName, in long newMarking, in long msgID)"
	// meldet das Pi-Tool dem Middleware neue Markierungen. Damit die Middleware
	// auch überprüfen kann, dass sie keine Nachrichten vermisst, gibt es die
	// Variable "msgID", welche bei jeder Nachricht um eins inkrementiert wird.
	// Aber da nur geänderte Markierungen übertragen werden, glaube ich nicht,
	// dass es zum Nachrichtenstau kommen wird.

	private void initialize(int port) {
		try {
			server = new ServerSocket(4000);
			client = server.accept();

			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			String strInput = null;

			while ((strInput = in.readLine()) != null) {
				System.out.println("Thread " + Thread.currentThread().getId()
						+ ": Eingabe: " + strInput);

				// Die Eingabe in "UpperCase" umwandeln und als komplette Zeile
				// an Client zurückschieben.
				// "println" sorgt dafür, dass die Daten gesendet werden.
				out.println(strInput.toUpperCase());

				System.out.println("Thread " + Thread.currentThread().getId()
						+ " für Clientanfrage next round");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

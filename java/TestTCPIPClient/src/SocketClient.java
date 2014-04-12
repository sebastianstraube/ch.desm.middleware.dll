
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

	Socket client;

	public SocketClient(int port) {

		initialize(port);
	}

	private void initialize(int port) {
		try {
			client = new Socket("localhost", 4000);

			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String textInput = null;

			while ((textInput = reader.readLine()) != null
					&& !"X".equals(textInput)) {
				System.out.println("Client-Eingabe: " + textInput);

				// Ab zum Server:
				out.println(textInput);
				out.flush();

				System.out.println("Warten auf Server-Antwort...");
				// Server schickt uns "toUpper" der Eingabe:
				String textServer = in.readLine();
				System.out.println("Server-Antwort: " + textServer);
			}
			// User hat "X" eingegeben: Socket dichtmachen.
			client.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

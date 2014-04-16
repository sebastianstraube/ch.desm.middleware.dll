package ch.desm.middleware.modules.core.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

	Socket client;
	
	public SocketClient(int port){

		initialize(port);
	}
	
	private void initialize(int port){
		try {
			client = new Socket("localhost", 4000);
			
			OutputStream outStream = client.getOutputStream();
			PrintWriter out = new PrintWriter(outStream);
			
//			InputStream inputStream = client.getInputStream();
//			InputStream inputStream = System.in;
//			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//			BufferedReader in = new BufferedReader(inputStreamReader);
			
			String inputLine = "";
			while(true){
				
				InputStream inputStream = System.in;
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader in = new BufferedReader(inputStreamReader);
				
				
				if(in != null){
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

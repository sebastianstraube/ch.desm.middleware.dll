package ch.desm.middleware.component.simulation.locsim;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

/**
 * parse incoming serial message from locsim 
 * it's based on the following formats:
 * 
 * byte			0 		1 			2-3 	4-7 		8 
 * direction	start	Signalart 	Kanal 	Data 		ende
 * to locsim	[X]		[D,L,S,U,V]	[00-99] [0000-FFFF] [Y]
 * from locsim	[X]		[U,V]		[00-99] [0000-FFFF] [Y]
 * 
 */
public class LocsimEndpointRs232Parser {

	private static final Pattern validInitMessagePattern = Pattern.compile("^INI[0-9].*");
	private static final Pattern validDataMessagePattern = Pattern.compile("^X[DLSUV][0-9]{2}[0-9A-Z]{4}Y.*");

	private static final Pattern validIncompleteInitMessagePattern = Pattern.compile("^IN?I?[0-9]?");
	private static final Pattern validIncompleteDataMessagePattern = Pattern.compile("^X[DLSUV]?[0-9]{0,2}[0-9A-Z]{0,4}Y?");
	
	private Deque<String> messages;
	private String buffer;
	
	public LocsimEndpointRs232Parser() {
		messages = new ArrayDeque<String>();
		buffer = "";
	}
	
	public void addData(String data) {
		
		data = data.replaceAll("\r", "");
		data = data.replaceAll("\n", "");
		this.buffer = this.buffer + data;
		parseBuffer();
	}
	
	public String pop() {
		return this.messages.pollFirst();
	}
	
	private void parseBuffer() {
		if(buffer.isEmpty()) {
			return;
		}
		
		String message;
		while(true) {
			
			// first, try to pop all known messages
			while((message = popMessageFromBuffer()) != null) {
				messages.addLast(message);
			}

			// yeah, empty!
			if(buffer.isEmpty()) {
				break;
			}
			
			// buffer looks like valid data, but probably
			// some data is missing still. lets wait for it 
			if(bufferProbablyBeginsWithValidData()) {
				break;
			}
			
			// buffer does not look like it contains valid data.
			// lets remove the first byte and retry parsing
			//log.error("skipping invalid byte: " + buffer.charAt(0));
			buffer = buffer.substring(1);
		}
	}
	
	/**
	 * check if beginning of buffer looks like valid data 
	 */
	private boolean bufferProbablyBeginsWithValidData() {
		if(buffer.isEmpty()) {
			return true;
		}

		if(buffer.length() >= 9) {
			return validInitMessagePattern.matcher(buffer).matches()
			    || validDataMessagePattern.matcher(buffer).matches();
		} else {
			return validIncompleteInitMessagePattern.matcher(buffer).matches()
			    || validIncompleteDataMessagePattern.matcher(buffer).matches();
		}
	}
	
	private String popMessageFromBuffer() {
		int len = buffer.length();
		
		if(len >= 4 && validInitMessagePattern.matcher(buffer).matches()) {
			String message = popStringFromBuffer(4);
			return message;
		}
		
		if(len >= 9 && validDataMessagePattern.matcher(buffer).matches()) {
			String message = popStringFromBuffer(9);
			return message;
		}
		
		return null;
	}
	
	private String popStringFromBuffer(int len) {
		String message = buffer.substring(0, len);
		buffer = buffer.substring(len);
		return message;
	}

	public static void runTests() {
		LocsimEndpointRs232Parser parser = new LocsimEndpointRs232Parser();
		
		parser.addData("IN");
		parser.addData("I1");
		assertParsedMessages(parser, new String[]{"INI1"});
		
		parser.addData("MUHMUHMUHINI1INI2XU121234Y");
		assertParsedMessages(parser, new String[]{"INI1", "INI2", "XU121234Y"});
		
		parser.addData("UHINI1IN");
		parser.addData("I2XU121234Y");
		assertParsedMessages(parser, new String[]{"INI1", "INI2", "XU121234Y"});
		
		parser.addData("INXXINXXXU121234YIN");
		assertParsedMessages(parser, new String[]{"XU121234Y"});
	}
	
	private static void assertParsedMessages(LocsimEndpointRs232Parser parser, String[] expectedMessages) {
		int i = 0;
		String message;
		while((message = parser.pop()) != null) {
			if(i >= expectedMessages.length || !message.equals(expectedMessages[i])) {
				throw new AssertionError("invalid message " + message + ", expected: " + expectedMessages[i]);
			}
			i++;
		}
		if(i != expectedMessages.length) {
			throw new AssertionError("invalid message array length " + i);
		}
	}
		
}

package ch.desm.middleware.modules.communication.controller.impl;

import ch.desm.middleware.modules.communication.controller.Rs232;

public class Ubw32 extends Rs232{

	/*
	You end a command by sending a <CR> or <LF> or some combination of 
	the two. This is how all commands must be terminated to be considered 
	valid.
	
	The total number of bytes of each command, counting from the 
	very first byte of the command name up to and including the <CR> 
	at the end of the command must be 64 bytes or less. If it is longer 
	than 64 bytes, the command will be ignored, and other bad things may 
	or may not happen.
	
	You can string together as many commands as you want into one string, 
	and then send that string all at once to the UBW32. As long as each 
	individual command is not more than 64 bytes, this will work well. 
	By putting many commands together (each with their own terminating 
	<CR>) and sending it all to the UBW32 at once, you make the most 
	efficient use of the USB bandwidth.
	
	After successful reception of a command, the UBW32 will always send 
	back an OK packet, which will consist of "OK<CR><LF>". For just 
	testing things out with a terminal emulator, this is very useful 
	because it tells you that the UBW32 understood your command. However, 
	it does add extra communications overhead that may not be appreciated 
	in a higher speed application. You can use the CU command to turn 
	off the sending of "OK" packets. Errors will still be sent, but not 
	any "OK" packets.
	
	All command names ("C", "BC", etc.) are case sensitive. In other words,
	 "BC" is a different command from "bc".
	 
	All port names ("A", "B", "C") are case insensitive. You can use "B" 
	or "b" for port names.
	*/

	//TODO
	private String[] connectedPorts = {"COM4"};
	
	public Ubw32(){
		super.setSerialPorts(connectedPorts);
	}
}

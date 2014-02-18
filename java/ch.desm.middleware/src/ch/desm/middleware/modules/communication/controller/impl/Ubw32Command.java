package ch.desm.middleware.modules.communication.controller.impl;

public class Ubw32Command {

	public static final String COMMAND_SEPERATOR = ",";
	public static final String COMMAND_TERMINATOR = "\r";
	
	private String lastCommand;
	private Ubw32Port portA;
	private Ubw32Port portB;
	private Ubw32Port portC;
	private Ubw32Port portD;
	private Ubw32Port portE;
	private Ubw32Port portF;
	private Ubw32Port portG;

	public Ubw32Command() {
		portA = new Ubw32Port("A");
		portB = new Ubw32Port("B");
		portC = new Ubw32Port("C");
		portD = new Ubw32Port("D");
		portE = new Ubw32Port("E");
		portF = new Ubw32Port("F");
		portG = new Ubw32Port("G");
	}
	
	public String getLastCommand(){
		return lastCommand;
	}

	/**
	 * The "C" command stands for 'Configure' and allows you to set the state of
	 * the port direction registers for ports A, B and C, as well as enable
	 * analog inputs. This allows you to turn each pin into an input or an
	 * output on a pin by pin basis, or enable one or more of the pins to be
	 * analog inputs. Format:
	 * "C,<DirA>,<DirB>,<DirC>,<DirD>,<DirE>,<DirF>,<DirG><CR>" where <DirX> is
	 * a value between 0 and 65,535 that indicates the direction bits for that
	 * port. A 1 is an input, a 0 is an output. Example: "C,0,0,0,0,65535,0,0" -
	 * This would set all ports as outputs except port E which would be all
	 * input Return Packet: "OK"
	 **/
	public void setConfigure(int pinMaskA, int pinMaskB,
			int pinMaskC, int pinMaskD, int pinMaskE, int pinMaskF, int pinMaskG) {
		
		portA.setPins(pinMaskA);
		portB.setPins(pinMaskB);
		portC.setPins(pinMaskC);
		portD.setPins(pinMaskD);
		portE.setPins(pinMaskE);
		portF.setPins(pinMaskF);
		portG.setPins(pinMaskG);
	}
	
	/**
	 * 
	 * @return the last configured command
	 */
	public String getConfigure() {
		lastCommand = "C";
		lastCommand += COMMAND_SEPERATOR + portA.getPins();
		lastCommand += COMMAND_SEPERATOR + portB.getPins();
		lastCommand += COMMAND_SEPERATOR + portC.getPins();
		lastCommand += COMMAND_SEPERATOR + portD.getPins();
		lastCommand += COMMAND_SEPERATOR + portE.getPins();
		lastCommand += COMMAND_SEPERATOR + portF.getPins();
		lastCommand += COMMAND_SEPERATOR + portG.getPins();
		lastCommand += COMMAND_TERMINATOR;
		return lastCommand;
	}
	
	/**
	 * configures the output to high or low
	 * 0 = high
	 * 1 = low
	 * 
	 * @param pinMaskA
	 * @param pinMaskB
	 * @param pinMaskC
	 * @param pinMaskD
	 * @param pinMaskE
	 * @param pinMaskF
	 * @param pinMaskG
	 */
	public void setOutputState(int pinMaskA, int pinMaskB,
			int pinMaskC, int pinMaskD, int pinMaskE, int pinMaskF, int pinMaskG) {
		
		portA.setPins(pinMaskA);
		portB.setPins(pinMaskB);
		portC.setPins(pinMaskC);
		portD.setPins(pinMaskD);
		portE.setPins(pinMaskE);
		portF.setPins(pinMaskF);
		portG.setPins(pinMaskG);
	}
	
	/**
	 * 
	 * @return the last configured command
	 */
	public String getOutputState() {
		lastCommand = "O";
		lastCommand += COMMAND_SEPERATOR + portA.getPins();
		lastCommand += COMMAND_SEPERATOR + portB.getPins();
		lastCommand += COMMAND_SEPERATOR + portC.getPins();
		lastCommand += COMMAND_SEPERATOR + portD.getPins();
		lastCommand += COMMAND_SEPERATOR + portE.getPins();
		lastCommand += COMMAND_SEPERATOR + portF.getPins();
		lastCommand += COMMAND_SEPERATOR + portG.getPins();
		lastCommand += COMMAND_TERMINATOR;
		return lastCommand;
	}
	
	
}

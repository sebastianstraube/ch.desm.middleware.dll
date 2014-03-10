package ch.desm.middleware.modules.communication.serial.connector;


class Ubw32Command implements Ubw32Bridge{

	public static final String COMMAND_SEPERATOR = ",";
	public static final String COMMAND_TERMINATOR = "\r";

	private String lastCommand;
	private EnumCommand enumCommand;
	private Ubw32Port portA;
	private Ubw32Port portB;
	private Ubw32Port portC;
	private Ubw32Port portD;
	private Ubw32Port portE;
	private Ubw32Port portF;
	private Ubw32Port portG;

	public Ubw32Command(EnumCommand enumCommand) {
		this.lastCommand = "";
		this.enumCommand = enumCommand;
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
	
	public EnumCommand getEnumCommand(){
		return enumCommand;
	}

	public void setCommand(int pinMaskA, int pinMaskB,
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
	public String getCommand() {
		switch(enumCommand){
		case CONFIGURE: lastCommand = EnumCommand.CONFIGURE.getValue();
			break;
		case OUTPUT_STATE: lastCommand = EnumCommand.OUTPUT_STATE.getValue();
			break;
		default:
			break;
		}
				
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

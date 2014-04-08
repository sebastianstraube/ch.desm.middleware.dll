package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;


class EndpointUbw32Command {

	public static enum EnumCommand{
		CONFIGURE(1, "C"),
		OUTPUT_STATE(2, "O");
		
		private long id;
		private String value;
		
		private EnumCommand(long id, String value){
			this.id = id;
			this.value = value;
		};
		
		public long getId(){
			return this.id;
		};
		
		public String getValue(){
			return this.value;
		}
	}
	
	public static final String COMMAND_SEPERATOR = ",";
	public static final String COMMAND_TERMINATOR = "\r";

	private String lastCommand;
	private EnumCommand enumCommand;
	private EndpointUbw32Port portA;
	private EndpointUbw32Port portB;
	private EndpointUbw32Port portC;
	private EndpointUbw32Port portD;
	private EndpointUbw32Port portE;
	private EndpointUbw32Port portF;
	private EndpointUbw32Port portG;

	public EndpointUbw32Command(EnumCommand enumCommand) {
		this.lastCommand = "";
		this.enumCommand = enumCommand;
		portA = new EndpointUbw32Port("A");
		portB = new EndpointUbw32Port("B");
		portC = new EndpointUbw32Port("C");
		portD = new EndpointUbw32Port("D");
		portE = new EndpointUbw32Port("E");
		portF = new EndpointUbw32Port("F");
		portG = new EndpointUbw32Port("G");
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

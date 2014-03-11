package ch.desm.middleware.modules.communication.endpoint.serial.ubw32;


class CommunicationEndpointUbw32Command {

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
	private CommunicationEndpointUbw32Port portA;
	private CommunicationEndpointUbw32Port portB;
	private CommunicationEndpointUbw32Port portC;
	private CommunicationEndpointUbw32Port portD;
	private CommunicationEndpointUbw32Port portE;
	private CommunicationEndpointUbw32Port portF;
	private CommunicationEndpointUbw32Port portG;

	public CommunicationEndpointUbw32Command(EnumCommand enumCommand) {
		this.lastCommand = "";
		this.enumCommand = enumCommand;
		portA = new CommunicationEndpointUbw32Port("A");
		portB = new CommunicationEndpointUbw32Port("B");
		portC = new CommunicationEndpointUbw32Port("C");
		portD = new CommunicationEndpointUbw32Port("D");
		portE = new CommunicationEndpointUbw32Port("E");
		portF = new CommunicationEndpointUbw32Port("F");
		portG = new CommunicationEndpointUbw32Port("G");
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

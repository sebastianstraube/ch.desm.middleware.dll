package ch.desm.middleware.modules.communication.serial.connector;


interface Ubw32Bridge {

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
}

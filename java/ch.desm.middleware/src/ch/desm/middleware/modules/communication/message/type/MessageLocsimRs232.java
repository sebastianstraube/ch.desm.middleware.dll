package ch.desm.middleware.modules.communication.message.type;


public class MessageLocsimRs232 extends MessageBase {

	private String signalType;
	private int channel;
	private int data;
	
	/**
	 * 
	 * Message Definition:
	 * 
	 * 0		1			2-3		4-7			8
	 * start 	Signalart 	Kanal 	Data 		ende
	 * [X] 		[D,L,S,U,V] [00-99] [0000-FFFF]	[Y]
	 * [X]		[U,V] 		[00-99] [0000-FFFF] [Y]
	 */
	public MessageLocsimRs232(String payload, EnumMessageTopic topic) {
		super(payload, topic);
		// TODO Auto-generated constructor stub
		
		this.initialize(payload);
	}

	private void initialize(String message){
		signalType = message.substring(1);
		channel = Integer.valueOf(message.substring(2,3));
		data = Integer.valueOf(message.substring(4,7));
	}
	
	public String getSignalType(){
		return this.signalType;
	}
	
	public int getChannel(){
		return this.channel;
	}
	
	public int getData(){
		return this.data;
	}
	
	public boolean isCommandEnable(){
		return data == 1;
	}
}

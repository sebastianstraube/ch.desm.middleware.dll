package ch.desm.middleware.component.simulation.locsim.messages;

import ch.desm.middleware.communication.message.MessageBase;

public class LocsimMessageDll extends MessageBase {

	public static final String DLL_MESSAGE_TRAINPOSITION = "locsim.dll.trainposition;";
	public static final String DLL_MESSAGE_GLEISLIST = "locsim.dll.gleislist;";
	public static final String DLL_MESSAGE_SIGNAL = "locsim.dll.signal;";
	public static final String DLL_MESSAGE_WEICHE = "locsim.dll.weiche;";
	public static final String DLL_MESSAGE_TRACK = "locsim.dll.track;";
	public static final String DLL_MESSAGE_CONFIG_INI1 = "locsim.rs232.config.ini1;";
	
	private String globalId;
	private String dataId;
	private String value;
	
	/**
	 * e.g. message: locsim.dll.signal.id.stellung
	 * 
	 * @param payload
	 * @param topic
	 */
	public LocsimMessageDll(String payload, String topic) {
		super(payload, topic);
		
		this.initialize();
	}
	
	private void initialize(){
		String[] parts = getPayload().split(";");
		
		this.globalId = parts[0];
		this.dataId = parts[1];
		this.value = parts[2];
	}
	
	public String getDataId(){
		return this.dataId;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getGlobalId(){
		return this.globalId;
	}
	
	public boolean isSignalMessage(){
		return getPayload().startsWith(DLL_MESSAGE_SIGNAL);
	}
	
	public boolean isWeicheMessage(){
		return getPayload().startsWith(DLL_MESSAGE_WEICHE);
	}
	
	public boolean isTrainpositionMessage(){
		return getPayload().startsWith(DLL_MESSAGE_TRAINPOSITION);
	}
	
	public boolean isTrackMessage(){
		return getPayload().startsWith(DLL_MESSAGE_TRACK);
	}
	
	public boolean isGleislistMessage(){
		return getPayload().startsWith(DLL_MESSAGE_GLEISLIST);
	}
}

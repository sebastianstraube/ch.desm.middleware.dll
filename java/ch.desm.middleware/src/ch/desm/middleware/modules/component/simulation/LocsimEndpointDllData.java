package ch.desm.middleware.modules.component.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LocsimEndpointDllData extends LocsimConfiguration {

	public static final String LOCSIM_DLL_IDENTIFIER = "locsim.dll";
	public static final String LOCSIM_DLL_IDENTIFIER_SIGNAL = "locsim.dll.signal";
	public static final String LOCSIM_DLL_IDENTIFIER_WEICHE = "locsim.dll.weiche";
	public static final String LOCSIM_DLL_IDENTIFIER_TRACK = "locsim.dll.track";
	public static final String LOCSIM_DLL_IDENTIFIER_TRAINPOSITION = "locsim.dll.trainposition";
	
	private Map<String, String> mapSignaleGlobalIdToMiddlewareMessage;
	private Map<String, String> mapWeicheGlobalIdToMiddlewareMessage;
	private Map<String, String> mapTrackGlobalIdToMiddlewareMessage;

	public LocsimEndpointDllData() {
		mapSignaleGlobalIdToMiddlewareMessage = new HashMap<String, String>();
		mapWeicheGlobalIdToMiddlewareMessage = new HashMap<String, String>();
		mapTrackGlobalIdToMiddlewareMessage = new HashMap<String, String>();

		this.intializeSignalGlobalIdToMiddlewareMessage();
		this.intializeWeicheGlobalIdToMiddlewareMessage();
		this.intializeTrackGlobalIdToMiddlewareMessage();
	}

	
	public boolean isLocsimDllMessage(String message){
		if(message.startsWith(LOCSIM_DLL_IDENTIFIER)){

			if(message.contains(LOCSIM_DLL_IDENTIFIER_SIGNAL)){
					return true;
			}else if(message.contains(LOCSIM_DLL_IDENTIFIER_TRACK)){
					return true;
			}else if(message.contains(LOCSIM_DLL_IDENTIFIER_WEICHE)){
					return true;
			}else {
				System.err.println("invalid identifier for message: " + message);
			}
		}
		
		return false;
	}
	
	/**
	 * TODO implementation
	 */
	public String getGlobalIdFromSignalMapGlobalIdToLocsimDll(String message){

		return "";
	}
	
	/**
	 * TODO implementation
	 * @param key
	 * @return
	 */
	public String getValueFromSignalMapGlobalIdToLocsimDll(String key){
		
		for(Entry<String, String> entry : mapSignaleGlobalIdToMiddlewareMessage.entrySet()){
			if(entry.getKey().equals(key)){
				return entry.getValue();
			}
		}
		
		return "";
	}
	
	/**
	 * 
	 */
	private void intializeSignalGlobalIdToMiddlewareMessage() {
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.113", "locsim.dll.signal.113;;;signal;blocksignauemmenmattdoppelspur–einspur;p832;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.114", "locsim.dll.signal.114;;;signal;zwergdoppelspur–einspur;zwerg43;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.115", "locsim.dll.signal.115;;;signal;einfahrvorsignalGlobalIdToMiddlewareMessagemmenmatt;astern833;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.116", "locsim.dll.signal.116;;;signal;geschwindigkeitsanzeige;vmaxastern833;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.117", "locsim.dll.signal.117;;;signal;einfahrsignalGlobalIdToMiddlewareMessagemmenmatt;a833;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.118", "locsim.dll.signal.118;;;signal;geschwindigkeitsanzeige;vmaxa833;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.119", "locsim.dll.signal.119;;;signal;zwerg;zwerg44;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.120", "locsim.dll.signal.120;;;signal;ausfahrsignalGlobalIdToMiddlewareMessagemmenmattgleis2;c2;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.121", "locsim.dll.signal.121;;;signal;geschwindigkeitsanzeige;vmaxc2;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.122", "locsim.dll.signal.122;;;signal;zwerggleis2;zwerg45;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.123", "locsim.dll.signal.123;;;signal;ausfahrsignalGlobalIdToMiddlewareMessagemmenmattgleis1;c1;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.124", "locsim.dll.signal.124;;;signal;zwerggleis1;zwerg46;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.125", "locsim.dll.signal.125;;;signal;einfahrsignalobermatt;a834;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.126", "locsim.dll.signal.126;;;signal;zwergobermatt;zwerg47;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.127", "locsim.dll.signal.127;;;signal;blocksignalp835;p835;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.128", "locsim.dll.signal.128;;;signal;blocksignalp;p837;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.129", "locsim.dll.signal.129;;;signal;einfahrvorsignallangnau;p838;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.130", "locsim.dll.signal.130;;;signal;geschwindigkeitsanzeige;vmax838;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.131", "locsim.dll.signal.131;;;signal;einfahrsignallangnau;a840;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.132", "locsim.dll.signal.132;;;signal;geschwindigkeitsanzeige;vmax840;?;#");
		mapSignaleGlobalIdToMiddlewareMessage.put("locsim.dll.signal.133", "locsim.dll.signal.133;;;signal;zwergeinfahrweichelangnau;zwerg48;?;#");

	}

	/**
	 * TODO implementation
	 */
	private void intializeWeicheGlobalIdToMiddlewareMessage() {
		
	}

	/**
	 * TODO implementation
	 */
	private void intializeTrackGlobalIdToMiddlewareMessage() {
		
	}
}

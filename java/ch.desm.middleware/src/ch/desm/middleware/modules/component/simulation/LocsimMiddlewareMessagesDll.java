package ch.desm.middleware.modules.component.simulation;

import java.util.HashMap;
import java.util.Map;

public class LocsimMiddlewareMessagesDll {

	private static Map<String, String> messages;
	
	public LocsimMiddlewareMessagesDll(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public Map<String, String> getMessages(){
		return messages;
	}

	private void initialize(){
		messages.put("locsim.dll.signal.113", "locsim.dll.signal.113;;;signal;blocksignauemmenmattdoppelspur–einspur;p832;?;#");
		messages.put("locsim.dll.signal.114", "locsim.dll.signal.114;;;signal;zwergdoppelspur–einspur;zwerg43;?;#");
		messages.put("locsim.dll.signal.115", "locsim.dll.signal.115;;;signal;einfahrvorsignalemmenmatt;astern833;?;#");
		messages.put("locsim.dll.signal.116", "locsim.dll.signal.116;;;signal;geschwindigkeitsanzeige;vmaxastern833;?;#");
		messages.put("locsim.dll.signal.117", "locsim.dll.signal.117;;;signal;einfahrsignalemmenmatt;a833;?;#");
		messages.put("locsim.dll.signal.118", "locsim.dll.signal.118;;;signal;geschwindigkeitsanzeige;vmaxa833;?;#");
		messages.put("locsim.dll.signal.119", "locsim.dll.signal.119;;;signal;zwerg;zwerg44;?;#");
		messages.put("locsim.dll.signal.120", "locsim.dll.signal.120;;;signal;ausfahrsignalemmenmattgleis2;c2;?;#");
		messages.put("locsim.dll.signal.121", "locsim.dll.signal.121;;;signal;geschwindigkeitsanzeige;vmaxc2;?;#");
		messages.put("locsim.dll.signal.122", "locsim.dll.signal.122;;;signal;zwerggleis2;zwerg45;?;#");
		messages.put("locsim.dll.signal.123", "locsim.dll.signal.123;;;signal;ausfahrsignalemmenmattgleis1;c1;?;#");
		messages.put("locsim.dll.signal.124", "locsim.dll.signal.124;;;signal;zwerggleis1;zwerg46;?;#");
		messages.put("locsim.dll.signal.125", "locsim.dll.signal.125;;;signal;einfahrsignalobermatt;a834;?;#");
		messages.put("locsim.dll.signal.126", "locsim.dll.signal.126;;;signal;zwergobermatt;zwerg47;?;#");
		messages.put("locsim.dll.signal.127", "locsim.dll.signal.127;;;signal;blocksignalp835;p835;?;#");
		messages.put("locsim.dll.signal.128", "locsim.dll.signal.128;;;signal;blocksignalp;p837;?;#");
		messages.put("locsim.dll.signal.129", "locsim.dll.signal.129;;;signal;einfahrvorsignallangnau;p838;?;#");
		messages.put("locsim.dll.signal.130", "locsim.dll.signal.130;;;signal;geschwindigkeitsanzeige;vmax838;?;#");
		messages.put("locsim.dll.signal.131", "locsim.dll.signal.131;;;signal;einfahrsignallangnau;a840;?;#");
		messages.put("locsim.dll.signal.132", "locsim.dll.signal.132;;;signal;geschwindigkeitsanzeige;vmax840;?;#");
		messages.put("locsim.dll.signal.133", "locsim.dll.signal.133;;;signal;zwergeinfahrweichelangnau;zwerg48;?;#");
		messages.put("locsim.dll.weiche.xyz", "locsim.dll.weiche.xyz;;;;;;?;#");
		messages.put("locsim.dll.weiche.xyz", "locsim.dll.weiche.xyz;;;;;;?;#");
		messages.put("locsim.dll.weiche.xyz", "locsim.dll.weiche.xyz;;;;;;?;#");
		messages.put("locsim.dll.weiche.xyz", "locsim.dll.weiche.xyz;;;;;;?;#");
		messages.put("locsim.dll.weiche.xyz", "locsim.dll.weiche.xyz;;;;;;?;#");
		messages.put("locsim.dll.weiche.xyz", "locsim.dll.weiche.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");
		messages.put("locsim.dll.track.xyz", "locsim.dll.track.xyz;;;;;;?;#");

	}
}

package ch.desm.middleware.modules.component.simulation.locsim.maps;

import java.util.Map;

import ch.desm.middleware.modules.communication.message.store.MessageMapBase;


public class LocsimMapSignal extends MessageMapBase{

	@Override
	public Map<String, String> getMap() {
		return map;
	}
	
	@Override
	protected void initialize(){
		//test
		map.put("locsim.dll.signal.324", "locsim.dll.signal.133;os;0;signal;zwergeinfahrweichelangnau;zwerg48;?;locsim-dll;#");
		
		map.put("locsim.dll.signal.113", "locsim.dll.signal.113;os;0;signal;blocksignauemmenmattdoppelspur�einspur;p832;?;locsim-dll;#");
		map.put("locsim.dll.signal.114", "locsim.dll.signal.114;os;0;signal;zwergdoppelspur�einspur;zwerg43;?;locsim-dll;#");
		map.put("locsim.dll.signal.115", "locsim.dll.signal.115;os;0;signal;einfahrvorsignalemmenmatt;astern833;?;locsim-dll;#");
		map.put("locsim.dll.signal.116", "locsim.dll.signal.116;os;0;signal;geschwindigkeitsanzeige;vmaxastern833;?;locsim-dll;#");
		map.put("locsim.dll.signal.117", "locsim.dll.signal.117;os;0;signal;einfahrsignalemmenmatt;a833;?;locsim-dll;#");
		map.put("locsim.dll.signal.118", "locsim.dll.signal.118;os;0;signal;geschwindigkeitsanzeige;vmaxa833;?;locsim-dll;#");
		map.put("locsim.dll.signal.119", "locsim.dll.signal.119;os;0;signal;zwerg;zwerg44;?;locsim-dll;#");
		map.put("locsim.dll.signal.120", "locsim.dll.signal.120;os;0;signal;ausfahrsignalemmenmattgleis2;c2;?;locsim-dll;#");
		map.put("locsim.dll.signal.121", "locsim.dll.signal.121;os;0;signal;geschwindigkeitsanzeige;vmaxc2;?;locsim-dll;#");
		map.put("locsim.dll.signal.122", "locsim.dll.signal.122;os;0;signal;zwerggleis2;zwerg45;?;locsim-dll;#");
		map.put("locsim.dll.signal.123", "locsim.dll.signal.123;os;0;signal;ausfahrsignalemmenmattgleis1;c1;?;locsim-dll;#");
		map.put("locsim.dll.signal.124", "locsim.dll.signal.124;os;0;signal;zwerggleis1;zwerg46;?;locsim-dll;#");
		map.put("locsim.dll.signal.125", "locsim.dll.signal.125;os;0;signal;einfahrsignalobermatt;a834;?;locsim-dll;#");
		map.put("locsim.dll.signal.126", "locsim.dll.signal.126;os;0;signal;zwergobermatt;zwerg47;?;locsim-dll;#");
		map.put("locsim.dll.signal.127", "locsim.dll.signal.127;os;0;signal;blocksignalp835;p835;?;locsim-dll;#");
		map.put("locsim.dll.signal.128", "locsim.dll.signal.128;os;0;signal;blocksignalp;p837;?;locsim-dll;#");
		map.put("locsim.dll.signal.129", "locsim.dll.signal.129;os;0;signal;einfahrvorsignallangnau;p838;?;locsim-dll;#");
		map.put("locsim.dll.signal.130", "locsim.dll.signal.130;os;0;signal;geschwindigkeitsanzeige;vmax838;?;locsim-dll;#");
		map.put("locsim.dll.signal.131", "locsim.dll.signal.131;os;0;signal;einfahrsignallangnau;a840;?;locsim-dll;#");
		map.put("locsim.dll.signal.132", "locsim.dll.signal.132;os;0;signal;geschwindigkeitsanzeige;vmax840;?;locsim-dll;#");
		map.put("locsim.dll.signal.133", "locsim.dll.signal.133;os;0;signal;zwergeinfahrweichelangnau;zwerg48;?;locsim-dll;#");
	}
}

package ch.desm.middleware.modules.component.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LocsimEndpointRs232Data extends LocsimConfiguration {

	public static final String LOCSIM_INIT_MESSAGE = "INI";
	public static final String LOCSIM_RS232_IDENTIFIER = "locsim.rs232";
	
	private Map<String, String> mapGlobalIdToLocsimRs232;
	
	public LocsimEndpointRs232Data() {
		mapGlobalIdToLocsimRs232 = new HashMap<String, String>();
		this.intializeMapGlobalIdToLocsimRs232();
	}

	public boolean isLocsimRs232Message(String message){
		if(message.contains(LOCSIM_RS232_IDENTIFIER)){
			return true;
		}
		
		return false;
	}
	
	public String getGlobalIdFromMapGlobalIdToLocsimRs232(String value){
		
		for(Entry<String, String> entry : mapGlobalIdToLocsimRs232.entrySet()){
			if(entry.getValue().contains(value)){
				return entry.getKey();
			}
		}
		
		return "";
	}
	
	public String getValueFromMapGlobalIdToLocsimRs232(String key){
		
		for(Entry<String, String> entry : mapGlobalIdToLocsimRs232.entrySet()){
			if(entry.getKey().equals(key)){
				return entry.getValue();
			}
		}
		
		return "";
	}
	
	public Map<String, String> getMapGlobalIdToLocsimRs232(){
		return this.mapGlobalIdToLocsimRs232;
	}
	
	private void intializeMapGlobalIdToLocsimRs232() {
		mapGlobalIdToLocsimRs232.put("S241", "XV400000Y");
		mapGlobalIdToLocsimRs232.put("locsim.auslöseschalter", "");
		mapGlobalIdToLocsimRs232.put("locsim.hauptleitungsdruck", "");
		mapGlobalIdToLocsimRs232.put("locsim.rangierbremse", "");
		mapGlobalIdToLocsimRs232.put("locsim.hldruck", "");
		mapGlobalIdToLocsimRs232.put("locsim.bremszylinderdruck", "");
		mapGlobalIdToLocsimRs232.put("locsim.fahrleitungsspannung", "");
		mapGlobalIdToLocsimRs232.put("S172.1", "XU160001YXV170000Y");
		mapGlobalIdToLocsimRs232.put("locsim.kompressorschalter.o", "XU160000YXV170000Y");
		mapGlobalIdToLocsimRs232.put("S172.2", "XU170001YXV160000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.bremsen.plus", "XU080001YXU070000YXU060000YXU050000YXU040000YXU030000YXU020000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.bremsen.punkt", "XU070001YXU080000YXU060000YXU050000YXU040000YXU030000YXU020000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.bremsen.minus", "XU060001YXU070000YXU080000YXU050000YXU040000YXU030000YXU020000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.0.null", "XU050001YXU070000YXU060000YXU080000YXU040000YXU030000YXU020000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.fahren.minus", "XU040001YXU070000YXU060000YXU050000YXU080000YXU030000YXU020000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.fahren.punkt", "XU030001YXU070000YXU060000YXU050000YXU040000YXU080000YXU020000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.fahren.m", "XU020001YXU070000YXU060000YXU050000YXU040000YXU030000YXU080000YXU010000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.fahren.plus", "XU010001YXU070000YXU060000YXU050000YXU040000YXU030000YXU020000YXU080000YXU000000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrschalter.fahren.plusplus", "XU000001YXU070000YXU060000YXU050000YXU040000YXU030000YXU020000YXU010000YXU080000Y");
		mapGlobalIdToLocsimRs232.put("S140a", "XU090001YXU100000Y");
		mapGlobalIdToLocsimRs232.put("locsim.fahrtrichtungschalter.neutral", "XU100000YXU090000Y");
		mapGlobalIdToLocsimRs232.put("S140b", "XU100001YXU090000Y");
		mapGlobalIdToLocsimRs232.put("locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null", "XU180000YXU160000YXV170000Y");
		mapGlobalIdToLocsimRs232.put("S132", "XU180000YXU160001YXV170000Y");
		mapGlobalIdToLocsimRs232.put("S316.4", "XU340000Y");
		mapGlobalIdToLocsimRs232.put("S316.1", "XU350000Y");
		mapGlobalIdToLocsimRs232.put("S316.5", "XU360000Y");
		mapGlobalIdToLocsimRs232.put("S316.2", "XU370000Y");
		mapGlobalIdToLocsimRs232.put("S316.6", "XU380000Y");
		mapGlobalIdToLocsimRs232.put("S316.3", "XU390000Y");
		mapGlobalIdToLocsimRs232.put("S324", "XU440000Y");
		mapGlobalIdToLocsimRs232.put("S324.2", "XU430000Y");
		mapGlobalIdToLocsimRs232.put("S242.02", "XU280000Y");
		mapGlobalIdToLocsimRs232.put("S182.3", "XU230000Y");
		mapGlobalIdToLocsimRs232.put("S182.4", "XU250000Y");
		mapGlobalIdToLocsimRs232.put("S182", "XU240000Y");
		mapGlobalIdToLocsimRs232.put("locsim.wagentürenzustandinfo", "");
		mapGlobalIdToLocsimRs232.put("S311", "");
		mapGlobalIdToLocsimRs232.put("S169", "XU150000Y");
		mapGlobalIdToLocsimRs232.put("locsim.druckknopfsignalpfeife. aus", "XU300000YXU310000Y");
		mapGlobalIdToLocsimRs232.put("S189.1", "XU300001YXU310000Y");
		mapGlobalIdToLocsimRs232.put("S189.2", "XU310001YXU300000Y");
		mapGlobalIdToLocsimRs232.put("S242.01", "XU320000Y");
		mapGlobalIdToLocsimRs232.put("S281", "XU220000Y");
		mapGlobalIdToLocsimRs232.put("locsim.schlüsselschalterabfertigungsbefehl", "XU000000Y");
		mapGlobalIdToLocsimRs232.put("S126", "XU200000Y");
		mapGlobalIdToLocsimRs232.put("S129", "XU190000Y");
		mapGlobalIdToLocsimRs232.put("S235", "XU270001Y");
	}
}

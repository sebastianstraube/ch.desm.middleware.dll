package ch.desm.middleware.modules.component.simulation;

import java.util.HashMap;
import java.util.Map;

public class LocsimMiddlewareMessagesRs232 {

	private static Map<String, String> messages;
	
	public LocsimMiddlewareMessagesRs232(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public Map<String, String> getMessages(){
		return messages;
	}

	private void initialize(){
		messages.put("locsim.auslöseschalter", "locsim.auslöseschalter;i;0;schalter;auslöse;;?;#");
		messages.put("locsim.hauptleitungsdruck", "locsim.hauptleitungsdruck;o;0;;;;?;#");
		messages.put("locsim.rangierbremse", "locsim.rangierbremse;o;0;;;;?;#");
		messages.put("locsim.hldruck", "locsim.hldruck;o;0;;;;?;#");
		messages.put("locsim.bremszylinderdruck", "locsim.bremszylinderdruck;o;0;;;;?;#");
		messages.put("locsim.fahrleitungsspannung", "locsim.fahrleitungsspannung;o;0;;;;?;#");
		messages.put("locsim.kompressorschalter.o", "locsim.kompressorschalter.o;i;0;schalter;kompressor;o;?;#");
		messages.put("locsim.fahrschalter.bremsen.plus", "locsim.fahrschalter.bremsen.plus;i;0;fahrschalter;bremse;plus;?;#");
		messages.put("locsim.fahrschalter.bremsen.punkt", "locsim.fahrschalter.bremsen.punkt;i;0;fahrschalter;bremse;punkt;?;#");
		messages.put("locsim.fahrschalter.bremsen.minus", "locsim.fahrschalter.bremsen.minus;i;0;fahrschalter;bremse;minus;?;#");
		messages.put("locsim.fahrschalter.null", "locsim.fahrschalter.null;i;0;fahrschalter;neutral;null;?;#");
		messages.put("locsim.fahrschalter.fahren.minus", "locsim.fahrschalter.fahren.minus;i;0;fahrschalter;fahren;minus;?;#");
		messages.put("locsim.fahrschalter.fahren.punkt", "locsim.fahrschalter.fahren.punkt;i;0;fahrschalter;fahren;punkt;?;#");
		messages.put("locsim.fahrschalter.fahren.m", "locsim.fahrschalter.fahren.m;i;0;fahrschalter;fahren;m;?;#");
		messages.put("locsim.fahrschalter.fahren.plus", "locsim.fahrschalter.fahren.plus;i;0;fahrschalter;fahren;plus;?;#");
		messages.put("locsim.fahrschalter.fahren.plusplus", "locsim.fahrschalter.fahren.plusplus;i;0;fahrschalter;fahren;plusplus;?;#");
		messages.put("locsim.fahrtrichtungschalter.neutral", "locsim.fahrtrichtungschalter.neutral;i;0;schalter;fahrtrichtung;neutral;?;#");
		messages.put("locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null", "locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null;i;0;schalter;hauptschalter;aus;?;#");
		messages.put("locsim.wagentürenzustandinfo", "locsim.wagentürenzustandinfo;o;0;zustandinfo;tür;wagen;?;#");
		messages.put("locsim.druckknopfsignalpfeife. aus", "locsim.druckknopfsignalpfeife. aus;o;0;druckknopf;pfeife;signal;?;#");
		messages.put("locsim.schlüsselschalterabfertigungsbefehl", "locsim.schlüsselschalterabfertigungsbefehl;i;0;schalter;abfertigung;schlüssel;?;#");
	}
}

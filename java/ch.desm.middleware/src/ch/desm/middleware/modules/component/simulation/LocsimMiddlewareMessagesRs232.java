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
		messages.put("locsim.rs232.config.ini1", "locsim.rs232.config.ini1;os;0;configuration;start;initialisation;?;locsim-rs232;#");
		messages.put("locsim.auslöseschalter", "locsim.auslöseschalter;i;0;schalter;auslöse;;?;locsim-rs232;#");
		messages.put("locsim.hauptleitungsdruck", "locsim.hauptleitungsdruck;o;0;;;;?;locsim-rs232;#");
		messages.put("locsim.rangierbremse", "locsim.rangierbremse;o;0;;;;?;locsim-rs232;#");
		messages.put("locsim.hldruck", "locsim.hldruck;o;0;;;;?;locsim-rs232;#");
		messages.put("locsim.bremszylinderdruck", "locsim.bremszylinderdruck;o;0;;;;?;locsim-rs232;#");
		messages.put("locsim.fahrleitungsspannung", "locsim.fahrleitungsspannung;o;0;;;;?;locsim-rs232;#");
		messages.put("locsim.kompressorschalter.o", "locsim.kompressorschalter.o;i;0;schalter;kompressor;o;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.bremsen.plus", "locsim.fahrschalter.bremsen.plus;i;0;fahrschalter;bremse;plus;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.bremsen.punkt", "locsim.fahrschalter.bremsen.punkt;i;0;fahrschalter;bremse;punkt;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.bremsen.minus", "locsim.fahrschalter.bremsen.minus;i;0;fahrschalter;bremse;minus;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.null", "locsim.fahrschalter.null;i;0;fahrschalter;neutral;null;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.fahren.minus", "locsim.fahrschalter.fahren.minus;i;0;fahrschalter;fahren;minus;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.fahren.punkt", "locsim.fahrschalter.fahren.punkt;i;0;fahrschalter;fahren;punkt;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.fahren.m", "locsim.fahrschalter.fahren.m;i;0;fahrschalter;fahren;m;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.fahren.plus", "locsim.fahrschalter.fahren.plus;i;0;fahrschalter;fahren;plus;?;locsim-rs232;#");
		messages.put("locsim.fahrschalter.fahren.plusplus", "locsim.fahrschalter.fahren.plusplus;i;0;fahrschalter;fahren;plusplus;?;locsim-rs232;#");
		messages.put("locsim.fahrtrichtungschalter.neutral", "locsim.fahrtrichtungschalter.neutral;i;0;schalter;fahrtrichtung;neutral;?;locsim-rs232;#");
		messages.put("locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null", "locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null;i;0;schalter;hauptschalter;aus;?;locsim-rs232;#");
		messages.put("locsim.wagentürenzustandinfo", "locsim.wagentürenzustandinfo;o;0;zustandinfo;tür;wagen;?;locsim-rs232;#");
		messages.put("locsim.druckknopfsignalpfeife. aus", "locsim.druckknopfsignalpfeife. aus;o;0;druckknopf;pfeife;signal;?;locsim-rs232;#");
		messages.put("locsim.schlüsselschalterabfertigungsbefehl", "locsim.schlüsselschalterabfertigungsbefehl;i;0;schalter;abfertigung;schlüssel;?;locsim-rs232;#");
	}
}

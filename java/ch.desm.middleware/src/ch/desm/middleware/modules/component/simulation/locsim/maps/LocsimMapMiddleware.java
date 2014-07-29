package ch.desm.middleware.modules.component.simulation.locsim.maps;

import java.util.Map;

import ch.desm.middleware.modules.communication.message.store.MessageMapBase;


public class LocsimMapMiddleware extends MessageMapBase {
	
	@Override
	public Map<String, String> getMap() {
		return map;
	}
	
	@Override
	protected void initialize(){
		map.put("locsim.kompressorschalter.o", "locsim.kompressorschalter.o;i;0;schalter;kompressor;o;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.bremsen.plus", "locsim.fahrschalter.bremsen.plus;i;0;fahrschalter;bremse;plus;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.bremsen.punkt", "locsim.fahrschalter.bremsen.punkt;i;0;fahrschalter;bremse;punkt;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.bremsen.minus", "locsim.fahrschalter.bremsen.minus;i;0;fahrschalter;bremse;minus;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.null", "locsim.fahrschalter.null;i;0;fahrschalter;neutral;null;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.fahren.minus", "locsim.fahrschalter.fahren.minus;i;0;fahrschalter;fahren;minus;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.fahren.punkt", "locsim.fahrschalter.fahren.punkt;i;0;fahrschalter;fahren;punkt;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.fahren.m", "locsim.fahrschalter.fahren.m;i;0;fahrschalter;fahren;m;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.fahren.plus", "locsim.fahrschalter.fahren.plus;i;0;fahrschalter;fahren;plus;?;locsim-rs232;#");
		map.put("locsim.fahrschalter.fahren.plusplus", "locsim.fahrschalter.fahren.plusplus;i;0;fahrschalter;fahren;plusplus;?;locsim-rs232;#");
		map.put("locsim.fahrtrichtungschalter.neutral", "locsim.fahrtrichtungschalter.neutral;i;0;schalter;fahrtrichtung;neutral;?;locsim-rs232;#");
		map.put("locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null", "locsim.hauptschalterschalter.hauptschalter.aus.kompressor.null;i;0;schalter;hauptschalter;aus;?;locsim-rs232;#");
		map.put("locsim.druckknopfsignalpfeife. aus", "locsim.druckknopfsignalpfeife. aus;o;0;druckknopf;pfeife;signal;?;locsim-rs232;#");
		map.put("locsim.schlüsselschalterabfertigungsbefehl", "locsim.schlüsselschalterabfertigungsbefehl;i;0;schalter;abfertigung;schlüssel;?;locsim-rs232;#");
		map.put("S126", "s126;i;0;schalter;steuerstrom;0;?;kabinere420;#");
		map.put("S129", "s129;i;0;schalter;stromabnehmer;0;?;kabinere420;#");
		map.put("S132", "s132;i;0;schalter;haupt;0;?;kabinere420;#");
		map.put("S172.1", "s172.1;i;0;schalter;kompressor;a;?;kabinere420;#");
		map.put("S172.2", "s172.2;i;0;schalter;kompressor;d;?;kabinere420;#");
		map.put("S169", "s169;i;0;schalter;zugsammelschiene;0;?;kabinere420;#");
		map.put("S140a", "s140a;i;0;schalter;wende;vorwärts;?;kabinere420;#");
		map.put("S140b", "s140b;i;0;schalter;wende;rückwärts;?;kabinere420;#");
		map.put("S150a", "s150a;i;0;schalter;fahr;150a;?;kabinere420;#");
		map.put("S150b", "s150b;i;0;schalter;fahr;150b;?;kabinere420;#");
		map.put("S150d", "s150d;i;0;schalter;fahr;150d;?;kabinere420;#");
		map.put("S150e", "s150e;i;0;schalter;fahr;150e;?;kabinere420;#");
		map.put("S150f", "s150f;i;0;schalter;fahr;150f;?;kabinere420;#");
		map.put("S150g", "s150g;i;0;schalter;fahr;150g;?;kabinere420;#");
		map.put("S150l", "s150l;i;0;schalter;fahr;150l;?;kabinere420;#");
		map.put("S189.1", "s189.1;i;0;schalter;pfeife;stufe1;?;kabinere420;#");
		map.put("S189.2", "s189.2;i;0;schalter;pfeife;stufe2;?;kabinere420;#");
		map.put("S242.01", "s242.01;i;0;schalter;zugsicherung;zurückstellen;?;kabinere420;#");
		map.put("S242.02", "s242.02;i;0;taste;zugsicherung;manöver;?;kabinere420;#");
		map.put("S281", "s281;i;0;taste;schleuderschutz;0;?;kabinere420;#");
		map.put("S182.3", "s182.3;i;0;taste;türfreigabe;links;?;kabinere420;#");
		map.put("S182.4", "s182.4;i;0;taste;türfreigabe;rechts;?;kabinere420;#");
		map.put("S182", "s182;i;0;taste;türverriegelung;0;?;kabinere420;#");
		map.put("S324.2", "s324.2;;0;tastschalter;fst;fahrplan;?;kabinere420;#");
		map.put("S316.1", "s316.1;i;0;schalter;stirnlampe;linksweiss;?;kabinere420;#");
		map.put("S316.4", "s316.4;i;0;schalter;stirnlampe;linksrot;?;kabinere420;#");
		map.put("S316.2", "s316.2;i;0;schalter;stirnlampe;obenweiss;?;kabinere420;#");
		map.put("S316.5", "s316.5;i;0;schalter;stirnlampe;obenrot;?;kabinere420;#");
		map.put("S316.3", "s316.3;i;0;schalter;stirnlampe;rechtsweiss;?;kabinere420;#");
		map.put("S316.6", "s316.6;i;0;schalter;stirnlampe;rechtsrot;?;kabinere420;#");
		map.put("S324", "s324;;0;schalter;beleuchtung;führerstand;?;kabinere420;#");
		map.put("S235", "s235;i;0;schalter;sifa;pedal;?;kabinere420;#");
	}
}

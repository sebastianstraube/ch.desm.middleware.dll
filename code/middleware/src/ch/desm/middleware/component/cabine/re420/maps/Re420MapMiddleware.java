package ch.desm.middleware.component.cabine.re420.maps;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;

public class Re420MapMiddleware extends ComponentMapBase {

	@Override
	public Map<String, String> getMap() {
		return map;
	}
	
	@Override
	protected void initialize(){

//		digital input
		map.put("locsim.initialization.ready.ini1", "locsim.initialization.ready.ini1;os;0;message;initialisiation;ini1;?;locsim-rs232;#");
		map.put("locsim.initialization.ready.ini7", "locsim.initialization.ready.ini7;os;0;message;initialisiation;ini7;?;locsim-rs232;#");
		map.put("locsim.initialization.ready.ini2", "locsim.initialization.ready.ini2;os;0;message;initialisiation;ini2;?;locsim-rs232;#");
		
		map.put("s126", "s126;i;0;schalter;steuerstrom;0;?;kabinere420;#");
		map.put("s129", "s129;i;0;schalter;stromabnehmer;0;?;kabinere420;#");
		map.put("s132", "s132;i;0;schalter;haupt;0;?;kabinere420;#");
		map.put("s235", "s235;i;0;schalter;sifa;pedal;?;kabinere420;#");
		map.put("s140a", "s140a;i;0;schalter;wende;vorwärts;?;kabinere420;#");
		map.put("s140b", "s140b;i;0;schalter;wende;rückwärts;?;kabinere420;#");

		map.put("ao269", "ao269;i;0;drucksensor;druck;hauptleitung;?;kabinere420;#");
		map.put("ao173", "ao173;i;0;drucksensor;druck;bremszylinder;?;kabinere420;#");
		

		map.put("s182.3", "s182.3;i;0;taste;türfreigabe;links;?;kabinere420;#");
		map.put("s182.4", "s182.4;i;0;taste;türfreigabe;rechts;?;kabinere420;#");
		map.put("s182", "s182;i;0;taste;türverriegelung;0;?;kabinere420;#");
		
//		digital output
		map.put("l182.3", "l182.3;o;0;lampe;meldelampe;tür_l;?;kabinere420;#");
		map.put("l185", "l185;o;0;lampe;meldelampe;tür_offen;?;kabinere420;#");
		map.put("l182.4", "l182.4;o;0;lampe;meldelampe;tür_r;?;kabinere420;#");

//		analog output
		map.put("a74", "a74;o;0;analog-instrument;spannung;fahrdraht;?;kabinere420;#");
		map.put("a79", "a79;o;0;analog-instrument;strom;i_max;?;kabinere420;#");
		map.put("a79.1", "a79.1;o;0;analog-instrument;strom;i_delta;?;kabinere420;#");
		map.put("d94vi", "d94vi;;0;analog-instrument;geschwindigkeitsanzeige;ist;?;kabinere420;#");

		map.put("ao269", "ao269;i;0;drucksensor;druck;hauptleitung;?;kabinere420;#");
		map.put("ao173", "ao173;i;0;drucksensor;druck;bremszylinder;?;kabinere420;#");		
	}
}

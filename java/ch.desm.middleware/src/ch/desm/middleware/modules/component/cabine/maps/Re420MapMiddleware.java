package ch.desm.middleware.modules.component.cabine.maps;

import java.util.HashMap;
import java.util.Map;

public class Re420MapMiddleware {

	private static Map<String, String> messages;
	
	public Re420MapMiddleware(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public Map<String, String> getMessages(){
		return messages;
	}

	private void initialize(){
		messages.put("S126.1", "s126.1;;0;schalter;haupthahn;1;?;kabinere420;#");
		messages.put("S126", "s126;i;0;schalter;steuerstrom;0;?;kabinere420;#");
		messages.put("S129", "s129;i;0;schalter;stromabnehmer;0;?;kabinere420;#");
		messages.put("S132", "s132;i;0;schalter;haupt;0;?;kabinere420;#");
		messages.put("S172.1", "s172.1;i;0;schalter;kompressor;a;?;kabinere420;#");
		messages.put("S172.2", "s172.2;i;0;schalter;kompressor;d;?;kabinere420;#");
		messages.put("S169", "s169;i;0;schalter;zugsammelschiene;0;?;kabinere420;#");
		messages.put("S311", "s311;i;0;schalter;zug;0;?;kabinere420;#");
		messages.put("S316_1", "s316_1;i;0;schalter;dienstbel;1;?;kabinere420;#");
		messages.put("S316_2", "s316_2;;0;schalter;dienstbel;2;?;kabinere420;#");
		messages.put("S140a", "s140a;i;0;schalter;wende;vorwärts;?;kabinere420;#");
		messages.put("S140b", "s140b;i;0;schalter;wende;rückwärts;?;kabinere420;#");
		messages.put("cabine.fahrschalter.bremsen.plus", "cabine.fahrschalter.bremsen.plus;;;schalter;fahr;bremsenplus;?;;#");
		messages.put("cabine.fahrschalter.bremsen.punkt", "cabine.fahrschalter.bremsen.punkt;;;schalter;fahr;bremsenpunkt;?;;#");
		messages.put("cabine.fahrschalter.bremsen.minus", "cabine.fahrschalter.bremsen.minus;;;schalter;fahr;bremsenminus;?;;#");
		messages.put("cabine.fahrschalter.bremsen.neutral", "cabine.fahrschalter.bremsen.neutral;;;schalter;fahr;neutral;?;;#");
		messages.put("cabine.fahrschalter.fahren.minus", "cabine.fahrschalter.fahren.minus;;;schalter;fahr;fahrenminus;?;;#");
		messages.put("cabine.fahrschalter.fahren.punkt", "cabine.fahrschalter.fahren.punkt;;;schalter;fahr;fahrenpunkt;?;;#");
		messages.put("cabine.fahrschalter.fahren.m", "cabine.fahrschalter.fahren.m;;;schalter;fahr;fahrenm;?;;#");
		messages.put("cabine.fahrschalter.fahren.plus", "cabine.fahrschalter.fahren.plus;;;schalter;fahr;fahrenplus;?;;#");
		messages.put("cabine.fahrschalter.fahren.plusplus", "cabine.fahrschalter.fahren.plusplus;;;schalter;fahr;fahrenplusplus;?;;#");
		messages.put("S189.1", "s189.1;i;0;schalter;pfeife;stufe1;?;kabinere420;#");
		messages.put("S189.2", "s189.2;i;0;schalter;pfeife;stufe2;?;kabinere420;#");
		messages.put("S242.01", "s242.01;i;0;schalter;zugsicherung;zurückstellen;?;kabinere420;#");
		messages.put("S242.03", "s242.03;i;0;schalter;zub;zurückstellen;?;kabinere420;#");
		messages.put("S242.02", "s242.02;i;0;taste;zugsicherung;manöver;?;kabinere420;#");
		messages.put("S174", "s174;;0;taste;ventillator;aus;?;kabinere420;#");
		messages.put("S276_1", "s276_1;;0;schalter;bremse;r;?;kabinere420;#");
		messages.put("S276_2", "s276_2;;0;schalter;bremse;p;?;kabinere420;#");
		messages.put("S276_3", "s276_3;;0;schalter;bremse;g;?;kabinere420;#");
		messages.put("S241", "s241;;0;hebel;bvhahn;0;?;kabinere420;#");
		messages.put("S281", "s281;i;0;taste;schleuderschutz;0;?;kabinere420;#");
		messages.put("S182.3", "s182.3;i;0;taste;türfreigabe;links;?;kabinere420;#");
		messages.put("S182.4", "s182.4;i;0;taste;türfreigabe;rechts;?;kabinere420;#");
		messages.put("S182", "s182;i;0;taste;türverriegelung;0;?;kabinere420;#");
		messages.put("S324.1", "s324.1;;0;schalter;fst;instrumente;?;kabinere420;#");
		messages.put("S324.2", "s324.2;;0;tastschalter;fst;fahrplan;?;kabinere420;#");
		messages.put("S317", "s317;;0;tastschalter;fst;aufblend;?;kabinere420;#");
		messages.put("S316.1", "s316.1;i;0;schalter;stirnlampe;linksweiss;?;kabinere420;#");
		messages.put("S316.4", "s316.4;i;0;schalter;stirnlampe;linksrot;?;kabinere420;#");
		messages.put("S316.2", "s316.2;i;0;schalter;stirnlampe;obenweiss;?;kabinere420;#");
		messages.put("S316.5", "s316.5;i;0;schalter;stirnlampe;obenrot;?;kabinere420;#");
		messages.put("S316.3", "s316.3;i;0;schalter;stirnlampe;rechtsweiss;?;kabinere420;#");
		messages.put("S316.6", "s316.6;i;0;schalter;stirnlampe;rechtsrot;?;kabinere420;#");
		messages.put("S324", "s324;;0;schalter;beleuchtung;führerstand;?;kabinere420;#");
		messages.put("S333", "s333;;0;tastschalter;beleuchtung;instrumente;?;kabinere420;#");
		messages.put("S235", "s235;i;0;schalter;sifa;pedal;?;kabinere420;#");
		messages.put("W238_t", "w238_t;o;0;warntongeber;signum;tief;?;kabinere420;#");
		messages.put("W238_h", "w238_h;o;0;warntongeber;signum;hoch;?;kabinere420;#");
		messages.put("L317", "l317;;0;lampe;fernlicht;dienstbeleuchtung;?;kabinere420;#");
		messages.put("L242b", "l242b;o;0;lampe;zugsicherung;warnung;?;kabinere420;#");
		messages.put("L242a", "l242a;o;0;lampe;zugsicherung;halt;?;kabinere420;#");
		messages.put("D94VI.1", "d94vi.1;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.2", "d94vi.2;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.3", "d94vi.3;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.4", "d94vi.4;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.5", "d94vi.5;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.6", "d94vi.6;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.7", "d94vi.7;o;0;;;;?;kabinere420;#");
		messages.put("D94VI.8", "d94vi.8;o;0;;;;?;kabinere420;#");

		messages.put("D94m", "d94m;;0;analoganzeige;lzb;weganzeige;?;kabinere420;#");

		messages.put("D94LZB_Z5", "d94lzb_z5;;0;ziffernanzeige;lzb;digitalanzeige;?;kabinere420;#");
		messages.put("D94LZB_Z3", "d94lzb_z3;;0;ziffernanzeige;lzb;digitalanzeige;?;kabinere420;#");
		messages.put("L94_LZB_r", "l94_lzb_r;;0;lampe;lzb;anzeige ;?;kabinere420;#");
		messages.put("L94_LZB_gr", "l94_lzb_gr;;0;lampe;lzb;anzeige ;?;kabinere420;#");
		messages.put("L94_LZB_gb", "l94_lzb_gb;;0;lampe;lzb;anzeige ;?;kabinere420;#");
		messages.put("L94_LZB_w", "l94_lzb_w;;0;lampe;lzb;anzeige ;?;kabinere420;#");
		messages.put("A74", "a74;o;0;analog-instrument;spannung;fahrdraht;?;kabinere420;#");
		messages.put("A79", "a79;o;0;analog-instrument;strom;i_max;?;kabinere420;#");
		messages.put("A79.1", "a79.1;o;0;analog-instrument;strom;i_delta;?;kabinere420;#");
		messages.put("P13c_HB", "p13c_hb;;0;0;0;0;?;kabinere420;#");
		messages.put("P13c_HL", "p13c_hl;;0;0;0;0;?;kabinere420;#");
		messages.put("P13c_BZ", "p13c_bz;;0;0;0;0;?;kabinere420;#");
		messages.put("AO269", "ao269;i;0;drucksensor;druck;hauptleitung;?;kabinere420;#");
		messages.put("AO173", "ao173;i;0;drucksensor;druck;bremszylinder;?;kabinere420;#");
		messages.put("L281", "l281;o;0;lampe;meldelampe;schleudern;?;kabinere420;#");
		messages.put("L281.1", "l281.1;o;0;taste;schleuderbremse;bz_1bar;?;kabinere420;#");
		messages.put("L83", "l83;o;0;lampe;meldelampe;zss;?;kabinere420;#");
		messages.put("L175", "l175;o;0;lampe;meldelampe;stoeventi;?;kabinere420;#");
		messages.put("L163", "l163;o;0;lampe;meldelampe;stufensch;?;kabinere420;#");
		messages.put("L181", "l181;o;0;lampe;meldelampe;abf;?;kabinere420;#");
		messages.put("L182.3", "l182.3;o;0;lampe;meldelampe;tür_l;?;kabinere420;#");
		messages.put("L185", "l185;o;0;lampe;meldelampe;tür_offen;?;kabinere420;#");
		messages.put("L182.4", "l182.4;o;0;lampe;meldelampe;tür_r;?;kabinere420;#");
		messages.put("L242.2", "l242.2;o;0;lampe;meldelampe;m-taste;?;kabinere420;#");
		messages.put("L318a", "l318a;;0;lampe;dienstbel;weiss;?;kabinere420;#");
		messages.put("L318b", "l318b;;0;lampe;dienstbel;warnsignal;?;kabinere420;#");
		messages.put("L325.2", "l325.2;;0;lampe;fst;fahrplan;?;kabinere420;#");


	}
}

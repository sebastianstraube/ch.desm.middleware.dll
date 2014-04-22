package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

public class Re420MiddlewareMessages {

	private static Map<String, String> messages;
	
	public Re420MiddlewareMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public Map<String, String> getMessages(){
		return messages;
	}

	private void initialize(){
		messages.put("S126.1", "s126.1;;0;schalter;haupthahn;1;?;#");
		messages.put("S126", "s126;i;0;schalter;steuerstrom;0;?;#");
		messages.put("S129", "s129;i;0;schalter;stromabnehmer;0;?;#");
		messages.put("S132", "s132;i;0;schalter;haupt;0;?;#");
		messages.put("S172.1", "s172.1;i;0;schalter;kompressor;a;?;#");
		messages.put("S172.2", "s172.2;i;0;schalter;kompressor;d;?;#");
		messages.put("S169", "s169;i;0;schalter;zugsammelschiene;0;?;#");
		messages.put("S311", "s311;i;0;schalter;zug;0;?;#");
		messages.put("S316_1", "s316_1;i;0;schalter;dienstbel;1;?;#");
		messages.put("S316_2", "s316_2;;0;schalter;dienstbel;2;?;#");
		messages.put("S140a", "s140a;i;0;schalter;wende;vorwärts;?;#");
		messages.put("S140b", "s140b;i;0;schalter;wende;rückwärts;?;#");
		messages.put("S150a", "s150a;i;0;schalter;fahr;150a;?;#");
		messages.put("S150b", "s150b;i;0;schalter;fahr;150b;?;#");
		messages.put("S150d", "s150d;i;0;schalter;fahr;150d;?;#");
		messages.put("S150e", "s150e;i;0;schalter;fahr;150e;?;#");
		messages.put("S150f", "s150f;i;0;schalter;fahr;150f;?;#");
		messages.put("S150g", "s150g;i;0;schalter;fahr;150g;?;#");
		messages.put("S150l", "s150l;i;0;schalter;fahr;150l;?;#");
		messages.put("S189.1", "s189.1;i;0;schalter;pfeife;stufe1;?;#");
		messages.put("S189.2", "s189.2;i;0;schalter;pfeife;stufe2;?;#");
		messages.put("S242.01", "s242.01;i;0;schalter;zugsicherung;zurückstellen;?;#");
		messages.put("S242.03", "s242.03;i;0;schalter;zub;zurückstellen;?;#");
		messages.put("S242.02", "s242.02;i;0;taste;zugsicherung;manöver;?;#");
		messages.put("S174", "s174;;0;taste;ventillator;aus;?;#");
		messages.put("S276_1", "s276_1;;0;schalter;bremse;r;?;#");
		messages.put("S276_2", "s276_2;;0;schalter;bremse;p;?;#");
		messages.put("S276_3", "s276_3;;0;schalter;bremse;g;?;#");
		messages.put("S241", "s241;;0;hebel;bvhahn;0;?;#");
		messages.put("S281", "s281;i;0;taste;schleuderschutz;0;?;#");
		messages.put("S182.3", "s182.3;i;0;taste;türfreigabe;links;?;#");
		messages.put("S182.4", "s182.4;i;0;taste;türfreigabe;rechts;?;#");
		messages.put("S182", "s182;i;0;taste;türverriegelung;0;?;#");
		messages.put("S324.1", "s324.1;;0;schalter;fst;instrumente;?;#");
		messages.put("S324.2", "s324.2;;0;tastschalter;fst;fahrplan;?;#");
		messages.put("S317", "s317;;0;tastschalter;fst;aufblend;?;#");
		messages.put("S316.1", "s316.1;i;0;schalter;stirnlampe;linksweiss;?;#");
		messages.put("S316.4", "s316.4;i;0;schalter;stirnlampe;linksrot;?;#");
		messages.put("S316.2", "s316.2;i;0;schalter;stirnlampe;obenweiss;?;#");
		messages.put("S316.5", "s316.5;i;0;schalter;stirnlampe;obenrot;?;#");
		messages.put("S316.3", "s316.3;i;0;schalter;stirnlampe;rechtsweiss;?;#");
		messages.put("S316.6", "s316.6;i;0;schalter;stirnlampe;rechtsrot;?;#");
		messages.put("S324", "s324;;0;schalter;beleuchtung;führerstand;?;#");
		messages.put("S333", "s333;;0;tastschalter;beleuchtung;instrumente;?;#");
		messages.put("S235", "s235;i;0;schalter;sifa;pedal;?;#");
		messages.put("W238_t", "w238_t;i;0;warntongeber;signum;tief;?;#");
		messages.put("W238_h", "w238_h;i;0;warntongeber;signum;hoch;?;#");
		messages.put("L317", "l317;;0;lampe;fernlicht;dienstbeleuchtung;?;#");
		messages.put("L242b", "l242b;i;0;lampe;zugsicherung;warnung;?;#");
		messages.put("L242a", "l242a;i;0;lampe;zugsicherung;halt;?;#");
		messages.put("D94VI.1", "d94vi.1;i;0;;;;?;#");
		messages.put("D94VI.2", "d94vi.2;i;0;;;;?;#");
		messages.put("D94VI.3", "d94vi.3;i;0;;;;?;#");
		messages.put("D94VI.4", "d94vi.4;i;0;;;;?;#");
		messages.put("D94VI.5", "d94vi.5;i;0;;;;?;#");
		messages.put("D94VI.6", "d94vi.6;i;0;;;;?;#");
		messages.put("D94VI.7", "d94vi.7;i;0;;;;?;#");
		messages.put("D94VI.8", "d94vi.8;i;0;;;;?;#");
		messages.put("D94m", "d94m;;0;analoganzeige;lzb;weganzeige;?;#");
		messages.put("D94LZB_Z5", "d94lzb_z5;;0;ziffernanzeige;lzb;digitalanzeige;?;#");
		messages.put("D94LZB_Z3", "d94lzb_z3;;0;ziffernanzeige;lzb;digitalanzeige;?;#");
		messages.put("L94_LZB_r", "l94_lzb_r;;0;lampe;lzb;anzeige ;?;#");
		messages.put("L94_LZB_gr", "l94_lzb_gr;;0;lampe;lzb;anzeige ;?;#");
		messages.put("L94_LZB_gb", "l94_lzb_gb;;0;lampe;lzb;anzeige ;?;#");
		messages.put("L94_LZB_w", "l94_lzb_w;;0;lampe;lzb;anzeige ;?;#");
		messages.put("A74", "a74;;0;analog-instrument;spannung;fahrdraht;?;#");
		messages.put("A79", "a79;;0;analog-instrument;strom;i_max;?;#");
		messages.put("A79.1", "a79.1;;0;analog-instrument;strom;i_delta;?;#");
		messages.put("P13c_HB", "p13c_hb;;0;0;0;0;?;#");
		messages.put("P13c_HL", "p13c_hl;;0;0;0;0;?;#");
		messages.put("P13c_BZ", "p13c_bz;;0;0;0;0;?;#");
		messages.put("AO269", "ao269;;0;drucksensor;druck;hauptleitung;?;#");
		messages.put("AO173", "ao173;;0;drucksensor;druck;bremszylinder;?;#");
		messages.put("L281", "l281;i;0;lampe;meldelampe;schleudern;?;#");
		messages.put("L83", "l83;;0;lampe;meldelampe;zss;?;#");
		messages.put("L175", "l175;i;0;lampe;meldelampe;stoeventi;?;#");
		messages.put("L163", "l163;i;0;lampe;meldelampe;stufensch;?;#");
		messages.put("L181", "l181;i;0;lampe;meldelampe;abf;?;#");
		messages.put("L182.3", "l182.3;i;0;lampe;meldelampe;tür_l;?;#");
		messages.put("L185", "l185;i;0;lampe;meldelampe;tür_offen;?;#");
		messages.put("L182.4", "l182.4;i;0;lampe;meldelampe;tür_r;?;#");
		messages.put("L242.2", "l242.2;i;0;lampe;meldelampe;m-taste;?;#");
		messages.put("L318a", "l318a;;0;lampe;dienstbel;weiss;?;#");
		messages.put("L318b", "l318b;;0;lampe;dienstbel;warnsignal;?;#");
		messages.put("L325.2", "l325.2;;0;lampe;fst;fahrplan;?;#");
	}
}

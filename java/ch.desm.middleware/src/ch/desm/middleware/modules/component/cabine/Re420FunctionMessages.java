package ch.desm.middleware.modules.component.cabine;

import java.util.HashMap;
import java.util.Map;

public class Re420FunctionMessages {
	public final static String PARAMETER_PLACEHOLDER = "\\?";
	
	private static Map<String, String> messages;
	
	public Re420FunctionMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	public Map<String, String> getMessages(){
		return messages;
	}
	
	private void initialize(){
		messages.put("S126.1", "s126.1;;;e;schalter;haupthahn;1;?;#");
		messages.put("S126", "s126;;;e;schalter;steuerstrom;;?;#");
		messages.put("S129", "s129;;;e;schalter;stromabnehmer;;?;#");
		messages.put("S132", "s132;;;e;schalter;haupt;;?;#");
		messages.put("S172", "s172;;;e;schalter;kompressor;;?;#");
		messages.put("S169", "s169;;;e;schalter;zugsammelschiene;;?;#");
		messages.put("S311", "s311;;;e;schalter;zug;;?;#");
		messages.put("S316_1", "s316_1;;;e;schalter;dienstbel;1;?;#");
		messages.put("S316_2", "s316_2;;;e;schalter;dienstbel;2;?;#");
		messages.put("S140", "s140;;;e;schalter;richtung;;?;#");
		messages.put("S150_1", "s150_1;;;e;schalter;fahren;1;?;#");
		messages.put("S150_2", "s150_2;;;e;schalter;elbremsen;2;?;#");
		messages.put("S189", "s189;;;e;taste;pfeife;;?;#");
		messages.put("S242", "s242;;;e;taste;zugsicherung;quittierung;?;#");
		messages.put("S242.02", "s242.02;;;e;taste;zugsicherung;manöver;?;#");
		messages.put("S174", "s174;;;e;taste;ventillator;aus;?;#");
		messages.put("S276_1", "s276_1;;;e;schalter;bremse;r;?;#");
		messages.put("S276_2", "s276_2;;;e;schalter;bremse;p;?;#");
		messages.put("S276_3", "s276_3;;;e;schalter;bremse;g;?;#");
		messages.put("S241", "s241;;;e;hebel;bvhahn;;?;#");
		messages.put("S281", "s281;;;e;taste;schleuderschutz;;?;#");
		messages.put("S182.3", "s182.3;;;e;taste;türfreigabe;links;?;#");
		messages.put("S182.4", "s182.4;;;e;taste;türfreigabe;rechts;?;#");
		messages.put("S182", "s182;;;e;taste;türverriegelung;;?;#");
		messages.put("S324.1", "s324.1;;;e;schalter;fst;instrumente;?;#");
		messages.put("S324.2", "s324.2;;;e;tastschalter;fst;fahrplan;?;#");
		messages.put("S317", "s317;;;e;tastschalter;fst;aufblend;?;#");
		messages.put("S316.1", "s316.1;;;e;schalter;stirnlampe;links;?;#");
		messages.put("S316.2", "s316.2;;;e;schalter;stirnlampe;oben;?;#");
		messages.put("S316.3", "s316.3;;;e;schalter;stirnlampe;rechts;?;#");
		messages.put("S324", "s324;;;e;schalter;beleuchtung;führerstand;?;#");
		messages.put("S333", "s333;;;e;tastschalter;beleuchtung;instrumente;?;#");
		messages.put("S235", "s235;;;e;schalter;sifa;pedal;?;#");
		messages.put("W238_t", "w238_t;;;i;warntongeber;signum;tief;?;#");
		messages.put("W238_h", "w238_h;;;i;warntongeber;signum;hoch;?;#");
		messages.put("L317", "l317;;;i;lampe;fernlicht;dienstbeleuchtung;?;#");
		messages.put("L242b", "l242b;;;i;lampe;zugsicherung;warnung;?;#");
		messages.put("L242a", "l242a;;;i;lampe;zugsicherung;halt;?;#");
		messages.put("D94Vi", "d94vi;;;8bit;analoganzeige;geschwindigkeit;vist;?;#");
		messages.put("D94Vs", "d94vs;;;8bit;ziffernanzeige;geschwindigkeit;vsoll;?;#");
		messages.put("D94m", "d94m;;;8bit;analoganzeige;lzb;weganzeige;?;#");
		messages.put("D94LZB_Z5", "d94lzb_z5;;;i;ziffernanzeige;lzb;digitalanzeige;?;#");
		messages.put("D94LZB_Z3", "d94lzb_z3;;;i;ziffernanzeige;lzb;digitalanzeige;?;#");
		messages.put("L94_LZB_r", "l94_lzb_r;;;i;lampe;lzb;anzeige ;?;#");
		messages.put("L94_LZB_gr", "l94_lzb_gr;;;i;lampe;lzb;anzeige ;?;#");
		messages.put("L94_LZB_gb", "l94_lzb_gb;;;i;lampe;lzb;anzeige ;?;#");
		messages.put("L94_LZB_w", "l94_lzb_w;;;i;lampe;lzb;anzeige ;?;#");
		messages.put("A74", "a74;;;i;analog-instrument;spannung;fahrdraht;?;#");
		messages.put("A79", "a79;;;i;analog-instrument;strom;i_max;?;#");
		messages.put("A79.1", "a79.1;;;i;analog-instrument;strom;i_delta;?;#");
		messages.put("P13c_HB", "p13c_hb;;;p;;;;?;#");
		messages.put("P13c_HL", "p13c_hl;;;p;;;;?;#");
		messages.put("P13c_BZ", "p13c_bz;;;p;;;;?;#");
		messages.put("AO269", "ao269;;;ao;drucksensor;druck;hauptleitung;?;#");
		messages.put("AO173", "ao173;;;ao;drucksensor;druck;bremszylinder;?;#");
		messages.put("L281", "l281;;;i;lampe;meldelampe;schleudern;?;#");
		messages.put("L281", "l281;;;i;ep-ventil;zugsicherung;schnellbremse;?;#");
		messages.put("L83", "l83;;;i;lampe;meldelampe;zss;?;#");
		messages.put("L175", "l175;;;i;lampe;meldelampe;stoeventi;?;#");
		messages.put("L163", "l163;;;i;lampe;meldelampe;stufensch;?;#");
		messages.put("L181", "l181;;;i;lampe;meldelampe;abf;?;#");
		messages.put("L182.3", "l182.3;;;i;lampe;meldelampe;tür_l;?;#");
		messages.put("L185", "l185;;;i;lampe;meldelampe;tür_offen;?;#");
		messages.put("L182.4", "l182.4;;;i;lampe;meldelampe;tür_r;?;#");
		messages.put("L242.2", "l242.2;;;i;lampe;meldelampe;m-taste;?;#");
		messages.put("L318a", "l318a;;;i;lampe;dienstbel;weiss;?;#");
		messages.put("L318b", "l318b;;;i;lampe;dienstbel;warnsignal;?;#");
		messages.put("L325.2", "l325.2;;;i;lampe;fst;fahrplan;?;#");
	}
}

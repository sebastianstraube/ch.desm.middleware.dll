package ch.desm.middleware.modules.component.interlocking;

import java.util.HashMap;
import java.util.Map;

public class OMLFunctionMessages {
	public final static String PARAMETER_PLACEHOLDER = "\\?";
	
	public static Map<String, String> messages;
	
	public OMLFunctionMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	private void initialize(){
		messages.put("6.90.01", "6.90.01;;;e;taste;anforderungdurchfahrt;nach_emm;?;#");
		messages.put("6.91.02", "6.91.02;i;;e;taste;blockfba;nach_zb;?;#");
		messages.put("6.91.01", "6.91.01;i;;e;taste;blockfbf;nach_zb;?;#");
		messages.put("6.91.03", "6.91.03;i;;e;taste;blockrm;nach_zb;?;#");
		messages.put("6.91.04", "6.91.04;i;;e;taste;blockrm;nach_ln;?;#");
		messages.put("6.91.05", "6.91.05;i;;e;taste;blockfba;nach_ln;?;#");
		messages.put("6.91.06", "6.91.06;i;;e;taste;blockfbf;nach_ln;?;#");
		messages.put("6.91.07", "6.91.07;i;;e;taste;blockblu;alle_richtungen;?;#");
		messages.put("6.91.08", "6.91.08;i;;e;taste;gleis;richtung_emm;?;#");
		messages.put("6.91.09", "6.91.09;;;e;taste;wecker;abschalten;?;#");
		messages.put("6.91.10", "6.91.10;i;;e;taste;nottaste;weiche_1;?;#");
		messages.put("6.91.11", "6.91.11;;;e;taste;weichen;beleuchtung;?;#");
		messages.put("6.91.12", "6.91.12;;;e;taste;zeitschalter;umgehugn;?;#");
		messages.put("6.91.13", "6.91.13;i;;e;taste;nal;alle_fahrstrassen;?;#");
		messages.put("6.91.14", "6.91.14;i;;e;taste;gleis;richtung_zb;?;#");
		messages.put("6.91.15", "6.91.15;i;;e;taste;siu;alle_fahrstrassen;?;#");
		messages.put("7.91.01", "7.91.01;i;;e;schalterkontakt;ws1;endlage+;?;#");
		messages.put("7.91.02", "7.91.02;i;;e;schalterkontakt;ws1;endlage+_gedrückt;?;#");
		messages.put("7.91.04", "7.91.04;i;;e;schalterkontakt;ws1;endlage-;?;#");
		messages.put("7.91.06", "7.91.06;;;e;schalterkontakt;ws1;endlage-_gedrückt;?;#");
		messages.put("8.91.01", "8.91.01;;;e;schalterkontakt;fssegf;endlage;?;#");
		messages.put("8.91.02", "8.91.02;;;e;schalterkontakt;fssegf;endlage_gedrückt;?;#");
		messages.put("8.91.03", "8.91.03;;;e;schalterkontakt;fssegf;10°eg;?;#");
		messages.put("8.91.04", "8.91.04;;;e;schalterkontakt;fssegf;30°eg;?;#");
		messages.put("8.91.05", "8.91.05;;;e;schalterkontakt;fssegf;45°eg;?;#");
		messages.put("8.91.06", "8.91.06;;;e;schalterkontakt;fssegf;80°eg;?;#");
		messages.put("8.91.07", "8.91.07;;;e;schalterkontakt;fssegf;90°eg;?;#");
		messages.put("1.90.01", "1.90.01;o;;i;lampe;signalfstern;gestört;?;#");
		messages.put("1.90.02", "1.90.02;o;;i;lampe;signalf;notrot;?;#");
		messages.put("1.90.03", "1.90.03;;;i;lampe;signalf;grün_fb3;?;#");
		messages.put("1.90.04", "1.90.04;o;;i;lampe;signalf;rot;?;#");
		messages.put("1.90.05", "1.90.05;o;;i;lampe;signalf;grün_fb1;?;#");
		messages.put("1.90.06", "1.90.06;o;;i;lampe;signale;grün_fb1;?;#");
		messages.put("1.90.07", "1.90.07;o;;i;lampe;signale;rot;?;#");
		messages.put("1.90.08", "1.90.08;o;;i;lampe;signale;notrot;?;#");
		messages.put("1.90.09", "1.90.09;o;;i;lampe;signalg;grün_fb1;?;#");
		messages.put("1.90.10", "1.90.10;o;;i;lampe;signalg;rot;?;#");
		messages.put("1.90.11", "1.90.11;o;;i;lampe;signalg;grün_fb3;?;#");
		messages.put("1.90.12", "1.90.12;o;;i;lampe;signald;rot;?;#");
		messages.put("1.90.13", "1.90.13;;;i;lampe;signald;grün;?;#");
		messages.put("1.90.14", "1.90.14;o;;i;lampe;signalc;grün;?;#");
		messages.put("1.90.15", "1.90.15;o;;i;lampe;signalc;rot;?;#");
		messages.put("1.90.16", "1.90.16;o;;i;lampe;signalgstern;gestört;?;#");
		messages.put("1.91.21", "1.91.21;;;i;lampe;fbv;von_zb;?;#");
		messages.put("1.91.22", "1.91.22;o;;i;lampe;rm_möglich;nach_zb;?;#");
		messages.put("1.90.31", "1.90.31;o;;i;lampe;block_rot;von_ln;?;#");
		messages.put("1.90.32", "1.90.32;o;;i;lampe;block_weiss;von_ln;?;#");
		messages.put("1.90.33", "1.90.33;o;;i;lampe;block_weiss;nach_ln;?;#");
		messages.put("1.90.34", "1.90.34;o;;i;lampe;block_rot;nach_ln;?;#");
		messages.put("1.90.35", "1.90.35;o;;i;lampe;isolierung;egf;?;#");
		messages.put("1.90.36", "1.90.36;o;;i;lampe;isolierung;1;?;#");
		messages.put("1.90.37", "1.90.37;o;;i;lampe;fahrstrasse;ef;?;#");
		messages.put("1.90.38", "1.90.38;o;;i;lampe;isolierung;ef;?;#");
		messages.put("1.90.39", "1.90.39;o;;i;lampe;isolierung;cd;?;#");
		messages.put("1.90.40", "1.90.40;o;;i;lampe;fahrstrasse;gf;?;#");
		messages.put("1.90.41", "1.90.41;o;;i;lampe;block_rot;nach_zb;?;#");
		messages.put("1.90.42", "1.90.42;o;;i;lampe;block_weiss;nach_zb;?;#");
		messages.put("1.90.43", "1.90.43;o;;i;lampe;block_weiss;von_zb;?;#");
		messages.put("1.90.44", "1.90.44;o;;i;lampe;block_rot;von_zb;?;#");
		messages.put("1.91.02", "1.91.02;o;;i;lampe;fbv;von_ln;?;#");
		messages.put("1.91.03", "1.91.03;;;i;lampe;wecker;abschalten;?;#");
		messages.put("1.01.01", "1.01.01;;;i;lampe;ws1;freigabe;?;#");
		messages.put("1.01.02", "1.01.02;;;i;lampe;ws1;überwachung;?;#");
		messages.put("1.04.01", "1.04.01;;;i;lampe;fssegf;sperre;?;#");
		messages.put("1.04.02", "1.04.02;;;i;lampe;fssegf;kuppelstrom;?;#");
		messages.put("2.92.01", "2.92.01;o;;i;wecker;weichen;wecker;?;#");
		messages.put("2.92.02", "2.92.02;o;;i;wecker;block;wecker;?;#");
		messages.put("2.90.01", "2.90.01;o;;i;wecker;signale;wecker;?;#");
		messages.put("9.99.04", "9.99.04;;;i;sound;glocke;zug_von_emm;?;#");
		messages.put("9.99.05", "9.99.05;;;i;sound;glocke;zug_von_ln;?;#");
		messages.put("9.99.06", "9.99.06;;;i;sound;glocke;zug_von_zb;?;#");
		messages.put("9.99.07", "9.99.07;;;i;sound;glocke;zug_nach_emm;?;#");
		messages.put("9.99.08", "9.99.08;;;i;sound;glocke;zug_nach_ln;?;#");
		messages.put("9.99.09", "9.99.09;;;i;sound;glocke;zug_nach_zb;?;#");
		messages.put("9.99.10", "9.99.10;;;i;lampe;stellstrom;zeiger;?;#");
		messages.put("9.99.11", "9.99.11;;;i;lampe;weichenüberw;zeiger;?;#");
		messages.put("9.99.12", "9.99.12;;;i;lampe;schienenstrom;zeiger;?;#");
		messages.put("3.01.01", "3.01.01;o;;i;magnet;ws1;magnet;?;#");
		messages.put("3.04.01", "3.04.01;i;;i;magnet;fssegf;sperrmagnet;?;#");
		messages.put("3.04.02", "3.04.02;i;;i;magnet;fssegf;kuppelstrommagnet;?;#");
	}
}

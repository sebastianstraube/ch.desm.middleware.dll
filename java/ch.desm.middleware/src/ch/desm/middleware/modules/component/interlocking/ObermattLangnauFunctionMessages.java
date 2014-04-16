package ch.desm.middleware.modules.component.interlocking;

import java.util.HashMap;
import java.util.Map;

public class ObermattLangnauFunctionMessages {
	public final static String PARAMETER_PLACEHOLDER = "\\?";
	
	public static Map<String, String> messages;
	
	public ObermattLangnauFunctionMessages(){
		messages = new HashMap<String, String>();
		
		this.initialize();
	}
	
	private void initialize(){
		messages.put("6.90.01", "6.90.01;;e;taste;anf-durchfahrt;nach_emm;?;#");
		messages.put("6.91.02", "6.91.02;;e;taste;block-fba;nach_zb;?;#");
		messages.put("6.91.01", "6.91.01;;e;taste;block-fbf;nach_zb;?;#");
		messages.put("6.91.03", "6.91.03;;e;taste;block-rm;nach_zb;?;#");
		messages.put("6.91.04", "6.91.04;;e;taste;block-rm;nach_ln;?;#");
		messages.put("6.91.05", "6.91.05;;e;taste;block-fba;nach_ln;?;#");
		messages.put("6.91.06", "6.91.06;;e;taste;block-fbf;nach_ln;?;#");
		messages.put("6.91.07", "6.91.07;;e;taste;block-blu;alle_richtungen;?;#");
		messages.put("6.91.08", "6.91.08;;e;taste;gleis;richtung_emm;?;#");
		messages.put("6.91.09", "6.91.09;;e;taste;wecker;abschalten;?;#");
		messages.put("6.91.10", "6.91.10;;e;taste;nottaste;weiche_1;?;#");
		messages.put("6.91.11", "6.91.11;;e;taste;weichen;beleuchtung;?;#");
		messages.put("6.91.12", "6.91.12;;e;taste;zeitschalter;umgehugn;?;#");
		messages.put("6.91.13", "6.91.13;;e;taste;nal;alle_fahrstrassen;?;#");
		messages.put("6.91.14", "6.91.14;;e;taste;gleis;richtung_zb;?;#");
		messages.put("6.91.15", "6.91.15;;e;taste;siu;alle_fahrstrassen;?;#");
		messages.put("7.91.01", "7.91.01;;e;schalterkontakt;ws1;endlage+;?;#");
		messages.put("7.91.02", "7.91.02;;e;schalterkontakt;ws1;endlage+_gedrückt;?;#");
		messages.put("7.91.03", "7.91.03;;e;schalterkontakt;ws1;aus_endlage+;?;#");
		messages.put("7.91.04", "7.91.04;;e;schalterkontakt;ws1;endlage-;?;#");
		messages.put("7.91.06", "7.91.06;;e;schalterkontakt;ws1;endlage-_gedrückt;?;#");
		messages.put("7.91.07", "7.91.07;;e;schalterkontakt;ws1;aus_endlage-;?;#");
		messages.put("8.91.01", "8.91.01;;e;schalterkontakt;fss-egf;endlage;?;#");
		messages.put("8.91.02", "8.91.02;;e;schalterkontakt;fss-egf;endlage_gedrückt;?;#");
		messages.put("8.91.03", "8.91.03;;e;schalterkontakt;fss-egf;10°eg;?;#");
		messages.put("8.91.04", "8.91.04;;e;schalterkontakt;fss-egf;30°eg;?;#");
		messages.put("8.91.05", "8.91.05;;e;schalterkontakt;fss-egf;45°eg;?;#");
		messages.put("8.91.06", "8.91.06;;e;schalterkontakt;fss-egf;80°eg;?;#");
		messages.put("8.91.07", "8.91.07;;e;schalterkontakt;fss-egf;90°eg;?;#");
		messages.put("8.91.11", "8.91.11;;e;schalterkontakt;fss-egf;10°f;?;#");
		messages.put("8.91.12", "8.91.12;;e;schalterkontakt;fss-egf;30°f;?;#");
		messages.put("8.91.13", "8.91.13;;e;schalterkontakt;fss-egf;45°f;?;#");
		messages.put("8.91.14", "8.91.14;;e;schalterkontakt;fss-egf;80°f;?;#");
		messages.put("8.91.15", "8.91.15;;e;schalterkontakt;fss-egf;90°f;?;#");
		messages.put("1.90.01", "1.90.01;;i;lampe;f*;gestört;?;#");
		messages.put("1.90.02", "1.90.02;;i;lampe;f;notrot;?;#");
		messages.put("1.90.03", "1.90.03;;i;lampe;f;grün_fb3;?;#");
		messages.put("1.90.04", "1.90.04;;i;lampe;f;rot;?;#");
		messages.put("1.90.05", "1.90.05;;i;lampe;f;grün_fb1;?;#");
		messages.put("1.90.06", "1.90.06;;i;lampe;e;grün_fb1;?;#");
		messages.put("1.90.07", "1.90.07;;i;lampe;e;rot;?;#");
		messages.put("1.90.08", "1.90.08;;i;lampe;e;notrot;?;#");
		messages.put("1.90.09", "1.90.09;;i;lampe;g;grün_fb1;?;#");
		messages.put("1.90.10", "1.90.10;;i;lampe;g;rot;?;#");
		messages.put("1.90.11", "1.90.11;;i;lampe;g;grün_fb3;?;#");
		messages.put("1.90.12", "1.90.12;;i;lampe;d;rot;?;#");
		messages.put("1.90.13", "1.90.13;;i;lampe;d;grün;?;#");
		messages.put("1.90.14", "1.90.14;;i;lampe;c;grün;?;#");
		messages.put("1.90.15", "1.90.15;;i;lampe;c;rot;?;#");
		messages.put("1.90.16", "1.90.16;;i;lampe;g*;gestört;?;#");
		messages.put("1.91.21", "1.91.21;;i;lampe;fbv;von_zb;?;#");
		messages.put("1.91.22", "1.91.22;;i;lampe;rm_möglich;nach_zb;?;#");
		messages.put("1.90.31", "1.90.31;;i;lampe;block_rot;von_ln;?;#");
		messages.put("1.90.32", "1.90.32;;i;lampe;block_weiss;von_ln;?;#");
		messages.put("1.90.33", "1.90.33;;i;lampe;block_weiss;nach_ln;?;#");
		messages.put("1.90.34", "1.90.34;;i;lampe;block_rot;nach_ln;?;#");
		messages.put("1.90.35", "1.90.35;;i;lampe;isolierung;egf;?;#");
		messages.put("1.90.36", "1.90.36;;i;lampe;isolierung;1;?;#");
		messages.put("1.90.37", "1.90.37;;i;lampe;fahrstrasse;ef;?;#");
		messages.put("1.90.38", "1.90.38;;i;lampe;isolierung;ef;?;#");
		messages.put("1.90.39", "1.90.39;;i;lampe;isolierung;cd;?;#");
		messages.put("1.90.40", "1.90.40;;i;lampe;fahrstrasse;gf;?;#");
		messages.put("1.90.41", "1.90.41;;i;lampe;block_rot;nach_zb;?;#");
		messages.put("1.90.42", "1.90.42;;i;lampe;block_weiss;nach_zb;?;#");
		messages.put("1.90.43", "1.90.43;;i;lampe;block_weiss;von_zb;?;#");
		messages.put("1.90.44", "1.90.44;;i;lampe;block_rot;von_zb;?;#");
		messages.put("1.91.01", "1.91.01;;i;lampe;rm_möglich;nach_ln;?;#");
		messages.put("1.91.02", "1.91.02;;i;lampe;fbv;von_ln;?;#");
		messages.put("1.91.03", "1.91.03;;i;lampe;wecker;abschalten;?;#");
		messages.put("1.01.01", "1.01.01;;i;lampe;ws1;freigabe;?;#");
		messages.put("1.01.02", "1.01.02;;i;lampe;ws1;überwachung;?;#");
		messages.put("1.04.01", "1.04.01;;i;lampe;fss-egf;sperre;?;#");
		messages.put("1.04.02", "1.04.02;;i;lampe;fss-egf;kuppelstrom;?;#");
		messages.put("2.92.01", "2.92.01;;i;wecker;weichen;wecker;?;#");
		messages.put("2.92.02", "2.92.02;;i;wecker;block;wecker;?;#");
		messages.put("2.90.01", "2.90.01;;i;wecker;signale;wecker;?;#");
		messages.put("9.99.04", "9.99.04;;i;sound;glocke;zug_von_emm;?;#");
		messages.put("9.99.05", "9.99.05;;i;sound;glocke;zug_von_ln;?;#");
		messages.put("9.99.06", "9.99.06;;i;sound;glocke;zug_von_zb;?;#");
		messages.put("9.99.07", "9.99.07;;i;sound;glocke;zug_nach_emm;?;#");
		messages.put("9.99.08", "9.99.08;;i;sound;glocke;zug_nach_ln;?;#");
		messages.put("9.99.09", "9.99.09;;i;sound;glocke;zug_nach_zb;?;#");
		messages.put("9.99.10", "9.99.10;;i;lampe;stellstrom;zeiger;?;#");
		messages.put("9.99.11", "9.99.11;;i;lampe;weichenüberw;zeiger;?;#");
		messages.put("9.99.12", "9.99.12;;i;lampe;schienenstrom;zeiger;?;#");
		messages.put("3.01.01", "3.01.01;;i;magnet;ws1;magnet;?;#");
		messages.put("3.04.01", "3.04.01;;i;magnet;fss-egf;sperrmagnet;?;#");
		messages.put("3.04.02", "3.04.02;;i;magnet;fss-egf;kuppelstrommagnet;?;#");
	}
}

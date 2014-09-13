package ch.desm.middleware.component.cabine.re420.maps;

import java.util.Map;

import org.apache.log4j.Logger;

import ch.desm.middleware.component.ComponentMapBase;

public class Re420MapFahrschalter extends ComponentMapBase {

	private static Logger log = Logger.getLogger(Re420MapFahrschalter.class);
	
	@Override
	public Map<String, String> getMap() {
		return map;
	}
	
	@Override
	protected void initialize() {		
		map.put("fahrschalter.bremsen.plus", "fahrschalter.bremsen.plus;i;0;fahrschalter;bremse;plus;?;kabinere420;#");
		map.put("fahrschalter.bremsen.punkt", "fahrschalter.bremsen.punkt;i;0;fahrschalter;bremse;punkt;?;kabinere420;#");
		map.put("fahrschalter.bremsen.minus", "fahrschalter.bremsen.minus;i;0;fahrschalter;bremse;minus;?;kabinere420;#");
		map.put("fahrschalter.neutral", "fahrschalter.neutral;i;0;fahrschalter;neutral;;?;kabinere420;#");
		map.put("fahrschalter.fahren.minus", "fahrschalter.fahren.minus;i;0;fahrschalter;fahren;minus;?;kabinere420;#");
		map.put("fahrschalter.fahren.punkt", "fahrschalter.fahren.punkt;i;0;fahrschalter;fahren;punkt;?;kabinere420;#");
		map.put("fahrschalter.fahren.m", "fahrschalter.fahren.m;i;0;fahrschalter;fahren;m;?;kabinere420;#");
		map.put("fahrschalter.fahren.plus", "fahrschalter.fahren.plus;i;0;fahrschalter;fahren;plus;?;kabinere420;#");
		map.put("fahrschalter.fahren.plusplus", "fahrschalter.fahren.plusplus;i;0;fahrschalter;fahren;plusplus;?;kabinere420;#");
	}

}
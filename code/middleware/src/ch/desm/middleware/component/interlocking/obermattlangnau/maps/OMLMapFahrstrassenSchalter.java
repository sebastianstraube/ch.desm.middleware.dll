package ch.desm.middleware.component.interlocking.obermattlangnau.maps;

import java.util.Map;

import ch.desm.middleware.component.ComponentMapBase;

public class OMLMapFahrstrassenSchalter extends ComponentMapBase {

	@Override
	public Map<String, String> getMap() {
		return map;
	}

	@Override
	protected void initialize() {
		map.put("8.91.02", "8.91.02;i;0;schalterkontakt;fssegf;endlage_gedrückt;?;stellwerkobermattlangnau;#");
		map.put("8.91.01", "8.91.01;ia;0;schalterkontakt;fssegf;endlage;?;stellwerkobermattlangnau;#"); //FSS Grundstellung
		map.put("8.91.03", "8.91.03;ia;0;schalterkontakt;fssegf;10°f;?;stellwerkobermattlangnau;#"); //FSS 10� F
		map.put("8.91.04", "8.91.04;ia;0;schalterkontakt;fssegf;30°f;?;stellwerkobermattlangnau;#"); //FSS 30� F
		map.put("8.91.05", "8.91.05;ia;0;schalterkontakt;fssegf;45°f;?;stellwerkobermattlangnau;#"); //FSS 45� F
		map.put("8.91.06", "8.91.06;ia;0;schalterkontakt;fssegf;80°f;?;stellwerkobermattlangnau;#"); //FSS 80� F
		map.put("8.91.07", "8.91.07;ia;0;schalterkontakt;fssegf;90°f;?;stellwerkobermattlangnau;#"); //FSS 90� F
		map.put("8.91.19", "8.91.19;ia;0;schalterkontakt;fssegf;10°eg;?;stellwerkobermattlangnau;#"); //FSS 10� EG
		map.put("8.91.20", "8.91.20;ia;0;schalterkontakt;fssegf;30°eg;?;stellwerkobermattlangnau;#"); //FSS 30� EG
		map.put("8.91.21", "8.91.21;ia;0;schalterkontakt;fssegf;45°eg;?;stellwerkobermattlangnau;#"); //FSS 45� EG
		map.put("8.91.22", "8.91.22;ia;0;schalterkontakt;fssegf;80°eg;?;stellwerkobermattlangnau;#"); //FSS 80� EG
		map.put("8.91.23", "8.91.23;ia;0;schalterkontakt;fssegf;90°eg;?;stellwerkobermattlangnau;#"); //FSS 90� EG 
	}

}

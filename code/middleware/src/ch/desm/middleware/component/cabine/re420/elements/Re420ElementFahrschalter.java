package ch.desm.middleware.component.cabine.re420.elements;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ch.desm.middleware.component.cabine.re420.maps.Re420MapFahrschalter;

public class Re420ElementFahrschalter {

	private static Logger log = Logger.getLogger(Re420ElementFahrschalter.class);
	
	private Boolean s150a;
	private Boolean s150b;
	private Boolean s150d;
	private Boolean s150e;
	private Boolean s150f;
	private Boolean s150g;
	private Boolean s150l;

	/**
	 * TODO refactoring
	 */
	public ArrayList<String> keyListFahrschalter;
	private Re420MapFahrschalter map;
	
	public Re420ElementFahrschalter(){
		super();
		map = new Re420MapFahrschalter();
		initializeFahrschalter();
	}
	
	public Re420MapFahrschalter getMap(){
		return map;
	}
	
	private boolean isInitialized(){
		
		if (s150a != null
				&& s150b != null
				&& s150d != null
				&& s150e != null
				&& s150f != null
				&& s150g != null
				&& s150l != null){
			
			return true;
		}
		
		return false;
	}
	/**
	 * 
	 * @return the global id (key) from the buffered contacts
	 */
	public String getMessagePositionFahrschalter(String key, boolean isEnabled) {		
		boolean isChanged = isChanged(key, isEnabled);
		boolean isFarschalterInitialized = isInitialized();
		
		if(isChanged && isFarschalterInitialized){
			
			if (!s150a
					&& !s150b
					&& !s150d
					&& !s150e
					&& !s150f
					&& !s150g
					&& !s150l) {
				key = "fahrschalter.neutral";
			}
			else if (s150b
					&& s150d
					&& s150e
					&& s150g
					&& s150l) {
				key = "fahrschalter.fahren.m";
			}
			else if (s150b
					&& s150e
					&& s150f
					&& s150g
					&& s150l) {
				key = "fahrschalter.fahren.plusplus";
			}
			else if (s150a && s150e
					&& s150g
					&& s150l) {
				key = "fahrschalter.bremsen.plus";
			} 
			else if (s150a
					&& s150g) {
				key = "fahrschalter.bremsen.punkt";
			} 
			else if (s150a
					&& s150l) {
				key = "fahrschalter.bremsen.minus";
			}
			
			else if (s150b
					&& s150l) {
				key = "fahrschalter.fahren.minus";
			} 
			else if (s150b
					&& s150g) {
				key = "fahrschalter.fahren.punkt";
			} 
			
			else if (s150b
					&& s150e
					&& s150g) {
				key = "fahrschalter.fahren.plus";
			}
			
			else{
				if(keyListFahrschalter.contains(key)){
					log.trace("fahrschalter position unknown with key " + key);
					key = "";
				}
			}
		}else{
			if(!isChanged || !isFarschalterInitialized){
				key = "";
			}
		}
		
		return key;
	}

	private boolean isChanged(String key, boolean value) {
		boolean isChanged = false;

        if (key.equals("s150a")) {
            if (s150a == null || !s150a.equals(value)) {
                s150a = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        } else if (key.equals("s150b")) {
            if (s150b == null || !s150b.equals(value)) {
                s150b = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        } else if (key.equals("s150d")) {
            if (s150d == null || !s150d.equals(value)) {
                s150d = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        } else if (key.equals("s150e")) {
            if (s150e == null || !s150e.equals(value)) {
                s150e = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        } else if (key.equals("s150f")) {
            if (s150f == null || !s150f.equals(value)) {
                s150f = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        } else if (key.equals("s150g")) {
            if (s150g == null || !s150g.equals(value)) {
                s150g = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        } else if (key.equals("s150l")) {
            if (s150l == null || !s150l.equals(value)) {
                s150l = value;
                isChanged = true;
            } else {
                isChanged = false;
            }
        }
		
		return isChanged;
	}
	
	private void initializeFahrschalter(){
		keyListFahrschalter = new ArrayList<String>();
		keyListFahrschalter.add("s150a");
		keyListFahrschalter.add("s150b");
		keyListFahrschalter.add("s150d");
		keyListFahrschalter.add("s150e");
		keyListFahrschalter.add("s150f");
		keyListFahrschalter.add("s150g");
		keyListFahrschalter.add("s150l");
	}

}
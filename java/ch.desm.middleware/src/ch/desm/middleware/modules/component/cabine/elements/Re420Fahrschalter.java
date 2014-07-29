package ch.desm.middleware.modules.component.cabine.elements;

import java.util.ArrayList;

public class Re420Fahrschalter {

	private ArrayList<String> list;
	private Boolean s150a;
	private Boolean s150b;
	private Boolean s150d;
	private Boolean s150e;
	private Boolean s150f;
	private Boolean s150g;
	private Boolean s150l;
	public boolean change;

	public Re420Fahrschalter() {
		list = new ArrayList<String>();

		list.add("s150a"); // Fahrschalter 150a
		list.add("s150b"); // Fahrschalter 150b
		list.add("s150d"); // Fahrschalter 150d
		list.add("s150e"); // Fahrschalter 150e
		list.add("s150f"); // Fahrschalter 150f
		list.add("s150g"); // Fahrschalter 150g
		list.add("s150l"); // Fahrschalter 150l
	}

	/**
	 * 
	 * @return the global id (key) from the buffered contacts
	 */
	public String getKey() {
		String id = "";

		if (s150a != null && s150b != null && s150d != null && s150e != null
				&& s150f != null && s150g != null && s150l != null)
		{
			if (s150a && s150e && s150g && s150l) {
				id = "cabine.fahrschalter.bremsen.plus";
			} else if (s150a && s150g) {
				id = "cabine.fahrschalter.bremsen.punkt";
			} else if (s150a && s150l) {
				id = "cabine.fahrschalter.bremsen.minus";
			} else if (!s150a && !s150b && !s150d && !s150e && !s150f && !s150g
					&& !s150l) {
				id = "cabine.fahrschalter.bremsen.neutral";
			} else if (s150b && s150l) {
				id = "cabine.fahrschalter.fahren.minus";
			} else if (s150b && s150g) {
				id = "cabine.fahrschalter.fahren.punkt";
			} else if (s150b && s150d && s150e && s150g && s150l) {
				id = "cabine.fahrschalter.fahren.m";
			} else if (s150b && s150e && s150g) {
				id = "cabine.fahrschalter.fahren.plus";
			} else if (s150b && s150e && s150f && s150g && s150l) {
				id = "cabine.fahrschalter.fahren.plusplus";
			}
		}
		
		if(id.equals("")){
			System.err.println("undefined Fahrschalter Stellung");
		}
		
		return id;
	}

	public void bufferRelevantData(String globalId, String parameter) {
		boolean value = Boolean.valueOf(parameter);

		switch (globalId) {
		case ("s150a"): {
			s150a = value;
			break;
		}
		case ("s150b"): {
			s150b = value;
			break;
		}
		case ("s150d"): {
			s150d = value;
			break;
		}
		case ("s150e"): {
			s150e = value;
			break;
		}
		case ("s150f"): {
			s150f = value;
			break;
		}
		case ("s150g"): {
			s150g = value;
			break;
		}
		case ("s150l"): {
			s150l = value;
			break;
		}
		}
	}

}

package ch.desm.middleware.modules.communication.endpoint.dll.objects;

public class EndpointDllObjectTrack {
	public int gleisId;
	public double von;
	public double bis;
	public double abstand;
	public String name;

	public EndpointDllObjectTrack(int gleisId, double von, double bis, double abstand,
			String name) {
		this.gleisId = gleisId;
		this.von = von;
		this.bis = bis;
		this.abstand = abstand;
		this.name = name;
	}

	@Override
	public String toString() {
		String s = "";
		s += "gleisId: " + gleisId;
		s += ", ";
		s += "von: " + von;
		s += ", ";
		s += "bis: " + bis;
		s += ", ";
		s += "abstand: " + abstand;
		s += ", ";
		s += "name: " + name;

		return s;
	}
}
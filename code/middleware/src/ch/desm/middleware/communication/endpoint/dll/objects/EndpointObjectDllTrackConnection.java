package ch.desm.middleware.communication.endpoint.dll.objects;

public class EndpointObjectDllTrackConnection {
	public int gleis1Id;
	public int gleis2Id;
	public double von;
	public double bis;
	public String name;
	public int weiche1Id;
	public int weiche2Id;

	public EndpointObjectDllTrackConnection(int gleis1Id, int gleis2Id, double von,
			double bis, String name, int weiche1Id, int weiche2Id) {
		this.gleis1Id = gleis1Id;
		this.gleis2Id = gleis2Id;
		this.von = von;
		this.bis = bis;
		this.name = name;
		this.weiche1Id = weiche1Id;
		this.weiche2Id = weiche2Id;
	}

	@Override
	public String toString() {
		String s = "";
		s += "gleis1Id: " + gleis1Id;
		s += ", ";
		s += "gleis2Id: " + gleis2Id;
		s += ", ";
		s += "von: " + von;
		s += ", ";
		s += "bis: " + bis;
		s += ", ";
		s += "name: " + name;
		s += ", ";
		s += "weiche1Id: " + weiche1Id;
		s += ", ";
		s += "weiche2Id: " + weiche2Id;

		return s;
	}
}


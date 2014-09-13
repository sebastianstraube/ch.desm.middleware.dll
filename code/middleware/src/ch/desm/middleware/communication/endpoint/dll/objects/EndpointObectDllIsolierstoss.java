package ch.desm.middleware.communication.endpoint.dll.objects;

public class EndpointObectDllIsolierstoss {
	public int isolierstossId;
	public int gleisid;
	public double position;

	public EndpointObectDllIsolierstoss(int isolierstossId, int gleisid, double position) {
		this.isolierstossId = isolierstossId;
		this.gleisid = gleisid;
		this.position = position;
	}

	@Override
	public String toString() {
		String s = "";
		s += "isolierstossId: " + isolierstossId;
		s += ", ";
		s += "gleisid: " + gleisid;
		s += ", ";
		s += "position: " + position;

		return s;
	}
}

package ch.desm.middleware.modules.communication.endpoint.dll.objects;

public class EndpointDllObectIsolierstoss {
	public int isolierstossId;
	public int gleisid;
	public double position;

	public EndpointDllObectIsolierstoss(int isolierstossId, int gleisid, double position) {
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

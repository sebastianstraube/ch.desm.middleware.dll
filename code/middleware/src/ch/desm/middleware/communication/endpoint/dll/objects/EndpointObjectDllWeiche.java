package ch.desm.middleware.communication.endpoint.dll.objects;

public class EndpointObjectDllWeiche {

	public int weicheId;
	public int gleisId;

	public EndpointObjectDllWeiche(int weicheId, int gleisId) {
		this.weicheId = weicheId;
		this.gleisId = gleisId;
	}

	@Override
	public String toString() {
		String s = "";
		s += "weicheId: " + weicheId;
		s += ", ";
		s += "gleisId:" + gleisId;

		return s;
	}

}

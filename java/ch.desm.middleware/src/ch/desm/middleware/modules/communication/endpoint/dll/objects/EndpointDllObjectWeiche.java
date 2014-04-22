package ch.desm.middleware.modules.communication.endpoint.dll.objects;

public class EndpointDllObjectWeiche {

	public int weicheId;
	public int gleisId;

	public EndpointDllObjectWeiche(int weicheId, int gleisId) {
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

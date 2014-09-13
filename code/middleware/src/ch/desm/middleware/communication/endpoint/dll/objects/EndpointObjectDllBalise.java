package ch.desm.middleware.communication.endpoint.dll.objects;

public class EndpointObjectDllBalise {
	public int baliseId;
	public int stellung;
	public String protokoll;

	public EndpointObjectDllBalise(int baliseId, int stellung, String protokoll) {
		this.baliseId = baliseId;
		this.stellung = stellung;
		this.protokoll = protokoll;
	}

	@Override
	public String toString() {
		String s = "";
		s += "baliseId: " + baliseId;
		s += ", ";
		s += "stellung: " + stellung;
		s += ", ";
		s += "protokoll: " + protokoll;

		return s;
	}
}

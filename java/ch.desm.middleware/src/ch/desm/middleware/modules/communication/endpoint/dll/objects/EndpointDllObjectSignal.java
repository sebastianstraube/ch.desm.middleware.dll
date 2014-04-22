package ch.desm.middleware.modules.communication.endpoint.dll.objects;

public class EndpointDllObjectSignal {
	public int signalId;
	public String name;
	public int stellung;

	public EndpointDllObjectSignal(int signalId, String name, int stellung) {
		this.signalId = signalId;
		this.name = name;
		this.stellung = stellung;
	}

	@Override
	public String toString() {
		String s = "";
		s += "signalId: " + signalId;
		s += ", ";
		s += "name: " + name;
		s += ", ";
		s += "stellung: " + stellung;

		return s;
	}
}

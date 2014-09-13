package ch.desm.middleware.communication.endpoint.dll;

import java.util.ArrayList;

public class EndpointDllEvent {
	public int type;
	public ArrayList<Integer> params;

	public EndpointDllEvent(int type, ArrayList<Integer> params) {
		this.type = type;
		this.params = params;
	}
}

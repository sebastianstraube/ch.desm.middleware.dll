package ch.desm.middleware.communication.endpoint.dll.objects;

import java.util.ArrayList;

public class EndpointObjectDllTrainPosition {
	public int trainTyp;
	public int direction;
	public ArrayList positions;
	public ArrayList gleisList;

	public EndpointObjectDllTrainPosition(int trainTyp, int direction, ArrayList positions,
			ArrayList gleisList) {
		this.trainTyp = trainTyp;
		this.direction = direction;
		this.positions = positions;
		this.gleisList = gleisList;
	}

	@Override
	public String toString() {
		String s = "";
		s += "trainTyp: " + trainTyp;
		s += ", ";
		s += "direction: " + direction;
		s += ", ";
		s += "positions: " + positions;
		s += ", ";
		s += "gleisList: " + gleisList;

		return s;
	}
}

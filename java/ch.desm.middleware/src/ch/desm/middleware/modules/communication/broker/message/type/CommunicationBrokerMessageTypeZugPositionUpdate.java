package ch.desm.middleware.modules.communication.broker.message.type;

import ch.desm.middleware.modules.communication.broker.message.CommunicationBrokerMessage;

public class CommunicationBrokerMessageTypeZugPositionUpdate extends CommunicationBrokerMessage {
	private Double x;
	private Double y;
	private Double z;

	public CommunicationBrokerMessageTypeZugPositionUpdate(int id, Double x, Double y, Double z) {
		super(id);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}
}
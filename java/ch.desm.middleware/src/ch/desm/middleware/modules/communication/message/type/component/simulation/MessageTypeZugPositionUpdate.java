package ch.desm.middleware.modules.communication.message.type.component.simulation;

import ch.desm.middleware.modules.communication.message.MessageBase;

public class MessageTypeZugPositionUpdate extends MessageBase {
	private Double x;
	private Double y;
	private Double z;

	public MessageTypeZugPositionUpdate(String payload, Double x, Double y, Double z) {
		super(payload);
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
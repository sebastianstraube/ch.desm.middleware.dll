package ch.desm.middleware.modules.communication.broker.message.type.component.simulation;

import ch.desm.middleware.modules.communication.broker.message.MessageCommon;

public class MessageZugPositionUpdate extends MessageCommon {
	private Double x;
	private Double y;
	private Double z;

	public MessageZugPositionUpdate(EnumMessageType messageType, int parameterTypeId, Double x, Double y, Double z) {
		super(messageType, parameterTypeId);
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
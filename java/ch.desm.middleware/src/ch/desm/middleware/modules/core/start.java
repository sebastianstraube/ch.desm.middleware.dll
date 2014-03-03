package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.serial.Rs232.EnumSerialPorts;
import ch.desm.middleware.modules.communication.serial.ubw32.Ubw32;
import ch.desm.middleware.modules.components.impl.Locsim;


public class start {

	public static void main(String[] args) {

	Ubw32 ubw32Controller = new Ubw32(EnumSerialPorts.COM5);
	Locsim locsimSimulator = new Locsim();
	

	}

}

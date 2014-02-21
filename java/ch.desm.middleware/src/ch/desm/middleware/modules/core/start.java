package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.controller.Rs232.EnumSerialPorts;
import ch.desm.middleware.modules.communication.controller.impl.Ubw32;
import ch.desm.middleware.modules.components.impl.Locsim;
import ch.desm.middleware.modules.service.CommunicationAdapterService;


public class start {

	public static void main(String[] args) {

	Ubw32 ubw32Controller = new Ubw32(EnumSerialPorts.COM5);
	Locsim locsimSimulator = new Locsim();
	
	CommunicationAdapterService service = new CommunicationAdapterService(ubw32Controller, locsimSimulator);
	ubw32Controller.testCommunication();

	}

}

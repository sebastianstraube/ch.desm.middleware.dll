package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDll;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.simulation.LocsimEndpointDesmDll;
import ch.desm.middleware.modules.component.simulation.LocsimEndpointRs232;
import ch.desm.middleware.modules.component.simulation.LocsimEndpointUbw32;
import ch.desm.middleware.modules.component.simulation.LocsimImplDll;
import ch.desm.middleware.modules.component.simulation.LocsimImplRs232;
import ch.desm.middleware.modules.component.simulation.LocsimImplUbw32;

public class Main {

	public static void main(String[] args) {

		Broker broker = new Broker();

		//1# CABINE <> LOCSIM (CABINE FUNCTIONS) - connect to 3#
		LocsimEndpointRs232 communicationEndpointSimulationRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM8);
		
		//2# LOCSIM <> INTERLOCKING (INTERLOCKING FUNCTIONS)
		LocsimEndpointDesmDll communicationEndpointSimulationDll = new LocsimEndpointDesmDll();
		
		//3# CABINE <> LOCSIM (CABINE FUNCTIONS)
		LocsimEndpointUbw32 communicationEndpointSimulationUbw32 = new LocsimEndpointUbw32(EnumSerialPorts.COM6);
		
		LocsimImplDll componentSimulationLocsimDll = new LocsimImplDll(
				broker, communicationEndpointSimulationDll);
		
		LocsimImplRs232 componentSimulationLocsimRs232 = new LocsimImplRs232(
				broker, communicationEndpointSimulationRs232);
		
		LocsimImplUbw32 componentSimulationLocsimUbw32 = new LocsimImplUbw32(
				broker, communicationEndpointSimulationUbw32);
		
//		MessageCommon message = new Stufenschalter1(EnumMessageType.CABINE, 1, "test common message");
		
		componentSimulationLocsimRs232.emulateEndpointMessage("stufenschalter.1.off");
	}
	
	/* Test Case 1
	 CommunicationBroker broker = new CommunicationBroker();

		CommunicationEndpointRs232 communicationEndpointSimulation = new CommunicationEndpointUbw32(EnumSerialPorts.COM6);
		CommunicationEndpointRs232 communicationEndpointInterlocking = new CommunicationEndpointUbw32(EnumSerialPorts.COM8);

		ComponentSimulationLocsim componentSimulationLocsim = new ComponentSimulationLocsim(
				broker, communicationEndpointSimulation);
		
		ComponentInterlockingObermattLangau componentInterlockingObermattLangnau = new ComponentInterlockingObermattLangau(
				broker, communicationEndpointInterlocking);

//		communicationEndpointVirtualLocsim.emulateIncomingEndpointMessage("test message from virtual locsim endpoint");
//		communicationEndpointCabine.emulateIncomingEndpointMessage("test message from virtual cabine endpoint");
		

		CommunicationBrokerMessage messageFromLocsim = new CommunicationBrokerMessage(EnumComponentType.SIMULATION, "C,14784,199,65505,16383,64528,52939,64575");
		CommunicationBrokerMessage messageFromInterlocking = new CommunicationBrokerMessage(EnumComponentType.INTERLOCKING, "C,14784,199,65505,16383,64528,52939,64575");
		
		
		componentSimulationLocsim.emulateEndpointMessage("C,14784,199,65505,16383,64528,52939,64575");
//		componentSimulationLocsim.emulateBrokerMessage(messageFromLocsim);
//		componentInterlockingObermattLangnau.emulateBrokerMessage(messageFromInterlocking);
//		componentInterlockingObermattLangnau.emulateEndpointMessage("C,14784,199,65505,16383,64528,52939,64575");
	 */

}

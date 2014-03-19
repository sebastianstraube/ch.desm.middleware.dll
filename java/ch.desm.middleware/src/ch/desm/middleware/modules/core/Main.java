package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.broker.message.BrokerMessageCommon;
import ch.desm.middleware.modules.communication.endpoint.dll.EndpointDesmDll;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.component.ComponentBase.EnumComponentType;
import ch.desm.middleware.modules.component.cabine.Re420;
import ch.desm.middleware.modules.component.simulation.Locsim;

public class Main {

	public static void main(String[] args) {

		Broker broker = new Broker();

		EndpointUbw32 communicationEndpointSimulationUbw32 = new EndpointUbw32(EnumSerialPorts.COM8);
		EndpointDesmDll communicationEndpointSimulationDll = new EndpointDesmDll();
//		CommunicationEndpointUbw32 communicationEndpointInterlocking = new CommunicationEndpointUbw32(EnumSerialPorts.COM8);
		EndpointUbw32 communicationEndpointCabine = new EndpointUbw32(EnumSerialPorts.COM6);

		Locsim componentSimulationLocsimDll = new Locsim(
				broker, communicationEndpointSimulationDll);
		
		Locsim componentSimulationLocsimUbw32 = new Locsim(
				broker, communicationEndpointSimulationUbw32);
		
//		ComponentInterlockingObermattLangau componentInterlockingObermattLangnau = new ComponentInterlockingObermattLangau(
//				broker, communicationEndpointInterlocking);
		
		Re420 componentCabineRe420 = new Re420(
				broker, communicationEndpointCabine);

//		communicationEndpointVirtualLocsim.emulateIncomingEndpointMessage("test message from virtual locsim endpoint");
//		communicationEndpointCabine.emulateIncomingEndpointMessage("test message from virtual cabine endpoint");
		

//		CommunicationBrokerMessageCommon messageFromLocsim = new CommunicationBrokerMessageCommon(EnumComponentType.SIMULATION, 1, "SIMULATION");
//		CommunicationBrokerMessageCommon messageFromInterlocking = new CommunicationBrokerMessageCommon(EnumComponentType.INTERLOCKING, 2, "INTERLOCKING");
//		CommunicationBrokerMessageCommon messageFromCabine = new CommunicationBrokerMessageCommon(EnumComponentType.CABINE, 3, "CABINE");
		
		
		componentSimulationLocsimDll.emulateEndpointMessage("DLL");
//		componentSimulationLocsimUbw32.emulateEndpointMessage("UBW32");
		
//		componentSimulationLocsimDll.emulateBrokerMessage(messageFromLocsim);
//		componentInterlockingObermattLangnau.emulateBrokerMessage(messageFromInterlocking);
//		componentInterlockingObermattLangnau.emulateEndpointMessage("C,14784,199,65505,16383,64528,52939,64575");
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

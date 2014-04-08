package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.cabine.Re420EndpointConfiguration;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.cabine.Re420EndpointImplUbw32;
import ch.desm.middleware.modules.component.simulation.LocsimEndpointDesmDll;
import ch.desm.middleware.modules.component.simulation.LocsimEndpointRs232;
import ch.desm.middleware.modules.component.simulation.LocsimImplDll;
import ch.desm.middleware.modules.component.simulation.LocsimImplRs232;
import ch.desm.middleware.modules.component.simulation.LocsimNative;

public class Main {

	public static void main(String[] args) {
//
//		Broker broker = new Broker();
//
//		//1# CABINE <> LOCSIM (CABINE FUNCTIONS) - connect to 3#
//		LocsimEndpointRs232 communicationEndpointSimulationRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM8);
//		
//		//2# LOCSIM <> INTERLOCKING (INTERLOCKING FUNCTIONS)
//		LocsimEndpointDesmDll communicationEndpointSimulationDll = new LocsimEndpointDesmDll();
//		
//		//3# CABINE <> LOCSIM (CABINE FUNCTIONS)
//		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM6);
//		
//		LocsimImplDll componentSimulationLocsimDll = new LocsimImplDll(
//				broker, communicationEndpointSimulationDll);
//		
//		LocsimImplRs232 locsimImpRs232 = new LocsimImplRs232(
//				broker, communicationEndpointSimulationRs232);
//		
//		Re420EndpointImplUbw32 re420ImplUbw32 = new Re420EndpointImplUbw32(
//				broker, re420EndpointUbw32);
//
//		re420ImplUbw32.emulateEndpointMessage("1.90.31;??????");
		
		new LocsimNative();
	}
}

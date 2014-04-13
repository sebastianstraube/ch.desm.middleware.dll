package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauEndpointUbw32;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauImplUbw32;

public class Main {

	public static void main(String[] args) {
//
		Broker broker = new Broker();
//
//		//1# CABINE <> LOCSIM (CABINE FUNCTIONS) - connect to 3#
//		LocsimEndpointRs232 communicationEndpointSimulationRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM8);
//		
//		//2# LOCSIM <> INTERLOCKING (INTERLOCKING FUNCTIONS)
		ObermattLangnauEndpointUbw32 omlEndpoint = new ObermattLangnauEndpointUbw32(EnumSerialPorts.COM22);
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
		ObermattLangnauImplUbw32 omlImpl = new ObermattLangnauImplUbw32(broker, omlEndpoint);
//		try {
//		omlEndpoint.sendCommandT1("500", "1");
		
//		omlEndpoint.sendCommandOutputState("0,0,0,0,64,0,0");
		
//		Thread.sleep(10000);
		
//		omlEndpoint.sendCommandOutputState("0,0,0,0,32,0,0");
		
//		omlEndpoint.sendCommandOutputState("32801,25096,0,0,0,0,256");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32801,25096,0,0,0,256,256");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32800,25096,0,0,0,288,768");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32800,25608,0,0,0,292,768");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32800,25608,0,16384,0,36,768");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32801,50184,0,16384,0,32,256");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32801,50184,0,0,0,32,256");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32801,17416,0,0,0,32,256");
//		
//		Thread.sleep(5000);
//		
//		omlEndpoint.sendCommandOutputState("32801,17416,0,0,0,0,256");
//		
//		Thread.sleep(5000);

//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		omlEndpoint.sendCommandInputState();
		
//		re420ImplUbw32.emulateEndpointMessage("1.90.31;??????");
		
//		new LocsimNative();
		
//		SocketServer endpoint = new SocketServer(1024);
	}
}

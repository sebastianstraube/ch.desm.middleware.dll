package ch.desm.middleware.modules.core;

import ch.desm.middleware.modules.communication.broker.Broker;
import ch.desm.middleware.modules.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.modules.component.cabine.Re420BaseImpl;
import ch.desm.middleware.modules.component.cabine.Re420EndpointUbw32;
import ch.desm.middleware.modules.component.simulation.locsim.LocsimBaseImpl;
import ch.desm.middleware.modules.component.simulation.locsim.LocsimEndpointDll;
import ch.desm.middleware.modules.component.simulation.locsim.LocsimEndpointRs232;

public class Main {

	public static void main(String[] args) {
		testCaseLocsimEndpointRs232_to_Cabine();
		
		//LocsimEndpointRs232Parser.runTests();
		
		// testCaseEndpointToEndpoint();

		// testPWM(args);
	}
	
	/**
	 * 
	 */
	public static void testCaseLocsimEndpointDllRs232() {
		System.out.println(System.getProperty("java.library.path"));
		
		Broker broker = new Broker();

		//Test Cabine
//		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(
//				EnumSerialPorts.COM13);
//		Re420BaseImpl re420Impl = new Re420BaseImpl(broker, re420EndpointUbw32);
		
		//Test Interlocking
//		OMLEndpointUbw32 omlEndpoint = new OMLEndpointUbw32(EnumSerialPorts.COM12);
//		OMLBaseImpl OmlImpl = new OMLBaseImpl(broker, omlEndpoint);
		
		//Test Simulation
		LocsimEndpointDll endpointDll = new LocsimEndpointDll("dispatcher.json");
		LocsimEndpointRs232 endpointRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM5);
		LocsimBaseImpl locsimImpl = new LocsimBaseImpl(broker, endpointRs232, endpointDll);
	}
	
	/**
	 * 
	 */
	public static void testCaseLocsimEndpointRs232_to_Cabine() {
		
		Broker broker = new Broker();

		//Test Cabine
		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(
				EnumSerialPorts.COM13);
		Re420BaseImpl re420Impl = new Re420BaseImpl(broker, re420EndpointUbw32);
		
		//Test Interlocking
//		OMLEndpointUbw32 omlEndpoint = new OMLEndpointUbw32(EnumSerialPorts.COM12);
//		OMLBaseImpl OmlImpl = new OMLBaseImpl(broker, omlEndpoint);
		
		//Test Simulation
		LocsimEndpointDll endpointDll = new LocsimEndpointDll("dispatcher.json");
		LocsimEndpointRs232 endpointRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM5);
		LocsimBaseImpl locsimImpl = new LocsimBaseImpl(broker, endpointRs232, endpointDll);
	}
	
	public static void testCaseDll() {
		System.out.println("java.library.path");
		
		Broker broker = new Broker();

		LocsimEndpointDll locsimEndpointDll = new LocsimEndpointDll(
				"C:\\Users\\J�rg\\Dropbox\\DESM-Verein\\Projekte\\DESM-Middleware\\Middleware\\Dispatcher-DLL\\Debug\\dispatcher.json");
		LocsimEndpointRs232 locsimEndpointRs232 = new LocsimEndpointRs232(
				EnumSerialPorts.COM22);

		LocsimBaseImpl locsim = new LocsimBaseImpl(broker, locsimEndpointRs232,
				locsimEndpointDll);
	}

	/**
	 * The "PM" Command : Set hardware PWM output values command Sets a PWM
	 * value for any of the 5 PWM hardware channels Format:
	 * "PM,<Channel>,<DutyCycle><CR>" <Channel> is required and is 1 through 5,
	 * which correspond to the output compare (OC0 through OC4 = RD0 through
	 * RD4) pins. <DutyCycle> is required and is 0 through 65535. If <DutyCycle>
	 * is 0, then the hardware PWM is shut off. The PWM frequency is 1220Hz
	 * (80MHz/0x10000) Example: "PM,3,512" Return Packet: "OK"
	 */
	public static void testPWM(String[] args) {

		String comPort = "1";
		String channel = "1";
		int dutyCycle = Integer.parseInt("1");
		boolean autoCycle = false;
		int stepInkrementation = 1;
		try {
			if (args.length == 0) {
				throw new Exception("no parameter!");
			} else {
				if (Integer.parseInt(comPort) <= 0
						|| Integer.parseInt(comPort) >= 23) {
					throw new Exception("wrong com Port!");
				}

				if (Integer.parseInt(channel) <= 0
						|| Integer.parseInt(channel) >= 6) {
					throw new Exception("wrong channel!");
				}

				if (dutyCycle <= 0 || dutyCycle >= 65535) {
					throw new Exception("wrong duty cycle!");
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (args.length == 1) {
			comPort = args[0];
		} else if (args.length <= 2) {
			comPort = args[0];
			channel = args[1];
		} else if (args.length <= 3) {
			comPort = args[0];
			channel = args[1];
			dutyCycle = Integer.parseInt(args[2]);
		} else if (args.length <= 4) {
			comPort = args[0];
			channel = args[1];
			dutyCycle = Integer.parseInt(args[2]);
			autoCycle = args[3].equals("1");
		} else if (args.length <= 5) {
			comPort = args[0];
			channel = args[1];
			dutyCycle = Integer.parseInt(args[2]);
			autoCycle = args[3].equals("1");
			stepInkrementation = Integer.parseInt(args[4]);
		}

		Re420EndpointUbw32 re420EndpointUbw32;

		switch (comPort) {
		case "1":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM1);
			break;
		case "2":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM2);
			break;
		case "3":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM3);
			break;
		case "4":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM4);
			break;
		case "5":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM5);
			break;
		case "6":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM6);
			break;
		case "7":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM7);
			break;
		case "8":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM8);
			break;
		case "9":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM9);
			break;
		case "10":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM10);
			break;
		case "11":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM11);
			break;
		case "12":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM12);
			break;
		case "13":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM13);
			break;
		case "14":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM14);
			break;
		case "15":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM15);
			break;
		case "16":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM16);
			break;
		case "17":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM17);
			break;
		case "18":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM18);
			break;
		case "19":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM19);
			break;
		case "20":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM20);
			break;
		case "21":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM21);
			break;
		case "22":
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM22);
			break;
		default:
			re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM1);
			break;
		}

		re420EndpointUbw32.sendCommandPwm(channel, String.valueOf(dutyCycle));

		while (autoCycle) {
			if (dutyCycle > 65535) {
				dutyCycle = 0;
			}
			re420EndpointUbw32.sendCommandPwm(channel,
					String.valueOf(dutyCycle));

			dutyCycle += stepInkrementation;
		}

	}
}

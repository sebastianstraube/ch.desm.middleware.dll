package ch.desm.middleware.core;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.communication.endpoint.serial.EndpointRs232.EnumSerialPorts;
import ch.desm.middleware.communication.endpoint.serial.fabisch.EndpointFabisch;
import ch.desm.middleware.communication.endpoint.serial.ubw32.EndpointUbw32Impl;
import ch.desm.middleware.communication.endpoint.tcp.EndpointTcpClientImpl;
import ch.desm.middleware.component.cabine.re420.Re420;
import ch.desm.middleware.component.cabine.re420.Re420EndpointFabisch;
import ch.desm.middleware.component.cabine.re420.Re420EndpointUbw32;
import ch.desm.middleware.component.interlocking.obermattlangnau.OML;
import ch.desm.middleware.component.interlocking.obermattlangnau.OMLEndpointUbw32;
import ch.desm.middleware.component.petrinet.obermattlangnau.OMLPetriNet;
import ch.desm.middleware.component.petrinet.obermattlangnau.OMLPetriNetEndpoint;
import ch.desm.middleware.component.simulation.locsim.Locsim;
import ch.desm.middleware.component.simulation.locsim.LocsimEndpointDll;
import ch.desm.middleware.component.simulation.locsim.LocsimEndpointRs232;



public class Main {

	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		
//		testEndpointTcpIp();
		
//		testEndpointFabisch();
		
//		testEndpointPetriNet();
		
//		testCabine();
		
//		testCaseLocsimEndpointRs232_to_CabineUbw32(); //
		
//		LocsimEndpointRs232Parser.runTests();
		
//		testCaseEndpointToEndpoint();

//		testPWM(args);
		
//		testCaseUbw32();
		
		testCaseOMLEndpointUbw32();
		
		hangoutThread();
	}
	
	
	/**
	 * 
	 */
	public static void testEndpointTcpIp(){
		
//		EndpointTcpServer_ s = new EndpointTcp(7100);
		
		EndpointTcpClientImpl c = new EndpointTcpClientImpl("EndpointTcpClient", "127.0.0.1", 7100);
		
//		c.sendStream("test stream!");		
	}
	
	/**
	 * 
	 */
	public static void testCaseLocsimEndpointDllRs232() {
		log.trace(System.getProperty("java.library.path"));
		
		Broker broker = new Broker();

		//Test Cabine
		Re420EndpointUbw32 re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM13);
//		Re420BaseImpl re420Impl = new Re420BaseImpl(broker, re420EndpointUbw32);
		
		//Test Interlocking
		OMLEndpointUbw32 omlEndpoint = new OMLEndpointUbw32(EnumSerialPorts.COM12);
		OML OmlImpl = new OML(broker, omlEndpoint);
		
		//Test Simulation
		LocsimEndpointDll endpointDll = new LocsimEndpointDll("dispatcher.json");
		LocsimEndpointRs232 endpointRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM5);
		Locsim locsimImpl = new Locsim(broker, endpointRs232, endpointDll);
	}
	
	/**
	 * 
	 */
	public static void testCaseOMLEndpointUbw32() {		
		Broker broker = new Broker();
		
		//Test Interlocking
//		OMLEndpointUbw32 omlEndpoint = new OMLEndpointUbw32(EnumSerialPorts.COM33);
//		OML OmlImpl = new OML(broker, omlEndpoint);
		
		//Test Petrinet
		OMLPetriNetEndpoint omlPetriNetEndpoint = new OMLPetriNetEndpoint();
		OMLPetriNet omlPetriNet = new OMLPetriNet(broker, omlPetriNetEndpoint);
		
		//Test Isolierung belegt
		omlPetriNet.emulateBrokerMessage("10.99.07;i;1;kontakt;isolierung;belegt;on;stellwerkobermattlangnau;#");
		
	}

	/**
	 * 
	 */
	public static void testCabine(){
		
		Broker broker = new Broker();

		Re420EndpointUbw32 re420Endpoint = new Re420EndpointUbw32(EnumSerialPorts.COM30);
		Re420EndpointFabisch re420EndpointFabisch = new Re420EndpointFabisch(EnumSerialPorts.COM31);
		Re420 re420Impl = new Re420(broker, re420Endpoint, re420EndpointFabisch);
		
		re420Impl.emulateBrokerMessage("locsim.initialization.ready.ini1;os;0;message;initialisiation;ini1;?;locsim-rs232;#");
		
		re420Endpoint.setCacheEnabled(true);
		
		//test analog output
		re420Impl.emulateBrokerMessage("a74;o;0;analog-instrument;spannung;fahrdraht;FF;kabinere420;#");	
		re420Impl.emulateBrokerMessage("a79;o;0;analog-instrument;strom;i_max;FF;kabinere420;#");
		re420Impl.emulateBrokerMessage("a79.1;o;0;analog-instrument;strom;i_delta;FF;kabinere420;#");		
		re420Impl.emulateBrokerMessage("d94vi;o;0;analog-instrument;geschwindigkeitsanzeige;ist;FF;kabinere420;#");
	}
	
	/**
	 * 
	 */
	public static void testCaseLocsimEndpointRs232_to_CabineUbw32() {
		
		Broker broker = new Broker();

		//Test Cabine
		Re420EndpointUbw32 re420Endpoint = new Re420EndpointUbw32(EnumSerialPorts.COM24);
		Re420EndpointFabisch re420EndpointFabisch = new Re420EndpointFabisch(EnumSerialPorts.COM25);
		Re420 re420Impl = new Re420(broker, re420Endpoint, re420EndpointFabisch);
		
		//Test Simulation
		LocsimEndpointDll locsimEndpointDll = new LocsimEndpointDll("dispatcher.json");
		LocsimEndpointRs232 locsimEndpointRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM23);
		Locsim locsimImpl = new Locsim(broker, locsimEndpointRs232, locsimEndpointDll);
		
		//Start Test sequence
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		
//		locsimEndpointRs232.emulateEndpointMessage("INI1");
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
//		
//		locsimEndpointRs232.emulateEndpointMessage("INI7");
	}
	
	/**
	 * 
	 */
	public static void testEndpointFabisch(){
		
		EndpointFabisch endpointFabisch = new EndpointFabisch(EnumSerialPorts.COM31);
		
	}
	
	/**
	 * 
	 */
	public static void testEndpointPetriNet(){
        Broker broker = new Broker();

        OMLPetriNetEndpoint endpoint = new OMLPetriNetEndpoint();
        OMLPetriNet petriNetBase = new OMLPetriNet(broker, endpoint);
        
		//Test Interlocking
		OMLEndpointUbw32 omlEndpoint = new OMLEndpointUbw32(EnumSerialPorts.COM33);
		OML OmlImpl = new OML(broker, omlEndpoint);
        
		//test case
//		petriNetBase.emulateBrokerMessage("1.90.04;o;0;lampe;signalf;rot;on;stellwerkobermattlangnau;#");
		
	}
	
	/**
	 * 
	 */
	public static void testCaseDll() {
//		log.trace("java.library.path");
		
		Broker broker = new Broker();

		LocsimEndpointDll locsimEndpointDll = new LocsimEndpointDll("dispatcher.json");
		LocsimEndpointRs232 locsimEndpointRs232 = new LocsimEndpointRs232(EnumSerialPorts.COM22);

		Locsim locsim = new Locsim(broker, locsimEndpointRs232, locsimEndpointDll);
	}
	
	/**
	 * 
	 */
	public static void testCaseUbw32(){
		EndpointUbw32Impl endpoint = new EndpointUbw32Impl(EnumSerialPorts.COM33, "192,0,8192,16372,0,1,2", "0");

//		endpoint.sendCommandInputState();
		endpoint.sendCommandPinOutput("G", "8", "1");
		
		endpoint.startPolling();
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

		args = new String[]{"18"};
		
		String comPort = "18";
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
			log.error(e);
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

        if (comPort.equals("1")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM1);

        } else if (comPort.equals("2")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM2);

        } else if (comPort.equals("3")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM3);

        } else if (comPort.equals("4")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM4);

        } else if (comPort.equals("5")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM5);

        } else if (comPort.equals("6")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM6);

        } else if (comPort.equals("7")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM7);

        } else if (comPort.equals("8")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM8);

        } else if (comPort.equals("9")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM9);

        } else if (comPort.equals("10")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM10);

        } else if (comPort.equals("11")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM11);

        } else if (comPort.equals("12")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM12);

        } else if (comPort.equals("13")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM13);

        } else if (comPort.equals("14")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM14);

        } else if (comPort.equals("15")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM15);

        } else if (comPort.equals("16")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM16);

        } else if (comPort.equals("17")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM17);

        } else if (comPort.equals("18")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM18);

        } else if (comPort.equals("19")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM19);

        } else if (comPort.equals("20")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM20);

        } else if (comPort.equals("21")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM21);

        } else if (comPort.equals("22")) {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM22);

        } else {
            re420EndpointUbw32 = new Re420EndpointUbw32(EnumSerialPorts.COM1);

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
	
	
	public static void hangoutThread(){
		
		while(true){
			pauseThread(10000000);
		}
	}
	
	public static void pauseThread(long val){
		
		try {
			Thread.sleep(val);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

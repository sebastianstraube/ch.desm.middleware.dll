package ch.desm.middleware.communication.message;

import java.util.ArrayList;
import java.util.Arrays;

import ch.desm.middleware.communication.endpoint.serial.ubw32.EndpointUbw32RegisterAnalog;
import ch.desm.middleware.component.cabine.re420.maps.Re420MapUbw32Analog;

public class MessageUbw32Analog extends MessageUbw32Base {

	/**
	 * TODO refactoring
	 */
	// Port B
	private EndpointUbw32RegisterAnalog portAN0 = new EndpointUbw32RegisterAnalog("1");
	private EndpointUbw32RegisterAnalog portAN1 = new EndpointUbw32RegisterAnalog("2");
	private EndpointUbw32RegisterAnalog portAN2 = new EndpointUbw32RegisterAnalog("4");
	private EndpointUbw32RegisterAnalog portAN3 = new EndpointUbw32RegisterAnalog("8");
	private EndpointUbw32RegisterAnalog portAN4 = new EndpointUbw32RegisterAnalog("16");
	private EndpointUbw32RegisterAnalog portAN5 = new EndpointUbw32RegisterAnalog("32");
	private EndpointUbw32RegisterAnalog portAN6 = new EndpointUbw32RegisterAnalog("64");
	private EndpointUbw32RegisterAnalog portAN7 = new EndpointUbw32RegisterAnalog("128");
	private EndpointUbw32RegisterAnalog portAN8 = new EndpointUbw32RegisterAnalog("256");
	private EndpointUbw32RegisterAnalog portAN9 = new EndpointUbw32RegisterAnalog("512");
	private EndpointUbw32RegisterAnalog portAN10 = new EndpointUbw32RegisterAnalog("1024");
	private EndpointUbw32RegisterAnalog portAN11 = new EndpointUbw32RegisterAnalog("2048");
	private EndpointUbw32RegisterAnalog portAN12 = new EndpointUbw32RegisterAnalog("5096");
	private EndpointUbw32RegisterAnalog portAN13 = new EndpointUbw32RegisterAnalog("10192");
	private EndpointUbw32RegisterAnalog portAN14 = new EndpointUbw32RegisterAnalog("20384");
	private EndpointUbw32RegisterAnalog portAN15 = new EndpointUbw32RegisterAnalog("40768");

	public MessageUbw32Analog(String payload, String topic) {
		super(payload, topic);

		this.parsePayload(payload);
	}

	/**
	 * 
	 * @param payload
	 *            (IA,3,0,1IA,174,175)
	 */
	protected void parsePayload(String payload) {

		payload = payload.substring(payload.lastIndexOf("A,")+2, payload.length());
		String parts[] = payload.split(",");

		ArrayList<String> list = new ArrayList<String>(Arrays.asList(parts));

		int nrPorts = Integer.toBinaryString(Integer
				.valueOf(Re420MapUbw32Analog.PINBITMASK_INPUT_ANALOG)).length();

		int i=0;
		while (!list.isEmpty()) {

				switch (i%nrPorts) {
					case (0): {
						portAN0.addValue(list.get(0));
						break;
					}
					case (1): {
						portAN1.addValue(list.get(0));
						break;
					}
					case (2): {
						portAN2.addValue(list.get(0));
						break;
					}
					case (3): {
						portAN3.addValue(list.get(0));
						break;
					}
					case (4): {
						portAN4.addValue(list.get(0));
						break;
					}
					case (5): {
						portAN5.addValue(list.get(0));
						break;
					}
					case (6): {
						portAN6.addValue(list.get(0));
						break;
					}
					case (7): {
						portAN7.addValue(list.get(0));
						break;
					}
					case (8): {
						portAN8.addValue(list.get(0));
						break;
					}
					case (9): {
						portAN9.addValue(list.get(0));
						break;
					}
					case (10): {
						portAN10.addValue(list.get(0));
						break;
					}
					case (11): {
						portAN11.addValue(list.get(0));
						break;
					}
					case (12): {
						portAN12.addValue(list.get(0));
						break;
					}
					case (13): {
						portAN13.addValue(list.get(0));
						break;
					}
					case (14): {
						portAN14.addValue(list.get(0));
						break;
					}
					case (15): {
						portAN15.addValue(list.get(0));
						break;
					}
				
				}

				list.remove(0);
				i++;
		}
	}

	/**
	 * 
	 */
	@Override
	public String getInputValue(String register, String pin) {
		String value = "";

        if (register.equals("B0")) {
            value = portAN0.getAverageValue();
        } else if (register.equals("B1")) {
            value = portAN1.getAverageValue();
        } else if (register.equals("B2")) {
            value = portAN2.getAverageValue();
        } else if (register.equals("B3")) {
            value = portAN3.getAverageValue();
        } else if (register.equals("B4")) {
            value = portAN4.getAverageValue();
        } else if (register.equals("B5")) {
            value = portAN5.getAverageValue();
        } else if (register.equals("B6")) {
            value = portAN6.getAverageValue();
        } else if (register.equals("B7")) {
            value = portAN7.getAverageValue();
        } else if (register.equals("B8")) {
            value = portAN8.getAverageValue();
        } else if (register.equals("B9")) {
            value = portAN9.getAverageValue();
        } else if (register.equals("B10")) {
            value = portAN10.getAverageValue();
        } else if (register.equals("B11")) {
            value = portAN11.getAverageValue();
        } else if (register.equals("B12")) {
            value = portAN12.getAverageValue();
        } else if (register.equals("B13")) {
            value = portAN13.getAverageValue();
        } else if (register.equals("B14")) {
            value = portAN14.getAverageValue();
        } else if (register.equals("B15")) {
            value = portAN15.getAverageValue();
        }

		return value;
	}

	/**
	 * TODO implementation
	 */
	public String toString() {
		String s = super.toString();
		s += "PortAN0: " + portAN0;
		s += "PortAN1: " + portAN1;
		s += "PortAN2: " + portAN2;
		s += "PortAN3: " + portAN3;
		s += "PortAN4: " + portAN4;
		s += "PortAN5: " + portAN5;
		s += "PortAN6: " + portAN6;
		s += "PortAN7: " + portAN7;
		s += "PortAN8: " + portAN8;
		s += "PortAN9: " + portAN9;
		s += "PortAN10: " + portAN10;
		s += "PortAN11: " + portAN11;
		s += "PortAN12: " + portAN12;
		s += "PortAN13: " + portAN13;
		s += "PortAN14: " + portAN14;
		s += "PortAN15: " + portAN15;

		return s;
	}
}

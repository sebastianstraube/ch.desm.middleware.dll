package ch.desm.middleware.modules.communication.message.router;

import ch.desm.middleware.modules.communication.endpoint.serial.ubw32.EndpointUbw32;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslator;
import ch.desm.middleware.modules.communication.message.type.MessageCommon;
import ch.desm.middleware.modules.component.ComponentBase;
import ch.desm.middleware.modules.component.cabine.Re420Ubw32;
import ch.desm.middleware.modules.component.interlocking.ObermattLangnauEndpointUbw32;

/**
 * 
 * @author Sebastian
 * 
 */
public class MessageRouter {

	private MessageTranslator translator;

	public MessageRouter() {
		this.translator = new MessageTranslator();
	}

	/**
	 * 
	 * TODO Implementation
	 * 
	 * @param component
	 * @param message
	 */
	public void processEndpointMessage(ComponentBase component, String message) {
		
		component.publish(message);
	}

	/**
	 * 
	 * @param message
	 * @return true if the ubw32 returns a state package
	 */
	private boolean isReturnMessage(String message) {
		boolean isReturnMessage = false;

		if (message.startsWith(EndpointUbw32.RETURN_INPUT_ANALOG)) {
			isReturnMessage = true;
		} else if (message.startsWith(EndpointUbw32.RETURN_INPUT_STATE)) {
			isReturnMessage = true;
		} else if (message.startsWith(EndpointUbw32.RETURN_PIN_INPUT)) {
			isReturnMessage = true;
		}

		return isReturnMessage;
	}

	public void processBrokerMessage(Re420Ubw32 endpoint,
			MessageCommon messageBase) {

		System.out.println("processing message:" + messageBase);

		if (endpoint.getConfiguration().isKeyAvailable(
				messageBase.getGlobalId())) {
			String register = endpoint.getConfiguration().map.get(messageBase
					.getGlobalId());
			String port = register.substring(0, 1);
			String pin = register.substring(1, register.length());
			String value = messageBase.getParameter();
			boolean isReturnMessage = messageBase.isReturnMessage();

			switch (messageBase.getGlobalId()) {
			case ("S126"): {
			}
				; // Steuerstrom
			case ("S129"): {
			}
				; // Stromabnehmer
			case ("S132"): {

				if (isReturnMessage) {
					endpoint.setHauptschalter(port, pin, value);
				} else {
					endpoint.getHauptschalter(port, pin);
				}

			}
				; // Hauptschalter
			case ("S172.1"): {
			}
				; // Kompressor Automat
			case ("S172.2"): {
			}
				; // Kompressor direkt
			case ("S169"): {
			}
				; // Zugsammelschiene
			case ("S311"): {
			}
				; // Beleuchtung Zug
			case ("S316_1"): {
			}
				; // Dienstbeleuchtung 1
			case ("S140a"): {
			}
				; // Wendeschalter 140a vorwärts
			case ("S140b"): {
			}
				; // Wendeschalter 140b rückwärts
			case ("S150a"): {
			}
				; // Fahrschalter 150a
			case ("S150b"): {
			}
				; // Fahrschalter 150b
			case ("S150d"): {
			}
				; // Fahrschalter 150d
			case ("S150e"): {
			}
				; // Fahrschalter 150e
			case ("S150f"): {
			}
				; // Fahrschalter 150f
			case ("S150g"): {
			}
				; // Fahrschalter 150g
			case ("S150l"): {
			}
				; // Fahrschalter 150l
			case ("S189.1"): {
			}
				; // Pfeife Stufe 1
			case ("S189.2"): {
			}
				; // Pfeife Stufe 2
			case ("S242.01"): {
			}
				; // Rückstelltaste Zugsicherung
			case ("S242.03"): {
			}
				; // Rückstelltaste ZUB befreien
			case ("S242.02"): {
			}
				; // M-Taste
			case ("S281"): {
			}
				; // Schleuderschutztaste
			case ("S182.3"): {
			}
				; // Türfreigabe links
			case ("S182.4"): {
			}
				; // Türfreigabe rechts
			case ("S182"): {
			}
				; // Türverriegelung
			case ("S316.1"): {
			}
				; // Stirnlampe links weiss
			case ("S316.4"): {
			}
				; // Stirnlampe links rot
			case ("S316.2"): {
			}
				; // Strinlampe oben weiss
			case ("S316.5"): {
			}
				; // Strinlampe oben rot
			case ("S316.3"): {
			}
				; // Stirnlampe rechts weiss
			case ("S316.6"): {
			}
				; // Stirnlampe rechts rot
			case ("S235"): {
			}
				; // Totmannpedal
			case ("W238_t"): {
			}
				; // Tiefton (Schnellgang, Signum, Totmann)
			case ("W238_h"): {
			}
				; // Hochton (Langsamgang)
			case ("L242b"): {
			}
				; // Signumschalter gelb
			case ("L242a"): {
			}
				; // Signumschalter rot
			case ("D94VI.1"): {
			}
				; // Vist-LZB.1
			case ("D94VI.2"): {
			}
				; // Vist-LZB.2
			case ("D94VI.3"): {
			}
				; // Vist-LZB.3
			case ("D94VI.4"): {
			}
				; // Vist-LZB.4
			case ("D94VI.5"): {
			}
				; // Vist-LZB.5
			case ("D94VI.6"): {
			}
				; // Vist-LZB.6
			case ("D94VI.7"): {
			}
				; // Vist-LZB.7
			case ("D94VI.8"): {
			}
				; // Vist-LZB.8
			case ("D94u.1"): {
			}
				; // Uhr (hh)
			case ("D94u.2"): {
			}
				; // Uhr (mm)
			case ("D94U.3"): {
			}
				; // Uhr
			case ("AO269"): {
			}
				; // Drucksensor Hauptleitung
			case ("AO173"): {
			}
				; // Drucksensor Bremszylinder
			case ("L281"): {
			}
				; // Lampe Schleuderbremse
			case ("L281.1"): {
			}
				; // Schleuderbremse von Simulator
			case ("L175"): {
			}
				; // Lampe Ventilation/ Oelpumpe
			case ("L163"): {
			}
				; // Lampe Stufenschalter
			case ("L181"): {
			}
				; // Lampe Abfahrbefehl
			case ("L182.3"): {
			}
				; // Lampe Türfreigabe links
			case ("L185"): {
			}
				; // Lampe Tür offen
			case ("L182.4"): {
			}
				; // Lampe Türfreigabe rechts
			case ("L242.2"): {
			}
				; // Lampe M-Taste
			default:
				System.err.println("global message ID not found in");
			}

		}
	}

	public void processBrokerMessage(ObermattLangnauEndpointUbw32 endpoint,
			MessageCommon messageBase) {

		System.out.println("processing message:" + messageBase);

		if (endpoint.getConfiguration().isKeyAvailable(
				messageBase.getGlobalId())) {
			String register = endpoint.getConfiguration().mapDigital.get(messageBase
					.getGlobalId());
			String port = register.substring(0, 1);
			String pin = register.substring(1, register.length());
			String value = messageBase.getParameter();
			boolean isReturnMessage = messageBase.isReturnMessage();

			switch (messageBase.getGlobalId()) {

			default:
				System.err.println("global message ID not found in");
			}
		}
	}

}

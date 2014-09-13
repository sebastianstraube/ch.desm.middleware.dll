package ch.desm.middleware.component.petrinet.obermattlangnau;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ch.desm.middleware.communication.broker.Broker;
import ch.desm.middleware.communication.message.MessageBase;
import ch.desm.middleware.communication.message.MessageMiddleware;
import ch.desm.middleware.communication.message.processor.MessageProcessor;
import ch.desm.middleware.communication.message.translator.MessageTranslatorMiddleware;
import ch.desm.middleware.component.interlocking.obermattlangnau.maps.OMLMapMiddleware;
import ch.desm.middleware.component.petrinet.obermattlangnau.map.OMLPetriNetMap;

/**
 * Created by max on 06/08/14.
 */
public class OMLPetriNet extends OMLPetriNetBase {

    protected static Logger log = Logger.getLogger(OMLPetriNet.class);

    OMLPetriNetMap petriNetMap = new OMLPetriNetMap();
    MessageTranslatorMiddleware translator = new MessageTranslatorMiddleware();
    MessageProcessor processor = new MessageProcessor();

    public OMLPetriNet(Broker broker, OMLPetriNetEndpoint endpoint) {
        super(broker);
        this.endpoint = endpoint;
        this.registerEndpointListener(endpoint);
    }

    @Override
    protected void onIncomingBrokerMessage(String message) {
    	log.info("petrinet (" + this.getClass() + ") received broker message: " + message);
    	
        ArrayList<MessageMiddleware> messageCommon = translator.translateToCommonMiddlewareMessageList(message);
        processor.processBrokerMessage(this, messageCommon);
    }

    public void processBrokerMessage(MessageMiddleware message) {
        try {
            String globalId = message.getGlobalId();
            String sensorName = petriNetMap.mapBrokerToEndpointMessage(globalId);
            int sensorValue = message.getParameter().equals("on") ? 1 : 0;            
            endpoint.setSensor(sensorName, sensorValue);
        } catch (Exception e) {
        	log.warn(e);
        }
    }

    @Override
    public void onIncomingEndpointMessage(String firedTransition) {
        try {
        	log.info("petrinet (" + this.getClass() + ") received endpoint message: " + firedTransition);
        	
        	OMLMapMiddleware map = new OMLMapMiddleware();
            String globalId = petriNetMap.mapEndpointToBrokerMessage(firedTransition);
            String message = map.getValue(globalId);
            
            //TODO refactoring
            String parameter = "";
            if(message.contains("$")){
            	parameter = message.split("$")[1];
            	
            	 if(parameter.equals("ein")){
                 	message.replaceAll("?", "on");
                 } else if(parameter.equals("aus")){
                 	message.replaceAll("?", "off");
                 }
            }else{
            	//TODO check
            	message.replaceAll("?", "on");
            }
            
            processor.processEndpointMessage(this, message, MessageBase.MESSAGE_TOPIC_PETRINET_OBERMATT_LANGNAU);
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
    }
    
    @Override
    protected void intializeSignedTopic() {
        signForTopic(MessageBase.MESSAGE_TOPIC_SIMULATION_LOCSIM_DLL);
        signForTopic(MessageBase.MESSAGE_TOPIC_INTERLOCKING_OBERMATT_LANGNAU);
    }

}

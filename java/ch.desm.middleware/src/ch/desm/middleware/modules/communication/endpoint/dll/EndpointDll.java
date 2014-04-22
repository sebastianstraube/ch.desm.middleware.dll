package ch.desm.middleware.modules.communication.endpoint.dll;

import ch.desm.Dll;
import ch.desm.middleware.modules.communication.endpoint.EndpointCommon;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectSignal;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectTrainPosition;
import ch.desm.middleware.modules.communication.endpoint.dll.objects.EndpointDllObjectWeiche;
import ch.desm.middleware.modules.communication.message.translator.MessageTranslatorDll;

public abstract class EndpointDll extends EndpointCommon implements
		EndpointDllListenerInterface {

	private static final int POLLING_WAIT_TIME = 1000;
	private Dll dll;
	private EndpointDllPolling eventPollingDaemonDll;
	
	protected MessageTranslatorDll messageTranslatorDllObjects;

	public EndpointDll(String configPath) {
		this.messageTranslatorDllObjects = new MessageTranslatorDll();
		
		dll = new Dll();
		dll.onStartProgramm(configPath);
		this.eventPollingDaemonDll = new EndpointDllPolling(
				"EndpointDesmDllPolling", POLLING_WAIT_TIME, dll, this);
		
		eventPollingDaemonDll.start();
	}

	public Dll getDll() {
		return dll;
	}

	public void receiveEndpointObject(Object obj){
		
		System.out.println("receive in endpoint " + this.getClass() + " an object " + obj.getClass());
		
		String message = "";
		
		if(obj instanceof EndpointDllObjectSignal){
			message = messageTranslatorDllObjects.translateObjectToMiddlewareMessage((EndpointDllObjectSignal)obj);
		
		}else if(obj instanceof EndpointDllObjectTrainPosition){
			message = messageTranslatorDllObjects.translateObjectToMiddlewareMessage((EndpointDllObjectTrainPosition)obj);
		
		}else if(obj instanceof EndpointDllObjectWeiche){
			message = messageTranslatorDllObjects.translateObjectToMiddlewareMessage((EndpointDllObjectWeiche)obj);
		}else{
			
			System.err.println("Translation does not support yet endpoint object: " + obj.getClass());
		}
		
		if(!message.isEmpty()){
			this.onIncomingEndpointMessage(message);
		}
	}

}

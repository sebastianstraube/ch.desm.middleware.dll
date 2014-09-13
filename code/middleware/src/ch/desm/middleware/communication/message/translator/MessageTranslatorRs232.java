package ch.desm.middleware.communication.message.translator;


public abstract class MessageTranslatorRs232 extends MessageTranslatorMiddlewareBase {
	
	public abstract String translateToCommonMiddlewareMessage(String payload, String topic);

}

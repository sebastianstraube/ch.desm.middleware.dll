package ch.desm.middleware.modules.communication.broker.message;

import ch.desm.middleware.modules.component.ComponentBase;

/**
 * TODO refactoring and implementation
 * 
 * @author Sebastian
 *
 */
public class BrokerMessageChannel {

    private ComponentBase sourceComponent;
	private BrokerMessageCommon message;
	
	public BrokerMessageChannel(ComponentBase sourceComponent, BrokerMessageCommon message){
		this.sourceComponent = sourceComponent;
		this.message = message;
	}
	
	
}

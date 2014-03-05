package ch.desm.middleware.modules.component;

import java.util.List;


public interface ComponentsBridge {
	
	public String getType();
    public List<String> getRequiredTypes();
}

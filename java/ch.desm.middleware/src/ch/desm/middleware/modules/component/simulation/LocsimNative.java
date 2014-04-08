package ch.desm.middleware.modules.component.simulation;

public class LocsimNative {

	public native void callinfoVersion(String version);
	
	static {
		System.loadLibrary("DesmMiddlewarePlugin");
	}
	

}

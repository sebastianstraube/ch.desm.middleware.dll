package ch.desm;

/**
 * example usage:
 *
 *   Dll dll = new Dll();
 *
 *   System.out.println(dll.infoName());
 *   System.out.println(dll.infoVersion());
 *   System.out.println(dll.infoDescription());
 *   System.out.println(dll.infoConnectionStatus());
 *
 *   dll.onStartProgramm("locsim.json");
 *   dll.onLoadStrecke();
 *   dll.onStartSimulation();
 *
 *   dll.setKilometerDirection(42);
 *   int richtung = dll.getKilometerDirection();
 *   System.out.println("KilometerDirection = " + richtung);
 *
 *   dll.setWeiche(42, 23);
 *   int gleisId = dll.getWeiche(42);
 *   System.out.println("Weiche = " + gleisId);
 *
 *   dll.onStopSimulation();
 *   dll.onStopProgramm();
 *
 */
public class Dll {

    static {
        System.loadLibrary("DesmMiddlewarePlugin");
    }

    public native String infoName();
    public native String infoVersion();
    public native String infoDescription();
    public native String infoConnectionStatus();

    public native void onStartProgramm(String configPath);
    public native void onStartSimulation();
    public native void onStopSimulation();
    public native void onStopProgramm();
    public native void onLoadStrecke();

    public native int getKilometerDirection();
    public native void setKilometerDirection(int richtung);

    public native int getWeiche(int weicheId);
    public native void setWeiche(int weicheId, int gleisId);

}

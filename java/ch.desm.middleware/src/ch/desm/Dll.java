package ch.desm;

import java.util.ArrayList;

/**
 * TODO: implement all stw_set* methods using the according inner classes
 *
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

    public static final int ENUM_CMD_TRACK = 1;
    public static final int ENUM_CMD_TRACK_CONNECTION = 2;
    public static final int ENUM_CMD_ISOLIERSTOSS = 3;
    public static final int ENUM_CMD_KILOMETER_DIRECTION = 4;
    public static final int ENUM_CMD_BALISE = 5;
    public static final int ENUM_CMD_SIGNAL = 6;
    public static final int ENUM_CMD_LOOP = 7;
    public static final int ENUM_CMD_WEICHE = 8;
    public static final int ENUM_CMD_TRAINPOSITION = 9;
    public static final int ENUM_CMD_STARTSIMULATION = 10;
    public static final int ENUM_CMD_STOPSIMULATION = 11;
    public static final int ENUM_CMD_LOADSTRECKE = 12;

    public native String infoName();
    public native String infoVersion();
    public native String infoDescription();
    public native String infoConnectionStatus();

    public native void onStartProgramm(String configPath);
    public native void onStartSimulation();
    public native void onStopSimulation();
    public native void onStopProgramm();
    public native void onLoadStrecke();

    public class Event {
        public int type;
        public ArrayList<Integer> params;
        public Event(int type, ArrayList<Integer> params) {
            this.type = type;
            this.params = params;
        }
    }
    // NOTE: not ideal implementation yet. many instantiations of ArrayLists!
    private native void getEvents0(ArrayList res);
    public ArrayList<Event> getEvents() throws Exception {
        ArrayList<Event> result = new ArrayList<Event>();
        ArrayList al = new ArrayList();
        getEvents0(al);
        for(int i = 0; i < al.size(); ++i) {
            ArrayList alEvt = (ArrayList)al.get(i);
            if(alEvt.size() < 1) {
                throw new Exception("invalid result returned");
            }
            int type = (int)alEvt.get(0);
            ArrayList<Integer> params = new ArrayList<Integer>();
            for(int j = 1; j < alEvt.size(); ++j) {
                params.add((int)alEvt.get(j));
            }
            result.add(new Event(type, params));
        }
        return result;
    }

    public native void setKilometerDirection(int richtung);
    private native void getKilometerDirection0(ArrayList res);
    public int getKilometerDirection() throws Exception {
        ArrayList al = new ArrayList();
        getKilometerDirection0(al);
        if(al.size() != 1) {
            throw new Exception("invalid result returned");
        }
        return (int)al.get(0);
    }

    public class Weiche {

        public int weicheId;
        public int gleisId;
        private Weiche(int weicheId, int gleisId) {
            this.weicheId = weicheId;
            this.gleisId = gleisId;
        }
    }
    public native void setWeiche(int weicheId, int gleisId);
    private native void getWeiche0(int weicheId, ArrayList res);
    public Weiche getWeiche(int weicheId) throws Exception {
        ArrayList al = new ArrayList();
        getWeiche0(weicheId, al);
        if(al.size() != 1) {
            throw new Exception("invalid result returned");
        }
        return new Weiche(weicheId, (int)al.get(0));
    }

    public class Balise {
        public int baliseId;
        public int stellung;
        public String protokoll;
        public Balise(int baliseId, int stellung, String protokoll) {
            this.baliseId = baliseId;
            this.stellung = stellung;
            this.protokoll = protokoll;
        }
    }
    public native void setBalise(int baliseId, int stellung, String protokoll);
    private native void getBalise0(int baliseId, ArrayList res);
    public Balise getBalise(int baliseId) throws Exception {
        ArrayList al = new ArrayList();
        getBalise0(baliseId, al);
        if(al.size() != 2) {
            throw new Exception("invalid result returned");
        }
        return new Balise(baliseId, (int)al.get(0), (String)al.get(1));
    }

    public class Isolierstoss {
        public int isolierstossId;
        public int gleisid;
        public double position;
        public Isolierstoss(int isolierstossId, int gleisid, double position) {
            this.isolierstossId = isolierstossId;
            this.gleisid = gleisid;
            this.position = position;
        }
    }
    public native void setIsolierstoss(int isolierstossId, int gleisId, double position);
    private native void getIsolierstoss0(int isolierstossId, ArrayList res);
    public Isolierstoss getIsolierstoss(int isolierstossId) throws Exception {
        ArrayList al = new ArrayList();
        getLoop0(isolierstossId, al);
        if(al.size() != 2) {
            throw new Exception("invalid result returned");
        }
        return new Isolierstoss(isolierstossId, (int)al.get(0), (double)al.get(1));
    }

    public class Loop {
        public int baliseId;
        public int stellung;
        public String protokoll;
        public Loop(int baliseId, int stellung, String protokoll) {
            this.baliseId = baliseId;
            this.stellung = stellung;
            this.protokoll = protokoll;
        }
    }
    public native void setLoop(int baliseId, int stellung, String protokoll);
    private native void getLoop0(int baliseId, ArrayList res);
    public Loop getLoop(int baliseId) throws Exception {
        ArrayList al = new ArrayList();
        getBalise0(baliseId, al);
        if(al.size() != 2) {
            throw new Exception("invalid result returned");
        }
        return new Loop(baliseId, (int)al.get(0), (String)al.get(1));
    }

    public class Signal {
        public int signalId;
        public String name;
        public int stellung;
        public Signal(int signalId, String name, int stellung) {
            this.signalId = signalId;
            this.name = name;
            this.stellung = stellung;
        }
    }
    // TODO: public native void setSignal(int baliseId, int stellung, String protokoll);
    private native void getSignal0(int signalId, ArrayList res);
    public Signal getSignal(int signalId) throws Exception {
        ArrayList al = new ArrayList();
        getSignal0(signalId, al);
        if(al.size() != 2) {
            throw new Exception("invalid result returned");
        }
        return new Signal(signalId, (String)al.get(0), (int)al.get(1));
    }

    public class Track {
        public int gleisId;
        public double von;
        public double bis;
        public double abstand;
        public String name;
        public Track(int gleisId, double von, double bis, double abstand, String name) {
            this.gleisId = gleisId;
            this.von = von;
            this.bis = bis;
            this.abstand = abstand;
            this.name = name;
        }
    }
    // TODO: public native void setTrack(int baliseId, int stellung, String protokoll);
    private native void getTrack0(int gleisId, ArrayList res);
    public Track getTrack(int gleisId) throws Exception {
        ArrayList al = new ArrayList();
        getTrack0(gleisId, al);
        if(al.size() != 4) {
            throw new Exception("invalid result returned");
        }
        return new Track(gleisId, (double)al.get(0), (double)al.get(1), (double)al.get(2), (String)al.get(3));
    }

    public class TrackConnection {
        public int gleis1Id;
        public int gleis2Id;
        public double von;
        public double bis;
        public String name;
        public int weiche1Id;
        public int weiche2Id;
        public TrackConnection(int gleis1Id, int gleis2Id, double von, double bis, String name, int weiche1Id, int weiche2Id) {
            this.gleis1Id = gleis1Id;
            this.gleis2Id = gleis2Id;
            this.von = von;
            this.bis = bis;
            this.name = name;
            this.weiche1Id = weiche1Id;
            this.weiche2Id = weiche2Id;
        }
    }
    // TODO: public native void setTrackConnection(int baliseId, int stellung, String protokoll);
    private native void getTrackConnection0(int gleis1Id, int gleis2Id, ArrayList res);
    public TrackConnection getTrackConnection(int gleis1Id, int gleis2Id) throws Exception {
        ArrayList al = new ArrayList();
        getTrackConnection0(gleis1Id, gleis2Id, al);
        if(al.size() != 6) {
            throw new Exception("invalid result returned");
        }
        // NOTE: index 0 is gleisBasisId
        return new TrackConnection(gleis1Id, gleis2Id, (double)al.get(1), (double)al.get(2), (String)al.get(3), (int)al.get(4), (int)al.get(5));
    }

    public class TrainPosition {
        public int trainTyp;
        public int direction;
        public ArrayList positions;
        public ArrayList gleisList;
        public TrainPosition(int trainTyp, int direction, ArrayList positions, ArrayList gleisList) {
            this.trainTyp = trainTyp;
            this.direction = direction;
            this.positions = positions;
            this.gleisList = gleisList;
        }
    }
    // TODO: public native void setTrainPosition(int weicheId, int gleisId);
    private native void getTrainPosition0(int trainTyp, ArrayList res, ArrayList positions, ArrayList gleisList);
    public TrainPosition getTrainPosition(int trainTyp) throws Exception {
        ArrayList al = new ArrayList();
        ArrayList alP = new ArrayList();
        ArrayList alG = new ArrayList();
        getTrainPosition0(trainTyp, al, alP, alG);
        if(al.size() != 1) {
            throw new Exception("invalid result returned");
        }
        return new TrainPosition(trainTyp, (int)al.get(0), alP, alG);
    }

}

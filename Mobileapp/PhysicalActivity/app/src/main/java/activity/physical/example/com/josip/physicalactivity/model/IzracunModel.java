package activity.physical.example.com.josip.physicalactivity.model;

/**
 * Created by Korisnik on 15.2.2018..
 */

public class IzracunModel {
    BikeActivity bike;
    RunningActivity run;
    WalkActivity walk;

    public IzracunModel(){

    }
    public IzracunModel(BikeActivity bike){
        this.bike=bike;

    }
    public IzracunModel(RunningActivity run){
        this.run=run;

    }
    public IzracunModel(WalkActivity walk){
        this.walk=walk;
    }
    public IzracunModel(BikeActivity bike,RunningActivity run,WalkActivity walk){
        this.bike=bike;
        this.run=run;
        this.walk=walk;
    }

    public BikeActivity getBike() {
        return bike;
    }

    public void setBike(BikeActivity bike) {
        this.bike = bike;
    }

    public RunningActivity getRun() {
        return run;
    }

    public void setRun(RunningActivity run) {
        this.run = run;
    }

    public WalkActivity getWalk() {
        return walk;
    }

    public void setWalk(WalkActivity walk) {
        this.walk = walk;
    }
}

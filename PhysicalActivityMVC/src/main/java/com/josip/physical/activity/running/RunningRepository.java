package com.josip.physical.activity.running;
import java.util.Date;
import java.util.List;
public interface RunningRepository {
public List<RunningActivity> results();
public boolean spremiPodatke(RunningActivity run);
public List<RunningActivity> pokaziPoDatumu(Date date);
public boolean izbrisi();
public boolean izbrisiPoDatumu(Date date);

}

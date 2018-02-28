package com.josip.physical.activity.walking;
import java.util.Date;
import java.util.List;
public interface WalkingRepository {
List<WalkingActivity> res();
public List<WalkingActivity> izlistajSve();
public List<WalkingActivity> izlistajPoDatumu(Date date);
public WalkingActivity spremiPodatke(WalkingActivity wal);
public boolean izbrisiSve();
public boolean izbrisiPoDatumu(Date date);


}

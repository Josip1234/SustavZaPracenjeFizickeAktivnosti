package com.josip.physical.activity.walking;
import java.util.Date;
import java.util.List;
public interface WalkingRepository {
List<WalkingActivity> res();
public List<WalkingActivity> izlistajPoBrojuKoraka(int koraci);
public List<WalkingActivity> izlistajPoDatumu(Date date);
public List<WalkingActivity> izslistajPoDatumuiKoracima(Date date, int koraci);
public WalkingActivity spremiPodatke(WalkingActivity wal);
public boolean izbrisiSve();
public boolean izbrisiPoDatumu(Date date);
public boolean izbrisiPoKoracima(int koraci);
public boolean izbrisiPoDatumuIKoracima(Date date,int koraci);

}

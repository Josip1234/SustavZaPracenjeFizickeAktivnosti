package com.josip.physical.activity.walking;
import java.util.Date;
import java.util.List;
public interface WalkingRepository {
public List<WalkingActivity> res(String username);

public WalkingActivity spremiPodatke(WalkingActivity wal);



}

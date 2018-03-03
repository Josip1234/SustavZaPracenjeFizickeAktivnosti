package com.josip.physical.activity.biking;
import java.util.Date;
import java.util.List;;
public interface BikingRepository {
public List<BikingActivity> izlistajSve();
public BikingActivity insert(BikingActivity bak);
public boolean delete(BikingActivity bak);
public boolean izbrisiPoDatumu(Date datum);
public List<BikingActivity> izlistajRezultatePoDatumu(Date datum);
}

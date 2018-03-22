package com.josip.physical.activity.summary;
import java.util.Date;
import java.util.List;


public interface SummaryRepository {
public List<SummaryActivity> show(String name);
public SummaryActivity dodajUkupno(SummaryActivity summary);
public List<SummaryActivity> filtriraj(String name,String datum1,String datum2);
public SummaryBiking dodaj(SummaryBiking bike);
public List<SummaryBiking> izlistaj(String username);
public SummaryRunning dodaj(SummaryRunning run);
public List<SummaryRunning> lista(String username);
public WalkingStatistika dodaj(WalkingStatistika walk);
public List<WalkingStatistika> izlistajHod(String username);

}

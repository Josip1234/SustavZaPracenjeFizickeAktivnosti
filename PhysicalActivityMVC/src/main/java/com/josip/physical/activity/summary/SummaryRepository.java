package com.josip.physical.activity.summary;
import java.util.Date;
import java.util.List;


public interface SummaryRepository {
public List<SummaryActivity> show();
public SummaryActivity dodajUkupno(SummaryActivity summary);
public SummaryBiking dodaj(SummaryBiking bike);
public SummaryRunning dodaj(SummaryRunning run);
public WalkingStatistika dodaj(WalkingStatistika walk);
}

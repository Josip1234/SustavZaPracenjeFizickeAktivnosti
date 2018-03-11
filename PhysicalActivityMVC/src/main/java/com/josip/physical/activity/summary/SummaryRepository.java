package com.josip.physical.activity.summary;
import java.util.Date;
import java.util.List;
public interface SummaryRepository {
public List<SummaryActivity> show();
public boolean dodajStatistiku(SummaryBiking sumBike);
}

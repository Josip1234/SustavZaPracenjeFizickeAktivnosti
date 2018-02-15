package com.josip.physical.activity.running;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.biking.BikingActivity;
@Component
public class RunningImplementation implements RunningRepository {
    @Autowired
    RunningActivity run;
	@Override
	public List<RunningActivity> results() {
	List<RunningActivity> running=new ArrayList<RunningActivity>();
		
		run.setKorisnik("jbosnjak3@gmail.com");
		run.setBrzinaUkm(20);
		run.setLatitude(-15.64);
		run.setLokacija("Franka Andrijasevica 16");
		run.setLongitude(65.985);
		run.setUdaljenost(15.64);
		run.setVrijemeAktivnosti("15 minuta");
	    running.add(new RunningActivity(
	    		run.getKorisnik(),
	    		run.getBrzinaUkm(),
	    		run.getLokacija(),
	    		run.getLatitude(),
	    		run.getLongitude(),
	    		run.getVrijemeAktivnosti(),
	    		run.getUdaljenost()
	    		));
	    
		
		return running;
	}
	@Override
	public boolean spremiPodatke(RunningActivity run) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<RunningActivity> pokaziPoDatumu(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean izbrisi() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean izbrisiPoDatumu(Date date) {
		// TODO Auto-generated method stub
		return false;
	}

}

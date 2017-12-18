package com.josip.physical.activity.running;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RunningImplementation implements RunningRepository {
    @Autowired
    RunningActivity ra;
	
	@Override
	public List<RunningActivity> display() {
		List<RunningActivity> act=new ArrayList<RunningActivity>();
		ra.setKorisnik("jbosnjak3@gmail.com");
		ra.setBrzinaUkm(12.64);
		ra.setVrijemeAktivnosti("30 minuta");
		ra.setLokacija("Sveti rok 81");
		ra.setLatitude(-125.1236);
		ra.setLongitude(15.64);
		ra.setUdaljenost(0.54);
		act.add(new RunningActivity(ra.getVrijemeAktivnosti(), ra.getBrzinaUkm(), ra.getLokacija(), ra.getLongitude(), ra.getLatitude(), ra.getKorisnik(), ra.getUdaljenost()));
		return act;
	}

}

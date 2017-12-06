package com.josip.physical.activity.biking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.regist.Registration;

@Component
public class BikingImplementation implements BikingRepository {

	@Autowired
	BikingActivity bike;
	@Override
	public List<BikingActivity> listStuff() {
		List<BikingActivity> stuff=new ArrayList<BikingActivity>();
		
		bike.setKorisnik("jbosnjak3@gmail.com");
		bike.setLokacija("Sveti rok 81");
		bike.setBrzinaUkm(12.65);
		bike.setLatitude(12.656);
		bike.setLongitude(-16.57);
		bike.setUdaljenost(1.058);
		bike.setVrijemeAktivnosti("1.15");
	    stuff.add(new BikingActivity(
	    		bike.getVrijemeAktivnosti(),
	    	    bike.getBrzinaUkm(),
	    		bike.getLokacija(),
	    		bike.getLongitude(),
	    		bike.getLatitude(),
	    		bike.getKorisnik(),
	    		bike.getUdaljenost()
	    		
	    		
	    		
	    		
	    		));
	    
		return stuff;
	}

}

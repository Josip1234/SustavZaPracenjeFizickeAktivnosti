package com.josip.physical.activity.biking;

import java.util.ArrayList;
import java.util.Date;
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
	public List<BikingActivity> izlistajSve() {
		List<BikingActivity> stuff=new ArrayList<BikingActivity>();
		
		bike.setKorisnik("jbosnjak3@gmail.com");
		
		bike.setBrzinaUkm(12.65);
	
		bike.setUdaljenost(1.058);
		bike.setVrijemeAktivnosti("1.15");
		bike.setDatum("");
	    stuff.add(new BikingActivity(
	    		bike.getVrijemeAktivnosti(),
	    	    bike.getBrzinaUkm(),
	    
	    		bike.getKorisnik(),
	    		bike.getUdaljenost(),
	    		bike.getDatum()
	    		
	    		
	    		
	    		
	    		));
	    
		return stuff;
	}
	@Override
	public boolean insert(BikingActivity bak) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(BikingActivity bak) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean izbrisiPoDatumu(Date datum) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<BikingActivity> izlistajRezultatePoDatumu(Date datum) {
		// TODO Auto-generated method stub
		return null;
	}

}

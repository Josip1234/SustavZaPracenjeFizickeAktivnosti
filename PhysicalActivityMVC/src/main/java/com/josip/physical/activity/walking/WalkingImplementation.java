package com.josip.physical.activity.walking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalkingImplementation implements WalkingRepository {
@Autowired
WalkingActivity walk;

@Override
public List<WalkingActivity> res() {
	List<WalkingActivity> activity=new ArrayList<WalkingActivity>();
	walk.setAdresa("Sveti rok 81");
	walk.setBrzinaUkm(12.00);
	walk.setKoraci(10);
	walk.setKorisnik("jbosnjak3@gmail.com");
	walk.setLatitude(-25);
	walk.setLongitude(25);
	walk.setUdaljenost(1.547);
	walk.setVrijemeAktivnosti("5 minuta");
	activity.add(new WalkingActivity(walk.getUdaljenost(),walk.getVrijemeAktivnosti(),walk.getKoraci(),walk.getAdresa(),walk.getLongitude(),walk.getLatitude(),walk.getBrzinaUkm(),walk.getKorisnik()));
	return activity;
}

@Override
public List<WalkingActivity> izlistajPoBrojuKoraka(int koraci) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<WalkingActivity> izlistajPoDatumu(Date date) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<WalkingActivity> izslistajPoDatumuiKoracima(Date date, int koraci) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean spremiPodatke(List<WalkingActivity> wal) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean izbrisiSve() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean izbrisiPoDatumu(Date date) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean izbrisiPoKoracima(int koraci) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean izbrisiPoDatumuIKoracima(Date date, int koraci) {
	// TODO Auto-generated method stub
	return false;
}
}

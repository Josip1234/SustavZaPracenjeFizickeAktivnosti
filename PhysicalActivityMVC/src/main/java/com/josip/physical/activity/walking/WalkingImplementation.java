package com.josip.physical.activity.walking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class WalkingImplementation implements WalkingRepository {
    @Autowired
    WalkingActivity wal;
	@Override
	public List<WalkingActivity> disp() {
		List<WalkingActivity> walk=new ArrayList<WalkingActivity>();
		wal.setAdresa("Sveti rok 81");
		wal.setBrzinaUkm(5.00);
		wal.setKoraci(10);
		wal.setKorisnik("jbosnjak3@gmail.com");
		wal.setLatitude(12.84);
		wal.setLongitude(8.98);
		wal.setUdaljenost(32.65);
		wal.setVrijemeAktivnosti("15 minuta");
		walk.add(new WalkingActivity(wal.getUdaljenost(), wal.getVrijemeAktivnosti(), wal.getKoraci(), wal.getAdresa(), wal.getLongitude(), wal.getLatitude(), wal.getBrzinaUkm(), wal.getKorisnik()));
		return walk;
	}

}

package com.josip.physical.activity.walking;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.SpringDataSource;

@Component
public class WalkingImplementation implements WalkingRepository {
@Autowired
WalkingActivity walk;
@Autowired
SpringDataSource d;
@Override
public List<WalkingActivity> res() {
	List<WalkingActivity> activity=new ArrayList<WalkingActivity>();
    Date date = new Date();
	
	walk.setBrzinaUkm(12.00);
	walk.setKoraci(10);
	walk.setKorisnik("jbosnjak3@gmail.com");
	
	
	walk.setUdaljenost(1.547);
	walk.setVrijemeAktivnosti("5 minuta");
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String vrijeme=sdf.format(date);
	walk.setDatumIvrijeme(vrijeme);
	activity.add(new WalkingActivity(walk.getUdaljenost(),walk.getVrijemeAktivnosti(),walk.getKoraci(),walk.getBrzinaUkm(),walk.getKorisnik(),walk.getDatumIvrijeme()));
	return activity;
}





@Override
public WalkingActivity spremiPodatke(WalkingActivity wal) {
	
	
	d=new SpringDataSource();
	if(null!=d.getObj()) {
		String insert="INSERT INTO walking (udaljenost,vrijemeAktivnosti,koraci,brzinaUkm,email,datum) VALUES(?,?,?,?,?,?)";
	
		d.getObj().update(insert,wal.getUdaljenost(),wal.getVrijemeAktivnosti(),wal.getKoraci(),wal.getBrzinaUkm(),wal.getKorisnik(),wal.getDatumIvrijeme());
	
	}
	return wal;
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
public List<WalkingActivity> izlistajSve() {
	// TODO Auto-generated method stub
	return null;
}





@Override
public List<WalkingActivity> izlistajPoDatumu(Date date) {
	// TODO Auto-generated method stub
	return null;
}
}

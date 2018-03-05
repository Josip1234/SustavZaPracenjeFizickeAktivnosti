package com.josip.physical.activity.walking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.regist.Registration;

@Component
public class WalkingImplementation implements WalkingRepository {
@Autowired
WalkingActivity walk;
@Autowired
SpringDataSource d;
@Override
public List<WalkingActivity> res(String username) {
	List<WalkingActivity> activity=new ArrayList<WalkingActivity>();
	
	
	d=new SpringDataSource();
	
	if(null != d.getObj()) {
		String select="SELECT udaljenost,vrijemeAktivnosti,koraci,brzinaUkm,email,datum FROM `walking` WHERE email='"+username+"'";
		List<WalkingActivity> walk=d.getObj().query(select,new RowMapper() {
			public WalkingActivity mapRow(final ResultSet result,final int rowNum) throws SQLException{
				WalkingActivity walk=new WalkingActivity();
				walk.setBrzinaUkm(result.getDouble("brzinaUkm"));
				walk.setDatumIvrijeme(result.getString("datum"));
				walk.setKoraci(result.getInt("koraci"));
				walk.setKorisnik(result.getString("email"));
				walk.setUdaljenost(result.getDouble("udaljenost"));
				walk.setVrijemeAktivnosti(result.getString("vrijemeAktivnosti"));
				
				return walk;
				
			}});
			
		for (WalkingActivity walkingActivity : walk) {
			activity.add(walkingActivity);
		}
			
		}
		
	
	
	
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

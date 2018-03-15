package com.josip.physical.activity.running;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.biking.BikingActivity;
import com.josip.physical.activity.walking.WalkingActivity;
@Component
public class RunningImplementation implements RunningRepository {
    @Autowired
    RunningActivity run;
    @Autowired
    SpringDataSource data;
	@Override
	public List<RunningActivity> results(String username) {
		List<RunningActivity> activity=new ArrayList<RunningActivity>();
		
		
		data=new SpringDataSource();
		
		if(null != data.getObj()) {
			String select="SELECT vrijemeAktivnosti,brzinaUkm,udaljenost,email,datum FROM `running` WHERE email='"+username+"'";
			List<RunningActivity> run=data.getObj().query(select,new RowMapper() {
				public RunningActivity mapRow(final ResultSet result,final int rowNum) throws SQLException{
					RunningActivity run=new RunningActivity();
					run.setBrzinaUkm(result.getDouble("brzinaUkm"));
					run.setDatum(result.getString("datum"));
					
					run.setKorisnik(result.getString("email"));
					run.setUdaljenost(result.getDouble("udaljenost"));
					run.setVrijemeAktivnosti(result.getString("vrijemeAktivnosti"));
					
					return run;
					
				}});
				
			for (RunningActivity runningActivity : run) {
				activity.add(runningActivity);
			}
				
			}
			
		
		
		
		return activity;
	}
	@Override
	public RunningActivity spremiPodatke(RunningActivity run) {
		data=new SpringDataSource();
		if(null!=data.getObj()) {
			String insert="INSERT INTO running (vrijemeAktivnosti,brzinaUkm,udaljenost,email,datum) VALUES(?,?,?,?,?)";
		
			data.getObj().update(insert,run.getVrijemeAktivnosti(),run.getBrzinaUkm(),run.getUdaljenost(),run.getKorisnik(),run.getDatum());
		
		}
		return run;
	}


}

package com.josip.physical.activity.biking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.walking.WalkingActivity;

//@Component
public class BikingImplementation /*implements BikingRepository*/ {
/*
	@Autowired
	BikingActivity bike;
	@Autowired
	SpringDataSource data;
	@Override
	public List<BikingActivity> izlistajSve(String username) {
		List<BikingActivity> activity=new ArrayList<BikingActivity>();
		
		
		data=new SpringDataSource();
		
		if(null != data.getObj()) {
			String select="SELECT vrijemeAktivnosti,brzinaUkm,udaljenost,email,datum FROM `biking` WHERE email='"+username+"'";
			List<BikingActivity> bike=data.getObj().query(select,new RowMapper() {
				public BikingActivity mapRow(final ResultSet result,final int rowNum) throws SQLException{
					BikingActivity bike=new BikingActivity();
					bike.setBrzinaUkm(result.getDouble("brzinaUkm"));
					bike.setDatum(result.getString("datum"));
					bike.setKorisnik(result.getString("email"));
					bike.setUdaljenost(result.getDouble("udaljenost"));
					bike.setVrijemeAktivnosti(result.getString("vrijemeAktivnosti"));
					
					return bike;
					
				}});
				
			for (BikingActivity bikingActivity : bike) {
				activity.add(bikingActivity);
			}
				
			}
			
		
		
		
		return activity;
	}
	@Override
	public BikingActivity insert(BikingActivity bak) {
		data=new SpringDataSource();
		if(null!=data.getObj()) {
			String insert="INSERT INTO biking (vrijemeAktivnosti,brzinaUkm,udaljenost,email,datum) VALUES(?,?,?,?,?)";
		
			data.getObj().update(insert,bak.getVrijemeAktivnosti(),bak.getBrzinaUkm(),bak.getUdaljenost(),bak.getKorisnik(),bak.getDatum());
		
		}
		return bak;
	}
*/

}

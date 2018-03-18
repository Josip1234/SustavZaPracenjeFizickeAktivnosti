package com.josip.physical.activity.summary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.running.RunningActivity;


@Component
public class SummaryImplementation  implements SummaryRepository{
	

@Autowired
SummaryActivity summar;

@Autowired
SpringDataSource dat;


	@Override
	public List<SummaryActivity> show(String name) {
		List<SummaryActivity> list=new ArrayList<SummaryActivity>();
	dat=new SpringDataSource();
		
		if(null != dat.getObj()) {
			String select="SELECT korisnikSum,ukupanBrojKoraka,ukupnoVrijemeAktivnosti,ukupnaPrijedjenaUdaljenost,prosjecnaBrzina,datum FROM `summarystatistika` WHERE korisnikSum='"+name+"'";
			List<SummaryActivity> sum=dat.getObj().query(select,new RowMapper() {
				public SummaryActivity mapRow(final ResultSet result,final int rowNum) throws SQLException{
					SummaryActivity suma=new SummaryActivity();
					suma.setKorisnik(result.getString("korisnikSum"));
					suma.setUkupanBrojKoraka(result.getInt("ukupanBrojKoraka"));
					suma.setUkupnoVrijeme(result.getDouble("ukupnoVrijemeAktivnosti"));
					suma.setPrijedjeniKilometri(result.getDouble("ukupnaPrijedjenaUdaljenost"));
					suma.setProsjecnaBrzina(result.getDouble("prosjecnaBrzina"));
					suma.setDatum(result.getString("datum"));
					
					return suma;
					
				}});
				
			for (SummaryActivity summaryActivity : sum) {
				list.add(summaryActivity);
			}
				
			}
			
		
		
		
		
		return list;
	}
	@Override
	public SummaryActivity dodajUkupno(SummaryActivity summary) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String insert="INSERT INTO summarystatistika (korisnikSum,ukupanBrojKoraka,ukupnoVrijemeAktivnosti,ukupnaPrijedjenaUdaljenost,prosjecnaBrzina,datum) VALUES(?,?,?,?,?,?)";
		
			dat.getObj().update(insert,summary.getKorisnik(),summary.getUkupanBrojKoraka(),summary.getUkupnoVrijeme(),summary.getPrijedjeniKilometri(),summary.getProsjecnaBrzina(),summary.getDatum());
		
		}
		
		return summary;
	}

	@Override
	public SummaryBiking dodaj(SummaryBiking bike) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String insert="INSERT INTO bikingstatistika (korisnikBike,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period) VALUES(?,?,?,?,?)";
		
			dat.getObj().update(insert,bike.getKorisnik(),bike.getUkupnaUdaljenost(),bike.getUkupnoVrijemeAktivnosti(),bike.getProsjecnaBrzinaUkm(),bike.getPeriod());
		
		}
		
		return bike;
	}
	public List<SummaryBiking> izlistaj(String username) {
		List<SummaryBiking> list=new ArrayList<SummaryBiking>();
	dat=new SpringDataSource();
		
		if(null != dat.getObj()) {
			String select="SELECT korisnikBike,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period FROM `bikingstatistika` WHERE korisnikBike='"+username+"'";
			List<SummaryBiking> sumarybik=dat.getObj().query(select,new RowMapper() {
				public SummaryBiking mapRow(final ResultSet result,final int rowNum) throws SQLException{
					SummaryBiking bik=new SummaryBiking();
					bik.setKorisnik(result.getString("korisnikBike"));
					bik.setUkupnaUdaljenost(result.getDouble("ukupnaUdaljenost"));
					bik.setUkupnoVrijemeAktivnosti(result.getDouble("ukupnoVrijemeAktivnosti"));
					bik.setProsjecnaBrzinaUkm(result.getDouble("prosjecnaBrzinaUkm"));
					bik.setPeriod(result.getString("period"));
					
					
					return bik;
					
				}});
				
			for (SummaryBiking summaryBiking : sumarybik) {
				list.add(summaryBiking);
			}
				
			}
			
		
		
		
		
		return list;
		
	}

	@Override
	public SummaryRunning dodaj(SummaryRunning run) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String insert="INSERT INTO runningstatistika (korisnikRun,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period) VALUES(?,?,?,?,?)";
		
			dat.getObj().update(insert,run.getKorisnik(),run.getUkupnaUdaljenost(),run.getUkupnoVrijemeAktivnosti(),run.getProsjecnaBrzinaUkm(),run.getPeriod());
		
		}
		return run;
	}
	public List<SummaryRunning> lista(String username){
		List<SummaryRunning> list=new ArrayList<SummaryRunning>();
		dat=new SpringDataSource();
			
			if(null != dat.getObj()) {
				String select="SELECT korisnikRun,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period FROM `runningstatistika` WHERE korisnikRun='"+username+"'";
				List<SummaryRunning> sumaryrun=dat.getObj().query(select,new RowMapper() {
					public SummaryRunning mapRow(final ResultSet result,final int rowNum) throws SQLException{
						SummaryRunning run=new SummaryRunning();
						run.setKorisnik(result.getString("korisnikRun"));
						run.setUkupnaUdaljenost(result.getDouble("ukupnaUdaljenost"));
						run.setUkupnoVrijemeAktivnosti(result.getDouble("ukupnoVrijemeAktivnosti"));
						run.setProsjecnaBrzinaUkm(result.getDouble("prosjecnaBrzinaUkm"));
						run.setPeriod(result.getString("period"));
						
						
						return run;
						
					}});
					
				for (SummaryRunning summaryRunning : sumaryrun) {
					list.add(summaryRunning);
				}
					
				}
				
			
			
			
			
			return list;
	}
	@Override
	public WalkingStatistika dodaj(WalkingStatistika walk) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String insert="INSERT INTO walkingstatistika (email,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period,ukupanBrojKoraka) VALUES(?,?,?,?,?,?)";
		
			dat.getObj().update(insert,walk.getEmail(),walk.getUkupnaUdaljenost(),walk.getUkupnoVrijemeAktivnosti(),walk.getProsjecnaBrzinaUkm(),walk.getPeriod(),walk.getUkupanBrojKoraka());
		
		}
		return walk;
	}
	
	public List<WalkingStatistika> izlistajHod(String username){
		List<WalkingStatistika> list=new ArrayList<WalkingStatistika>();
		dat=new SpringDataSource();
			
			if(null != dat.getObj()) {
				String select="SELECT email,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period,ukupanBrojKoraka FROM `walkingstatistika` WHERE email='"+username+"'";
				List<WalkingStatistika> sumarywalk=dat.getObj().query(select,new RowMapper() {
					public WalkingStatistika mapRow(final ResultSet result,final int rowNum) throws SQLException{
						WalkingStatistika walk=new WalkingStatistika();
						walk.setEmail(result.getString("email"));
						walk.setUkupnaUdaljenost(result.getDouble("ukupnaUdaljenost"));
						walk.setUkupnoVrijemeAktivnosti(result.getDouble("ukupnoVrijemeAktivnosti"));
						walk.setProsjecnaBrzinaUkm(result.getDouble("prosjecnaBrzinaUkm"));
						walk.setPeriod(result.getString("period"));
						walk.setUkupanBrojKoraka(result.getInt("ukupanBrojKoraka"));
						
						return walk;
						
					}});
					
				for (WalkingStatistika summaryWalking : sumarywalk) {
					list.add(summaryWalking);
				}
					
				}
				
			
			
			
			
			return list;
	}
	

}

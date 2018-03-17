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
	/*@Override
	public boolean dodajStatistiku(SummaryBiking sumBike) {
		data=new SpringDataSource();
		if(null!=data.getObj()) {
			String insert="INSERT INTO bikingstatistika (korisnikBike,ukupnaUdaljenost,prosjecnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period) VALUES(?,?,?,?,?,?,?)";
		
			data.getObj().update(insert,sumBike.getKorisnik(),sumBike.getUkupnaUdaljenost(),sumBike.getProsjecnaUdaljenost(),sumBike.getUkupnoVrijemeAktivnosti(),sumBike.getProsjecnoVrijemeAktivnosti(),sumBike.getProsjecnaBrzinaUkm(),sumBike.getPeriod());
		
		}
		
		return true;
	}*/
	/*@Override
	public boolean dodajStatistiku(SummaryRunning sumRun) {
		data=new SpringDataSource();
		if(null!=data.getObj()) {
			String insert="INSERT INTO runningstatistika (korisnikRun,ukupnaUdaljenost,prosjecnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period) VALUES(?,?,?,?,?,?,?)";
		
			data.getObj().update(insert,sumRun.getKorisnik(),sumRun.getUkupnaUdaljenost(),sumRun.getProsjecnaUdaljenost(),sumRun.getUkupnoVrijemeAktivnosti(),sumRun.getProsjecnoVrijemeAktivnosti(),sumRun.getProsjecnaBrzinaUkm(),sumRun.getPeriod());
		
		}
		
		return true;
	}*/
	@Override
	public SummaryBiking dodaj(SummaryBiking bike) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String insert="INSERT INTO bikingstatistika (korisnikBike,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period) VALUES(?,?,?,?,?)";
		
			dat.getObj().update(insert,bike.getKorisnik(),bike.getUkupnaUdaljenost(),bike.getUkupnoVrijemeAktivnosti(),bike.getProsjecnaBrzinaUkm(),bike.getPeriod());
		
		}
		
		return bike;
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
	@Override
	public WalkingStatistika dodaj(WalkingStatistika walk) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String insert="INSERT INTO walkingstatistika (email,ukupnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period,ukupanBrojKoraka) VALUES(?,?,?,?,?,?)";
		
			dat.getObj().update(insert,walk.getEmail(),walk.getUkupnaUdaljenost(),walk.getUkupnoVrijemeAktivnosti(),walk.getProsjecnaBrzinaUkm(),walk.getPeriod(),walk.getUkupanBrojKoraka());
		
		}
		return walk;
	}

}

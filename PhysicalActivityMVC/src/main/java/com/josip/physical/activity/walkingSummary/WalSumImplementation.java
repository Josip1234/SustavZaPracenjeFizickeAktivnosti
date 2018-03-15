package com.josip.physical.activity.walkingSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.summary.SummaryActivity;
@Component
public class WalSumImplementation implements WalSumRepository{
	@Autowired
	WalkingStatistika stat;

	@Autowired
	SpringDataSource dat;
	@Override
	public WalkingStatistika dodajStatistiku(WalkingStatistika statistika) {
		dat=new SpringDataSource();
		if(null!=dat.getObj()) {
			String umetni="INSERT INTO walkingstatistika(email,ukupnaUdaljenost,prosjecnaUdaljenost,ukupnoVrijemeAktivnosti,prosjecnoVrijemeAktivnosti,prosjecnaBrzinaUkm,period,ukupanBrojKoraka,prosjecanBrojKoraka) VALUES(?,?,?,?,?,?,?,?,?)";
		//postoji greška u sql u
			dat.getObj().update(umetni,statistika.getEmail(),statistika.getUkupnaUdaljenost(),statistika.getProsjecnaUdaljenost(),statistika.getUkupnoVrijemeAktivnosti(),statistika.getProsjecnoVrijemeAktivnosti(),statistika.getProsjecnaBrzinaUkm(),statistika.getPeriod(),statistika.getUkupanBrojKoraka(),statistika.getProsjecanBrojKoraka());
		
		}
		
		return statistika;
	}

}

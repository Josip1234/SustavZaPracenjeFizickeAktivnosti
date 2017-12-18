package com.josip.physical.activity.summary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SummaryImplementation implements SummaryRepository {
@Autowired
SummaryActivity sum;
@Override
	public List<SummaryActivity> show() {
		List<SummaryActivity> list=new ArrayList<SummaryActivity>();
		sum.setKorisnik("jbosnjak3@gmail.com");
		sum.setPrijedjeniKilometri(50.5);
		sum.setProsjecnaBrzina(25.89);
		sum.setUkupanBrojKoraka(50);
		sum.setUkupnoVrijeme("200 minuta");
		list.add(new SummaryActivity(sum.getKorisnik(), sum.getUkupanBrojKoraka(), sum.getUkupnoVrijeme(), sum.getPrijedjeniKilometri(), sum.getProsjecnaBrzina()));
		return list;
	}

}
